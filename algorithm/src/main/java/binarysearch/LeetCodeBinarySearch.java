package binarysearch;

public class LeetCodeBinarySearch {

    public int search(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int a = leftMost(nums, target);
        if (a == -1) {
            return new int[]{-1, -1};
        }
        int b = rightMost(nums, target);
        return new int[]{a, b};
    }

    public int leftMost(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int candidate = -1;
        while (low <= high) {
            int m = (low + high) >>> 1;
            if (target < nums[m]) {
                high = m - 1;
            } else if (nums[m] < target) {
                low = m + 1;
            } else {
                candidate = m;
                high = m - 1;
            }
        }
        return candidate;
    }

    public int rightMost(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int candidate = -1;
        while (low <= high) {
            int m = (low + high) >>> 1;
            if (target < nums[m]) {
                high = m - 1;
            } else if (nums[m] < target) {
                low = m + 1;
            } else {
                candidate = m;
                low = m + 1;
            }
        }
        return candidate;
    }
}
