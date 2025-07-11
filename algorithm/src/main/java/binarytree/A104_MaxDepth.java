package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class A104_MaxDepth {
    /**
     * 104. 二叉树的最大深度
     * 1， 得到左子树的深度，得到右子树的深度，二者较大者加一，就是节点最大深度
     * 2，因为需要先得到左右子树深度，后续遍历的典型应用
     * 3， 关于深度的定义： 从根出发，离根最远的节点总边树
     *
     * @param node
     * @return
     */
    public static int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        return Integer.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 使用后序遍历方式
     *
     * @param node
     * @return
     */
    public static int maxDepth_1(TreeNode node) {
        TreeNode curr = node;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int depth = 0;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                depth = Math.max(depth, stack.size());
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return depth;
    }

    /**
     * 使用层序遍历方式
     *
     * @param node
     * @return
     */
    public static int maxDepth_2(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 0;
//        int c1 = 1;//每层节点的数量
        while (!queue.isEmpty()) {
//            int c2 = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
//                    c2++;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
//                    c2++;
                }
            }
//            c1 = c2;
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {

    }

}
