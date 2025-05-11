package binarysearch;

public class BinarySearch {
    public static void main(String[] args) {

    }

    /**
     * 二分查找基础版
     *
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchBasic(int[] a, int target) {
        int i = 0, j = a.length - 1; // 设置指针和初始值
        while (i <= j) { //指针范围内有东西
//            int m = (i + j) / 2;
            int m = (i + j) >>> 1;
            if (target < a[m]) { //目标值在左边
                j = m - 1;
            } else if (a[m] < target) {//目标值在右边
                i = m + 1;
            } else {
                return m;
            }

        }
        return -1;
    }

    public static int binarySearchAlternative(int[] a, int target) {
        int i = 0, j = a.length;
        while (i < j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else if (target > a[m]) {
                i = m + 1;
            } else {
                return m;
            }

        }
        return -1;
    }

    /**
     * 二分查找平衡版
     *
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchBalance(int[] a, int target) {
        int i = 0, j = a.length;
        while (1 < j - i) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        if (a[i] == target) {
            return i;
        } else {
            return -1;
        }
    }

    /**
     * 二分查找有重复元素，返回最左边的位置
     *
     * @param a
     * @param target
     * @return 找到返回最靠左侧索引，找不到返回-1
     */
    public static int binarySearchLeftmost1(int[] a, int target) {
        int i = 0, j = a.length - 1; // 设置指针和初始值
        int candidate = -1;
        while (i <= j) { //指针范围内有东西
//            int m = (i + j) / 2;
            int m = (i + j) >>> 1;
            if (target < a[m]) { //目标值在左边
                j = m - 1;
            } else if (a[m] < target) {//目标值在右边
                i = m + 1;
            } else {
                // 记录找到的候选者
                candidate = m;
                j = m - 1;
            }

        }
        return candidate;
    }

    /**
     * 二分查找有重复元素，返回最右边的位置
     *
     * @param a
     * @param target
     * @return 找到返回最靠右侧索引，找不到返回-1
     */
    public static int binarySearchRightmost1(int[] a, int target) {
        int i = 0, j = a.length - 1; // 设置指针和初始值
        int candidate = -1;
        while (i <= j) { //指针范围内有东西
//            int m = (i + j) / 2;
            int m = (i + j) >>> 1;
            if (target < a[m]) { //目标值在左边
                j = m - 1;
            } else if (a[m] < target) {//目标值在右边
                i = m + 1;
            } else {
                // 记录找到的候选者
                candidate = m;
                i = m + 1;
            }

        }
        return candidate;
    }

    /**
     * 二分查找有重复元素，返回最左边的位置
     *
     * @param a
     * @param target
     * @return >= target的目标索引的位置
     */
    public static int binarySearchLeftmost2(int[] a, int target) {
        int i = 0, j = a.length - 1; // 设置指针和初始值
        while (i <= j) { //指针范围内有东西
//            int m = (i + j) / 2;
            int m = (i + j) >>> 1;
            if (target <= a[m]) { //目标值在左边
                j = m - 1;
            } else {//目标值在右边
                i = m + 1;
            }
        }
        return i;
    }


    /**
     * 二分查找有重复元素，返回最右边的位置
     *
     * @param a
     * @param target
     * @return 找到返回<=目标，最靠右的位置
     */
    public static int binarySearchRightmost2(int[] a, int target) {
        int i = 0, j = a.length - 1; // 设置指针和初始值
        int candidate = -1;
        while (i <= j) { //指针范围内有东西
            int m = (i + j) >>> 1;
            if (target < a[m]) { //目标值在左边
                j = m - 1;
            } else {//目标值在右边
                i = m + 1;
            }
        }
        return i - 1;
    }

}
