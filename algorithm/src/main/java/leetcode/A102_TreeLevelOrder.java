package leetcode;

import queue.LinkedListQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层序遍历
 */
public class A102_TreeLevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int c1 = 1;// 当前层节点数
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int c2 = 0;// 下一层节点数
            for (int i = 0; i < c1; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    level.add(null);
                    continue;
                }
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                    c2++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    c2++;
                }
            }
            result.add(level);
            c1 = c2;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(4),
                        2,
                        new TreeNode(5)
                ),
                1,
                new TreeNode(
                        new TreeNode(6),
                        3,
                        new TreeNode(7)
                )
        );
        new A102_TreeLevelOrder().levelOrder(root);
//        base_tree_level_order(root);
    }

    private static void base_tree_level_order(TreeNode root) {
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1;// 当前层节点数
        while (!queue.isEmpty()) {
            int c2 = 0;// 下一层节点数
            for (int i = 0; i < c1; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    queue.offer(node.left);
                    c2++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    c2++;
                }
            }
            System.out.println();
            c1 = c2;
        }
    }
}
