package recursion;

import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] a) {
        insertion(a, 1);
    }

    private static void insertion(int[] a, int low) {
        if (low == a.length) {
            return;
        }
        int tmp = a[low];
        int i = low - 1;//已经排序区域指针
        while (i >= 0 && a[i] > tmp) { // 没有找到插入位置
            a[i + 1] = a[i];
            i--;
        }
        // 找到插入位置
        if (i + 1 != low) {
            a[i + 1] = tmp;
        }
        insertion(a, low + 1);
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1, 0, -2};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
