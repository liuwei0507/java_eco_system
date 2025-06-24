package recursion;

import java.util.Arrays;

/**
 * 递归方式二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = binarySearch(arr, 11);
        System.out.println(index);
    }

    public static int binarySearch(int[] a, int target) {
        return f(a, target, 0, a.length - 1);
    }

    private static int f(int[] a, int target, int i, int j) {
        if (i > j) {
            return -1;
        }
        int m = (i + j) >> 1;
        if (target < a[m]) {
            return f(a, target, i, m - 1);
        } else if (a[m] < target) {
            return f(a, target, m + 1, j);
        } else {
            return m;
        }
    }
}
