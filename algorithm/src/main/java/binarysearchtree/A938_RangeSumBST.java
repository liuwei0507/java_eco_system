package binarysearchtree;

import java.util.LinkedList;

/**
 * 二叉搜索树的范围和
 */
public class A938_RangeSumBST {
    /**
     * 中序遍历非递归方式
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST_1(TreeNode root, int low, int high) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (pop.val > high) {
                    break;
                }
                if (pop.val >= low) {
                    sum += pop.val;
                }
                p = pop.right;
            }
        }
        return sum;
    }

    /**
     * 上下限递归
     *
     * @param node
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        if (node.val < low) {
            return rangeSumBST(node.right, low, high);
        }
        if (node.val > high) {
            return rangeSumBST(node.left, low, high);
        }
        //在范围内
        return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
    }
}
