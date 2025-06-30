package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的Z字层序遍历
 */
public class A103_ZigZagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int c1 = 1;// 当前层节点数
        boolean odd = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int c2 = 0;// 下一层节点数
            for (int i = 0; i < c1; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    level.add(null);
                    continue;
                }
                if (odd) {
                    level.offerFirst(node.val);
                } else {
                    level.offerLast(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                    c2++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    c2++;
                }
            }
            odd = !odd;
            result.add(level);
            c1 = c2;
        }
        return result;
    }
}
