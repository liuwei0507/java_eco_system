package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class A111_MinDepth {
    public static int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        if (d2 == 0) { // 当右子树为空时，返回左子树深度+1
            return d1 + 1;
        }
        if (d1 == 0) {
            return d2 + 1;
        }
        return Integer.min(d1, d2) + 1;
    }

    /**
     * 使用层序遍历的方式，第一个叶子节点所在的层就是最小深度
     *
     * @param node
     * @return
     */
    public static int minDepth_1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left == null && poll.right == null) {
                    return depth;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return depth;
    }
}
