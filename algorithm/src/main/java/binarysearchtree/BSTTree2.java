package binarysearchtree;

/**
 * 二叉搜索树
 */
public class BSTTree2<T extends Comparable<T>, V> {
    static class BSTNode<T, V> {
        T key;
        V value;
        BSTNode<T, V> left;
        BSTNode<T, V> right;

        public BSTNode(T key) {
            this.key = key;
        }

        BSTNode(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(T key, V value, BSTNode<T, V> left, BSTNode<T, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    BSTNode<T, V> root;//根节点

    public V get(T key) {
        BSTNode<T, V> p = root;
        while (p != null) {
            /*
             * compareTo
             * -1 key < p.key
             * 0 key == p.key
             * 1 key > p.key
             */
            int result = key.compareTo(p.key);
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }
}
