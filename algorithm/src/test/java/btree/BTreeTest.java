package btree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {


    @Test
    void testPut() {
        BTree bTree = new BTree(2);
        bTree.put(1);
        bTree.put(2);
        bTree.put(3);
        bTree.put(4);
        bTree.put(5);
        bTree.put(6);
    }
}