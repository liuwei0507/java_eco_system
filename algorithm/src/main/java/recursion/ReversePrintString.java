package recursion;

public class ReversePrintString {
    public static void main(String[] args) {
        String str = "abcdefg";
        reversePrint(0, str);
    }

    private static void reversePrint(int n, String str) {
        if (n == str.length()) {
            return;
        }
        reversePrint(n + 1, str);
        System.out.println(str.charAt(n));
    }
}
