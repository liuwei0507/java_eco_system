package recursion;

import java.util.Arrays;

/**
 * 递归求解斐波拉契数列
 * f(n)=0 n=0
 * f(n)=1 n=1
 * f(n)=f(n-1)+f(n-2) n>1
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(f(40));
//        System.out.println(fibonacci(40));
    }

    private static int f(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int x = f(n - 1);
        int y = f(n - 2);
        return x + y;
    }

    /**
     * 优化后的斐波拉契数列
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);// [-1,-1,-1];
        cache[0] = 0;
        cache[1] = 1;
        return f2(n, cache);
    }

    private static int f2(int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }

        int x = f(n - 1);
        int y = f(n - 2);
        cache[n] = x + y;
        return cache[n];
    }
}
