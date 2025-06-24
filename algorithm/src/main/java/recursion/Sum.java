package recursion;

/**
 * 递归求和 n + n-1 + n-2 + ... + 1 + 0
 */
public class Sum {

    // f(n) = f(n-1) + n

    public static long sum(int n) {
        if (n == 1) {
            return 1;
        }
        return sum(n - 1) + n;
    }

    public static void main(String[] args) {
        System.out.println(sum(30000));
    }
}
