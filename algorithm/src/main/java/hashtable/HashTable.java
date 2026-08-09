package hashtable;

/**
 * 给每份数据分配一个编号，放入表格（数组）
 * 建立编号与表格索引的关系，将来就可以通过编号快速查找到数据
 * 1，理想情况编号应当唯一，数组能容纳所有的数据
 * 2，现实是不能说为了容纳所有的数据造一个超大数组，编号也有可能重复
 * <p>
 * 解决：
 * 1，有限长度的数组，以【拉链】方式存储数据
 * 2，允许编号适当重复，通过数据自身来进行区分
 */
public class HashTable {

    //节点类
    static class Entry {
        int hash;//哈希码
        Object key;//键
        Object value;//值
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];//存储节点的数组
    int size = 0;//哈希表元素个数

    //根据哈希码获取value
    Object get(int hash, Object key) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            return null;
        }
        Entry p = table[index];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 求模运算替换为位运算
     * 前提： 数组长度是 2 的 n次方
     * -  hash % 数组长度等价与 hash & ( 数组长度 - 1 )
     *
     * @param hash
     * @param key
     * @param value
     */
    //向hash表中存入新的key value，如果key重复，则更新value
    void put(int hash, Object key, Object value) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            // index处有空位，直接新增
            table[index] = new Entry(hash, key, value);
        } else {
            // index处有数据，沿着链表查找，有重复key更新，否则新增
            Entry p = table[index];
            while (true) {
                if (p.key.equals(key)) {
                    p.value = value;//更新
                    return;
                }
                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            p.next = new Entry(hash, key, value);
        }
        size++;
    }

    //根据hash码删除，返回删除的value
    Object remove(int hash, Object key) {
        return null;
    }

}
