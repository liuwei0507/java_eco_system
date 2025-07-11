package binarytree;

public class TreeTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(4), 2, null
                ),
                1,
                new TreeNode(
                        new TreeNode(5),
                        3,
                        new TreeNode(6)
                )
        );
        preOrder(root);//1	2	4	3	5	6
        System.out.println();
        inOrder(root); //4	2	1	5	3	6
        System.out.println();
        postOrder(root);//4	2	5	6	3	1
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + "\t");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + "\t");
    }
}
