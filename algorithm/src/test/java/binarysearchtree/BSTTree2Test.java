package binarysearchtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTree2Test {

    public BSTTree2 createTree() {
        BSTTree2.BSTNode<String, String> n1 = new BSTTree2.BSTNode<>("a", "张无忌");
        BSTTree2.BSTNode<String, String> n3 = new BSTTree2.BSTNode<>("c", "宋青书");
        BSTTree2.BSTNode<String, String> n2 = new BSTTree2.BSTNode<>("b", "周芷若", n1, n3);

        BSTTree2.BSTNode<String, String> n5 = new BSTTree2.BSTNode<>("e", "说不得");
        BSTTree2.BSTNode<String, String> n7 = new BSTTree2.BSTNode<>("g", "殷离");
        BSTTree2.BSTNode<String, String> n6 = new BSTTree2.BSTNode<>("f", "赵敏", n5, n7);

        BSTTree2.BSTNode<String, String> root = new BSTTree2.BSTNode<>("d", "小昭", n2, n6);

        BSTTree2<String, String> tree = new BSTTree2<>();
        tree.root = root;
        return tree;
    }

    @Test
    void testGet() {
        BSTTree2<String, String> tree = createTree();
        assertEquals("小昭", tree.get("d"));
        assertEquals("殷离", tree.get("g"));
        assertNull(tree.get("h"));
    }

}