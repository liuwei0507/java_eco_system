package com.hmdp.utils;

public interface ILock {
    /**
     * 尝试获取锁，成功返回true，失败返回false
     *
     * @param timeoutSec
     * @return
     */
    boolean tryLock(long timeoutSec);

    void unLock();
}
