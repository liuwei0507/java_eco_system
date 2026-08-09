package btree;

import java.util.Arrays;

public class BTree {
    static class Node {
        int[] keys;//关键字
        Node[] children;//孩子
        int keyNumber;//有效关键字的个数
        boolean leaf = true;//是否是叶子结点

        int t;//最小度数（最小孩子个数）

        public Node(int t) { //t>=2
            this.t = t;
            this.children = new Node[2 * t];//最小度数*2=最多的孩子个数
            this.keys = new int[2 * t - 1];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        //多路查找
        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            //执行到此时 keys[i] > key或 i==keyNumber
            // 如果是叶子节点，到这里的时候就是没找到
            if (leaf) {
                return null;
            }
            //非叶子节点，到这里时，需要找到对应的孩子结点
            return children[i].get(key);
        }

        //向指定索引处插入key
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        //向children 指定 索引处插入child
        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }
    }

    Node root;
    int t;//树中节点的最小度数
    final int MIN_KEY_NUMBER; //最小key的数目
    final int MAX_KEY_NUMBER;//最大key的数目

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    //1. 是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    //2. 新增
    /*
    首先查找本节点的插入位置i，如果没有空位（key被找到），应该走更新的逻辑，目前什么没做
    接下来分两种情况：
        如果节点是叶子节点，可以直接插入
        如果节点是非叶子节点，需要继续在children[i]中继续递归插入
    无论那种情况，插入完成后都可能超过节点keys的数目限制，此时应当执行节点分裂
     */
    public void put(int key) { //
        doPut(root, key, null, 0);
    }

    private void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return;//更新逻辑，目前设计没有value，所以不做任何操作
            }
            if (node.keys[i] > key) {
                break;//找到了插入位置，即为i；
            }
            i++;
        }
        if (node.leaf) {
            node.insertKey(key, i);
            // 达到上限，需要分裂
        } else {
            doPut(node.children[i], key, node, i);
            // 超过key的上限，需要分裂
        }
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    /*
    超过key的限制后分裂节点：
    创建right节点（分裂后大于当前left节点的），把t以后的key和child都拷贝过去
    中间处：t-1处的key插入到parent的index处，index指left作为孩子时的索引
    right节点作为parent的哈子插入到index+1处
     */
    private void split(Node node, Node parent, int index) {
        if (parent == null) {//分裂节点是根节点
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(node, 0);
            this.root = newRoot;
            parent = newRoot;
        }
        //创建right节点（分裂后大于当前left节点的），把t以后的key和child都拷贝过去
        Node right = new Node(t);
        right.leaf = node.leaf;
        System.arraycopy(node.keys, t, right.keys, 0, t - 1);
        if (!node.leaf) {
            System.arraycopy(node.children, t, right.children, 0, t);
        }
        right.keyNumber = t - 1;
        node.keyNumber = t - 1;
        //t-1处的key插入到parent的index处，index指left作为孩子时的索引
        int nodeMidKey = node.keys[t - 1];
        parent.insertKey(nodeMidKey, index);
        // right节点作为parent的哈子插入到index+1处
        parent.insertChild(right, index + 1);
    }
}

