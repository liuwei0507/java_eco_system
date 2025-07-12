package binarysearchtree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTree1Test {

    public BSTTree1 createTree() {
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, "张无忌");
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, "宋青书");
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, "周芷若", n1, n3);

        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, "说不得");
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, "殷离");
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, "赵敏", n5, n7);

        BSTTree1.BSTNode root = new BSTTree1.BSTNode(4, "小昭", n2, n6);

        BSTTree1 tree = new BSTTree1();
        tree.root = root;
        return tree;
    }

    @Test
    void testGet() {
        BSTTree1 tree = createTree();
        assertEquals("小昭", tree.get(4));
        assertEquals("殷离", tree.get(7));
        assertNull(tree.get(8));
    }

    @Test
    void testGet_2() {
        BSTTree1 tree = createTree();
        assertEquals("小昭", tree.get_2(4));
        assertEquals("殷离", tree.get_2(7));
        assertNull(tree.get_2(8));
    }

    @Test
    void testMin() {
        BSTTree1 tree = createTree();
        assertEquals("张无忌", tree.min());
    }

    @Test
    void testMin_2() {
        BSTTree1 tree = createTree();
        assertEquals("张无忌", tree.min_2());
    }

    @Test
    void testMax() {
        BSTTree1 tree = createTree();
        assertEquals("殷离", tree.max());
    }

    @Test
    void testPut() {
        BSTTree1 tree = createTree();
        tree.put(8, "小昭");
        assertEquals("小昭", tree.get(8));
        tree.put(4, "小昭1");
        assertEquals("小昭1", tree.get(4));
    }

    @Test
    void testPredecessor() {
        BSTTree1 tree = createTree();
        assertNull(tree.predecessor(8));
        assertNull(tree.predecessor(1));
        assertEquals("赵敏", tree.predecessor(7));
        assertEquals("说不得", tree.predecessor(6));
        assertEquals("小昭", tree.predecessor(5));
        assertEquals("宋青书", tree.predecessor(4));
        assertEquals("周芷若", tree.predecessor(3));
        assertEquals("张无忌", tree.predecessor(2));
    }

    @Test
    void testSuccessor() {
        BSTTree1 tree = createTree();
        assertNull(tree.successor(8));
        assertNull(tree.successor(7));
        assertEquals("殷离", tree.successor(6));
        assertEquals("赵敏", tree.successor(5));
        assertEquals("说不得", tree.successor(4));
        assertEquals("小昭", tree.successor(3));
        assertEquals("宋青书", tree.successor(2));
        assertEquals("周芷若", tree.successor(1));
    }

    @Test
    void testLess() {
        BSTTree1 tree = createTree();
        assertIterableEquals(List.of("张无忌", "周芷若", "宋青书"), tree.less(4));
    }
}