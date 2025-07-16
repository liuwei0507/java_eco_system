package avltree;

/**
 * AVL树
 * 二叉搜索树在插入和删除时，节点可能失衡
 * 如果在插入和删除时通过旋转，始终让二叉搜索树保持平衡，称为自平衡的二叉搜索树
 * AVL是自平衡二叉搜索树的实现之一
 */
public class AVLTree {
    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1;

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 求节点的高度
     *
     * @param node
     * @return
     */
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 更新节点的高度（新增，插入，删除）
     *
     * @param node
     */

    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 平衡因子（balance factor) = 左子树的高度 - 右子树的高度
     *
     * @param node
     * @return
     */
    private int balanceFactor(AVLNode node) {
        return height(node.left) - height(node.right);
    }

    // 0 1 -1 平衡的
    //>1 <-1不平衡的

    /**
     * LL
     * -- 失衡节点的bf>1，即左边更高
     * -- 失衡节点的左孩子的bf>=0，即左孩子的这边也是左边更高或等高
     * LR
     * -- 失衡节点的bf>1，即左边更高
     * -- 失衡节点的左孩子的bf<0，即左孩子的这边是右边更高或等高
     * RL
     * -- 失衡节点的bf<0，即右边更高
     * -- 失衡节点的右孩子的bf>=0，即右孩子的这边也是左边更高或等高
     * RR
     * -- 失衡节点的bf<0，即右边更高
     * -- 失衡节点的右孩子的bf<0，即右孩子的这边也是左边更高或等高
     */

    /**
     * @param node 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode rightRotate(AVLNode node) {
        // 先找到左节点
        AVLNode left = node.left;
        // 左节点的右孩子
        AVLNode leftRight = left.right;
        // 将左节点的右孩子赋值给要旋转的节点
        left.right = node;// 上位
        //要旋转的节点的右孩子赋值非左节点的右孩子
        node.left = leftRight;// 换爹
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    /**
     * @param node 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode leftRotate(AVLNode node) {
        // 先找到左节点
        AVLNode right = node.right;
        // 左节点的右孩子
        AVLNode rightLeft = right.left;
        //要旋转的节点的右孩子赋值非左节点的右孩子
        right.left = node;// 上位
        // 将左节点的右孩子赋值给要旋转的节点
        node.right = rightLeft;// 换爹
        updateHeight(node);
        updateHeight(right);
        return right;
    }

    // 先左旋左子树，再右旋根节点
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 先右旋右子树，再左旋根节点
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // 检查节点是否失衡，重新平衡代码
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = balanceFactor(node);
        if (bf > 1 && balanceFactor(node.left) >= 0) { // LL
            return rightRotate(node);
        } else if (bf > 1 && balanceFactor(node.left) < 0) { // LR
            return leftRightRotate(node);
        } else if (bf < -1 && balanceFactor(node.right) > 0) {// RL
            return rightLeftRotate(node);
        } else if (bf < -1 && balanceFactor(node.right) <= 0) {// RR
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;

    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        // 1 找到空位，创建新节点
        if (node == null) {
            return new AVLNode(key, value);
        }
        // 2 key以及存在，更新
        if (key == node.key) {
            node.value = value;
            return node;
        }
        // 3 继续查找
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        // 更新节点高度
        updateHeight(node);
        //重新调整平衡树高度
        return balance(node);
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        // node == null
        if (root == null) {
            return null;
        }
        // 没找到key
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else {
            //找到key 1) 没有孩子， 2）只有一个孩子，3） 有两个孩子
            // 找到key
            if (node.left == null && node.right == null) {
                // 没有孩子
                return null;
            } else if (node.left == null) {
                // 只有右孩子
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                // s 是后继节点
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        // 更新高度
        updateHeight(node);
        //balance
        return balance(node);
    }
}
