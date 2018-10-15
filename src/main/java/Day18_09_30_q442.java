import java.util.*;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/9/30
 */
public class Day18_09_30_q442 {
    public static void main(String[] args) {
        int[][] a = new int[0][4];
        System.out.println(a[0].length);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 我的解法
    public static boolean q766_My(int[][] matrix) {
        int[] before = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            if (! perLine(matrix[i], before))
                return false;
        }
        return true;
    }

    private static boolean perLine(int[] line, int[] before) {
        for (int i = 0; i < line.length - 1; i++) {
            if (before[i] != line[i + 1])
                return false;
        }
        System.arraycopy(line, 0, before, 0, line.length);
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // q766 官方解法 1:
    // 将 col - row = 列 - 行 作为 Map 的键, 遍历一遍数组, 进行检查即可
    public static boolean q766_solutions_1(int[][] matrix) {
        int col = matrix[0].length;
        int row = matrix.length;
        Map<Integer, Integer> regions = new HashMap<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int key = j - i;
                if (regions.containsKey(key)) {
                    if (regions.get(key) != matrix[i][j])
                        return false;
                }else
                    regions.put(key, matrix[i][j]);
            }
        }
        return true;
    }

    // q766 官方解法 2:
    // 满足题目要求则必须有 A[i][j] == A[i - 1][j - 1] 而且第一行和第一列的是不存在左上角元素的, 要排除掉
    public static boolean q766_solutions_2(int[][] matrix) {
        int col = matrix[0].length;
        int row = matrix.length;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1])
                    return false;
            }
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 这一题没想出来 -^-, 看了别人的, 使用值作为索引, 再用负数作为标记, 很巧妙~
    public static List<Integer> q442_My(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int value = Math.abs(nums[i]);
            if (nums[value - 1] < 0) {
                rst.add(value);
            }else {
                nums[value - 1] = - nums[value - 1];
            }
        }
        return rst;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // 自己写的有 bug ... 前面一部分有bug, 但是官方的第三种方法也就是我这种方法
    public static int[][] q566_My(int[][] nums, int r, int c) {
//        int row = nums.length;
//        int col = nums[0].length;// 这里存在 num 行为 0 的情况，那么 num[0].length 就会超界限, 然后这行就会抛出异常...
//        if (nums.length == 0 || row * col != r * c) {
//            return nums;
//        }

        if (nums.length == 0 || nums.length * nums[0].length != r * c)
            return nums;
        int row = nums.length;
        int col = nums[0].length;
        int[][] rst = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int idx = i  * c + j;
                rst[i][j] = nums[idx / col][idx % col];
            }
        }
        return rst;
    }

    // 使用队列, 很直观的过程, 一行行入队列, 再一行行出队列
    public static int[][] q566_solutions_1(int[][] nums, int r, int c) {
        if (nums.length == 0 || nums.length * nums[0].length != r * c)
            return nums;
        int row = nums.length;
        int col = nums[0].length;
        int[][] rst = new int[r][c];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                queue.add(nums[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                rst[i][j] = queue.remove();
            }
        }
        return rst;
    }

    // 也很简单的一个过程, 在遍历一个矩阵的同时加入两个指针, 用于标志第二个矩阵的界限
    public static int[][] q566_solutions_2(int[][] nums, int r, int c) {
        if (nums.length == 0 || nums.length * nums[0].length != r * c)
            return nums;
        int row = nums.length;
        int col = nums[0].length;
        int[][] rst = new int[r][c];
        int rr = 0, cc = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rst[rr][cc] = nums[i][j];
                cc++;
                if (cc == c) {
                    rr++;
                    cc = 0;
                }
            }
        }
        return rst;
    }

    // -----------------------------------------------------------------------------------------------------------------
}
