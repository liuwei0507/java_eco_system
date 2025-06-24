package recursion;

import java.util.Arrays;

/**
 * 递归冒泡排序
 */
public class BubbleSort {


    /**
     * @param a
     * @param j 代表未排序区域的右边界
     */
    private static void bubbleSort(int[] a, int j) {
        if (j == 0) {
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
                x = i;
            }
        }
        bubbleSort(a, x);
    }

    public static void main(String[] args) {
        int[] a = {5, 4, 3, -1, 0, 2, 1};
        System.out.println(Arrays.toString(a));
        bubbleSort(a, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
