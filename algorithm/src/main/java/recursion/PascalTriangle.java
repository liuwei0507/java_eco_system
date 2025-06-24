package recursion;

/**
 * 杨辉三角
 */
public class PascalTriangle {
    private static int element(int i, int j) {
        if (i == j || j == 0) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);
    }

    public static void main(String[] args) {
        System.out.println(element(4, 2));
    }
}
