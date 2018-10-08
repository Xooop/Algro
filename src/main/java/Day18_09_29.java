import java.util.Arrays;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/9/29
 */
public class Day18_09_29 {
    public static void main(String[] args) {

    }

    //------------------------------------------------------------------------------------------------------------------

    // 这题应该是很简单的, 但我写的还是很冗长...233
    // 因为我没有考虑到 c_len + 1 / 2 这样就一定可以遍历 一半 + (可能有:总数量为单数时中间单出来的那一个)
    public static int[][] q832_My(int[][] A) {
        int c_len = A[0].length;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < c_len / 2; j++) {
                swap(A[i], j, c_len - 1 - j);
            }
            for (int k = 0; k < c_len; k++) {
                A[i][k] = (A[i][k] + 1) % 2;
            }
        }
        return A;
    }

    private static void swap(int[] a, int i , int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // 官方解答
    // 注意两个点
    // 1. (c_len + 1) / 2 这样就可以遍历 一半 + (可能有:总数量为单数时中间单出来的那一个)
    // 2. 取反 a ^ 1;
    public static int[][] q832_Solution(int[][] A) {
        int c_len = A[0].length;
        for (int[] line : A) {
            for (int i = 0; i < (c_len + 1) / 2; i++) {
                int tmp = line[i] ^ 1;
                line[i] = line[c_len - 1 - i] ^ 1;
                line[c_len - 1 - i] = tmp;
            }
        }
        return A;
    }

    //------------------------------------------------------------------------------------------------------------------

    // 我考虑的方法是假设跟最小的一组的不是第二小的, 然后论证这个一定比是第二小的要小推出来的
    public static int q561_My(int[] nums) {
        int[] tmp = nums.clone();
        int sum = 0;
        Arrays.sort(tmp);
        for (int i = 0; i < nums.length; i += 2) {
            sum += tmp[i];
        }
        return sum;
    }

    //------------------------------------------------------------------------------------------------------------------
    // i == j 的情况跳不跳过都无所谓
    public static int[][] transpose(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        int[][] matrix = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == j) {
                    matrix[i][j] = A[i][j];
                    continue;
                }
                matrix[j][i] = A[i][j];
            }
        }
        return matrix;
    }


}
