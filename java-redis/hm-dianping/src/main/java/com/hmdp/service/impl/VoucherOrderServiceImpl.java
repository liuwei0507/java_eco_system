package com.hmdp.service.impl;

import com.hmdp.dto.Result;
import com.hmdp.entity.SeckillVoucher;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.SimpleRedisLock;
import com.hmdp.utils.UserHolder;
import org.springframework.aop.framework.AopContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {
    @Resource
    private ISeckillVoucherService seckillVoucherService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
//    @Transactional
    public Result seckillVoucher(Long voucherId) {
        //1 查询优惠券
        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
        //2 判断秒杀是否开始
        if (voucher.getBeginTime().isAfter(LocalDateTime.now())) {
            return Result.fail("秒杀尚未开始");
        }
        //3 优惠券是否已经结束
        if (voucher.getEndTime().isBefore(LocalDateTime.now())) {
            return Result.fail("秒杀已经结束");
        }
        Long userId = UserHolder.getUser().getId();
//        synchronized (userId.toString().intern()) { // 锁对象，应该为唯一，可以使用订单id，加上优惠券id，加上用户id， 使用 intern， 保证字符串常量池只有一个对象
//            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();// 使用代理对象，解决spring事物失效问题
//            return proxy.createVoucherOrder(voucherId, voucher);
//        }
        //创建锁对象
        SimpleRedisLock lock = new SimpleRedisLock("order:" + userId, stringRedisTemplate);
        boolean isLock = lock.tryLock(1200);
        //判断是否获取锁成功
        if (!isLock) {
            //获取锁失败，返回失败或者重试
            return Result.fail("请勿重复下单");
        }
        try {
            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();// 使用代理对象，解决spring事物失效问题
            return proxy.createVoucherOrder(voucherId, voucher);
        } finally {
            lock.unLock();
        }
    }

    @Transactional
    public Result createVoucherOrder(Long voucherId, SeckillVoucher voucher) {
        // 一个人只能抢一次
        // 查询订单
        Long userId = UserHolder.getUser().getId();
        Long count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
        // 判断是否存在
        if (count > 0) {
            return Result.fail("您已经抢过该优惠券了");
        }

        //4 优惠券库存是否充足
        if (voucher.getStock() < 1) {
            return Result.fail("库存不足");
        }
        //5 扣减库存
        boolean success = seckillVoucherService.update()
                .setSql("stock = stock - 1") // set stock=stock-1
                .eq("voucher_id", voucherId).gt("stock", 0)// where id=? and stock>0 乐观锁解决超卖问题
                .update();
        if (!success) {
            return Result.fail("库存不足");
        }
        //6 创建订单
        VoucherOrder voucherOrder = new VoucherOrder();
        //7 生成订单id
        long orderId = redisIdWorker.nextId("order");
        voucherOrder.setId(orderId);
        voucherOrder.setUserId(userId);
        voucherOrder.setVoucherId(voucherId);
        //7 保存订单
        save(voucherOrder);
        //8 返回订单id
        return Result.ok(orderId);
    }
}
