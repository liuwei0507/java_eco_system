package binarytree;

import java.util.LinkedList;
import java.util.List;

public class A144_PreOrderTraversal {

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
        System.out.println();
        order(root);
    }

    private static void preOrder(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = node;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.print(curr.val + "\t");
                stack.push(curr);//压入栈，记住来时的路
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
//                System.out.println("回：" + pop.val);
                curr = pop.right;
            }

        }

    }

    private static void inOrder(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = node;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
//                System.out.println(curr.val + "\t");
                stack.push(curr);//压入栈，记住来时的路
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.print(pop.val + "\t");
                curr = pop.right;
            }

        }
    }


    private static void postOrder(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;//最近一次弹栈的元素
        TreeNode curr = node;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);//压入栈，记住来时的路
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {//右边子树处理完成
                    pop = stack.pop();
                    System.out.print(pop.val + "\t");
                } else {
                    curr = peek.right;
                }
            }
        }
    }

    private static void order(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;//最近一次弹栈的元素
        TreeNode curr = node;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);//压入栈，记住来时的路
                //前序遍历打印
                System.out.print(curr.val + "\t");
                //待处理左子树
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null) {//右边子树处理完成
                    //中序遍历打印
                    System.out.print(peek.val + "\t");
                    pop = stack.pop();
                    //打印后续遍历
                    System.out.print(pop.val + "\t");

                }
                //右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                    //打印后续遍历
                    System.out.print(pop.val + "\t");
                }
                //待处理右子树
                else {
                    //中序遍历打印
                    System.out.print(peek.val + "\t");
                    curr = peek.right;
                }
            }
        }
    }

    private static List<Integer> preOrder_144(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;//最近一次弹栈的元素
        TreeNode curr = node;
        List<Integer> result = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);//压入栈，记住来时的路
                result.add(curr.val);
                //待处理左子树
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null) {//右边子树处理完成
                    pop = stack.pop();

                }
                //右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                }
                //待处理右子树
                else {
                    curr = peek.right;
                }
            }
        }
        return result;
    }

    private static List<Integer> inOrder_94(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;//最近一次弹栈的元素
        TreeNode curr = node;
        List<Integer> result = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);//压入栈，记住来时的路
                //待处理左子树
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null) {//右边子树处理完成
                    //中序遍历打印
                    result.add(peek.val);
                    pop = stack.pop();
                }
                //右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                }
                //待处理右子树
                else {
                    //中序遍历打印
                    result.add(peek.val);
                    curr = peek.right;
                }
            }
        }
        return result;
    }

    private static List<Integer> postOrder_145(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;//最近一次弹栈的元素
        TreeNode curr = node;
        List<Integer> result = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);//压入栈，记住来时的路
                //待处理左子树
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                //没有右子树
                if (peek.right == null) {//右边子树处理完成
                    pop = stack.pop();
                    //打印后续遍历
                    result.add(pop.val);

                }
                //右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                    //打印后续遍历
                    result.add(pop.val);
                }
                //待处理右子树
                else {
                    curr = peek.right;
                }
            }
        }
        return result;
    }
}
