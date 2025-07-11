package binarytree;

public class A101_IsSymmetric {
    public static boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root.left, root.right);
    }

    private static boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        // right left 不能同时为null
        if (right == null || left == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
    }
}
