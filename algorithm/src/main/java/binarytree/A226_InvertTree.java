package binarytree;

public class A226_InvertTree {
    public static TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private static void fn(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
        fn(node.left);
        fn(node.right);
    }
}
