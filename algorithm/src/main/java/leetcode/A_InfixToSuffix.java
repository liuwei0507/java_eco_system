package leetcode;

import java.util.LinkedList;

/**
 * 中缀表达式转换为后缀表达式
 */
public class A_InfixToSuffix {
    /**
     * @param infix
     * @return 1 遇到非运算符，直接拼串
     * 2 遇到运算符，
     * 比较运算符的优先级，如果栈顶运算符优先级高，则将栈顶运算符弹出并拼串，
     * 否则将运算符压栈
     * 3 遇到括号，如果左括号，则压栈
     * 否则，弹出栈顶运算符并拼串，直到遇到左括号
     * 4 遍历结束后，将栈中剩余运算符弹出并拼串
     * <p>
     * <p>
     * 带括号 ()
     * 1 遇到左括号，则压栈,左括号优先级设置为0，最低
     * 2 遇到右括号，
     */
    public String infixToSuffix(String infix) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            switch (c) {
                case '*', '/', '+', '-' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        if (priority(c) > priority(stack.peek())) {
                            stack.push(c);
                        } else {
                            while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                                sb.append(stack.pop());
                            }
                            stack.push(c);
                        }
                    }
                }
                case '(' -> {
                    stack.push(c);
                }
                case ')' -> {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();// 弹出左括号
                }
                default -> {
                    sb.append(c);
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    static int priority(char op) {
        return switch (op) {
            case '(' -> 0;
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

    public static void main(String[] args) {
        System.out.println(new A_InfixToSuffix().infixToSuffix("a+b"));
        System.out.println(new A_InfixToSuffix().infixToSuffix("a+b-c"));
        System.out.println(new A_InfixToSuffix().infixToSuffix("a*b+c"));
        System.out.println(new A_InfixToSuffix().infixToSuffix("a+b*c"));
        System.out.println(new A_InfixToSuffix().infixToSuffix("a+b*c-d"));
        System.out.println(new A_InfixToSuffix().infixToSuffix("(a+b)*c")); // ab+c*
        System.out.println(new A_InfixToSuffix().infixToSuffix("(a+b*c-d)*e"));// abc*+d-e*
        System.out.println(new A_InfixToSuffix().infixToSuffix("a*(b+c)"));// abc+*
    }
}
