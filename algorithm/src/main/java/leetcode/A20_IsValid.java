package leetcode;

import stack.ArrayStack;

/**
 * 有效的括号
 */
public class A20_IsValid {
    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (!stack.isEmpty() && c == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        A20_IsValid a20 = new A20_IsValid();
        System.out.println(a20.isValid("([{}])"));
        System.out.println(a20.isValid("()[]{}"));
        System.out.println(a20.isValid("()"));
        System.out.println("------------");
        System.out.println(a20.isValid("(]"));
        System.out.println(a20.isValid("([)]"));
        System.out.println(a20.isValid("([]"));
        System.out.println(a20.isValid("("));
        System.out.println(a20.isValid("--------------"));
        System.out.println(a20.isValid(")("));
        System.out.println(a20.isValid("]"));
    }
}
