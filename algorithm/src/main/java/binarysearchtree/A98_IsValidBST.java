package binarysearchtree;

import java.util.LinkedList;

/**
 * 判断是合法的二叉搜索树
 * 利用二叉搜索树中序遍历的特性
 */
public class A98_IsValidBST {
    public boolean isValidBST_1(TreeNode root) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;//代表上一个值
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                //处理值
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

    /**
     * 递归方式实现
     *
     * @param node
     * @return
     */
    long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean a = isValidBST(node.left);
        if (!a) {
            return false;
        }
        if (prev >= node.val) {
            return false;
        }
        prev = node.val;
        return isValidBST(node.right);
    }

}
