package leetcode;

import java.util.LinkedList;

public class A150_EvalRPN {

    /**
     * @param tokens
     * @return ["2","1","+","3","*"]
     * <p>
     * 思路： 遇到数字压栈，遇到运算符出栈顶两个数字，计算结果压栈，重复此步骤，直到所有数字计算完毕
     */
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a + b);
                }
                case "-" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a / b);
                }
                default -> { // 数字压栈
                    stack.push(Integer.parseInt(token));
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        System.out.println(new A150_EvalRPN().evalRPN(tokens));
        String[] tokens1 = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(new A150_EvalRPN().evalRPN(tokens1));
    }
}
