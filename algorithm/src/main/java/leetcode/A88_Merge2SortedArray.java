package leetcode;

import java.util.Arrays;

/**
 * 合并两个有序数组
 */
public class A88_Merge2SortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] a2 = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i <= m - 1 && j <= n - 1) {
            if (nums1[i] < nums2[j]) {
                a2[k] = nums1[i];
                i++;
            } else {
                a2[k] = nums2[j];
                j++;
            }
            k++;
        }
        if (i > m - 1) {
            System.arraycopy(nums2, j, a2, k, n - j);
        }
        if (j > n - 1) {
            System.arraycopy(nums1, i, a2, k, m - i);
        }
        System.arraycopy(a2, 0, nums1, 0, m + n);
    }


    /**
     * 递归方式合并两个有序数组
     *
     * @param a1   原始数组
     * @param i    第一个有序区间的起点
     * @param iEnd 第一个有序区间的终点
     * @param j    第二个有序区间的起点
     * @param jEnd 第二个有序区间的终点
     * @param a2   结果数组
     * @param k    目标数组的索引位置
     */
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2, int k) {

        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
            return;
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
            return;
        }
        if (a1[i] < a1[j]) {
            a2[k] = a1[i];
            merge(a1, i + 1, iEnd, j, jEnd, a2, k + 1);
        } else {
            a2[k] = a1[j];
            merge(a1, i, iEnd, j + 1, jEnd, a2, k + 1);
        }
    }

    /**
     * 非递归方式合并两个有序区间
     *
     * @param a1
     * @param i
     * @param iEnd
     * @param j
     * @param jEnd
     * @param a2
     */
    public static void merge1(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = 0;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 5, 7, 10, 11};
        int[] a2 = new int[a1.length];
//        merge(a1, 0, 8, 9, 12, a2, 0);
        merge1(a1, 0, 8, 9, 12, a2);
        System.out.println(Arrays.toString(a2));
    }
}
