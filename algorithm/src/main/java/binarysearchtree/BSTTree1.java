package binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树
 */
public class BSTTree1 {
    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    BSTNode root;//根节点

    /**
     * 查找key所对应的value
     *
     * @param key
     * @return
     */
    public Object get(int key) {
        return doGet(root, key);
    }

    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null;//没找到
        }
        if (key < node.key) {
            return doGet(node.left, key);// 向左找
        } else if (node.key < key) {
            return doGet(node.right, key);// 向右找
        } else {
            return node.value;//找到了
        }
    }

    /**
     * 非递归方式查找
     *
     * @param key
     * @return
     */
    public Object get_2(int key) {
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     * 获取最小的key所对应的value
     *
     * @return
     */
    public Object min() {
        return doMin(root);
    }

    private Object doMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }

    public Object min_2() {
        return min(root);
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    /**
     * 获取最大的key所对应的value
     *
     * @return
     */
    public Object max() {
        return max(root);
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    /**
     * 插入key所对应的value
     *
     * @param key
     * @param value
     */
    public void put(int key, Object value) {
        //1， key存在，更新

        //2， key不存在，新增
        BSTNode node = root; // root
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right; // 一直向右找，直到找到null
            } else {
                //key已存在，更新
                node.value = value;
                return;
            }
        }
        //key不存在，新增， 找到最后没找到，则parent时最后一个节点
        BSTNode newNode = new BSTNode(key, value);
        if (parent == null) {
            root = newNode;
            return;
        }
        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    /**
     * 查找key对应的前驱值
     *
     * @param key
     * @return
     */
    public Object predecessor(int key) {
        BSTNode node = root;
        BSTNode ancestorFromLeft = null;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                ancestorFromLeft = node;
                node = node.right;
            } else {
                //找到了，则退出
                break;
            }
        }
        if (node == null) {
            // 如果没有找到节点，则前任也为null
            return null;
        }
        /**
         * 情况1: 节点有左子树，此时，前任就是左子树的最大值
         * 情况2: 节点没有左子树，若离他最近的，自左而来的祖先就是前任
         */
        if (node.left != null) {
            return max(node.left);
        }
        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    /**
     * 查找key对应后继值
     *
     * @param key
     * @return
     */
    public Object successor(int key) {
        BSTNode node = root;
        BSTNode ancestorFromRight = null;
        while (node != null) {
            if (key < node.key) {
                ancestorFromRight = node;
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                //找到了，则退出
                break;
            }
        }
        if (node == null) {
            // 如果没有找到节点，则前任也为null
            return null;
        }
        /**
         * 情况1: 节点有右子树，此时，前任就是右子树的最小值
         * 情况2: 节点没有右子树，若离他最近的，自右而来的祖先就是前任
         */
        if (node.right != null) {
            return min(node.right);
        }
        return ancestorFromRight != null ? ancestorFromRight.value : null;
    }

    /**
     * 删除key所对应的节点
     *
     * @param key
     */
    public Object delete(int key) {
        /**
         * 1，删除的节点没有左孩子，将右孩子托孤给parent
         * 2，删除的节点没有右孩子，将左孩子托孤给parent
         * 3， 删除节点的左右孩子都没有，涵盖在第一和第二中情况下，将null托孤给parent
         * 4， 删除的节点左右节点都存在
         *      可以将他的后继节点（S）托孤给parent，再称S的父亲为SP，
         *      1）  SP就是被删除的节点，此时D与S紧邻，只需将S托孤给Parent
         *      2）SP不是被删除的节点，此时D与S不相邻，此时只需要将S的后代托孤给SP，再将S托孤给parent
         *
         */
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            if (key < node.key) {
                parent = node;
                node = node.left;
            } else if (key > node.key) {
                parent = node;
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            return null;
        }
        //找到了，执行删除操作
        if (node.left == null) {
            shift(parent, node, node.right);
        } else if (node.right == null) {
            shift(parent, node, node.left);
        } else {
            // 被删除的节点都右左右孩子
            // 4.1 被删除  节点找后继
            BSTNode s = node.right;
            BSTNode sParent = node;// 后继节点的父亲
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            //后继节点即为s
            if (sParent != node) {// 不相邻
                //4.2处理后继节点的后事
                shift(sParent, s, s.right);
                s.right = node.right;
            }

            //4.3后继节点取代被删除节点
            shift(parent, node, s);
            s.left = node.left;
        }
        return node.value;
    }

    /**
     * 托孤方法
     *
     * @param parent  被删除节点的父亲
     * @param deleted 被删除节点
     * @param child   被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }

    }


    // 二叉搜索树的中序遍历就是排序好的数组

    /**
     * 找到小于key的所有value
     *
     * @param key
     * @return
     */
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                //先向左走到头
                stack.push(p);
                p = p.left;
            } else {
                //向左走到头之后，往回走，弹出栈中的元素
                BSTNode pop = stack.pop();
                //处理值，中序遍历的结果
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    /**
     * 找到大于key的所有value
     *
     * @param key
     * @return
     */
    public List<Object> greater(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                //先向左走到头
                stack.push(p);
                p = p.left;
            } else {
                //向左走到头之后，往回走，弹出栈中的元素
                BSTNode pop = stack.pop();
                //处理值
                if (pop.key > key) {
                    result.add(pop.value);
                }
                p = pop.right;
            }
        }
        return result;
    }

    /**
     * 找到 >=key1 and <=key2 的所有value
     *
     * @param key1
     * @param key2
     * @return
     */
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                //先向左走到头
                stack.push(p);
                p = p.left;
            } else {
                //向左走到头之后，往回走，弹出栈中的元素
                BSTNode pop = stack.pop();
                //处理值
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }
}
