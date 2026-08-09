package hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void put() {
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三"); // 1 % 16 == 1
        table.put(17, "li", "李四");   // 17 % 16 == 1
        table.put(2, "wang", "王五");  // 2

        assertEquals(3, table.size);
        assertEquals("张三", table.table[1].value);
        assertEquals("李四", table.table[1].next.value);
        assertEquals("王五", table.table[2].value);

        table.put(1, "zhang", "张4");
        table.put(17, "li", "李5");
        assertEquals("张4", table.table[1].value);
        assertEquals("李5", table.table[1].next.value);
    }
}