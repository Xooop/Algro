package code;

import java.util.*;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/9/26
 */
public class Day18_09_q442_marked {

    public static void main(String[] args) {
        Arrays.stream(
                q905_Solutions(new int[]{3, 1, 2, 4})
        ).forEach(System.out::println);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static int[] q905_My(int[] A) {
        int len = A.length;
        int[] output = new int[len];
        for (int p = 0, i = 0, j = len - 1; p < len; p++) {
            if (A[p] % 2 == 0) {
                output[i] = A[p];
                i++;
            } else {
                output[j] = A[p];
                j--;
            }
            if (i == j) {
                output[i] = A[p + 1];
                break;
            }
        }
        return output;
    }
    // -----------------------------------------------------------------------------------------------------------------
    private static int[] q905_Solutions(int[] A) {
        // 1: 将 i 用 i % 2 表示, 再用数组的排序去排序, 所以时间复杂度是 N log(N)
//        return Arrays.stream(A).boxed()
//                .sorted(Comparator.comparingInt(i -> i % 2))
//                .mapToInt(Integer::intValue)
//                .toArray();

        // 2: 两个指针, 一个 p 指向开头, 一个 q 指向最后, 两个数使用 i -> i % 2 表示, 那么就有以下 4 种形式:
        // (1, 0) 俩数需要交换, p 后移, q 前移
        // (0, 1) 俩数位置正确, p 后移, q 前移
        // (0, 0) 前面一个数位置正确, p 后移, q 不变
        // (1, 1) 后面一个数位置正确, p 不变, q 前移
        int p = 0, q = A.length - 1;
        while (p < q) {
//            我的版本很冗长...233
////            if (A[p] % 2 == 1) {
////                if (A[q] % 2 == 0) {
////                    int tmp = A[p];
////                    A[p] = A[q];
////                    A[q] = tmp;
////                    p++;
////                    q--;
////                }else {
////                    q--;
////                }
////            }else{
////                if (A[q] % 2 == 1) {
////                    p++;
////                    q--;
////                }else {
////                    p++;
////                }
////            }
            /*官方版本, 十分简洁...*/
            if (A[p] % 2 > A[q] % 2) {
                int tmp = A[p];
                A[p] = A[q];
                A[q] = tmp;
            }
            if (A[p] % 2 == 0) p++;
            if (A[q] % 2 == 1) q--;
        }
        return A;
    }
    // -----------------------------------------------------------------------------------------------------------------
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

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // -----------------------------------------------------------------------------------------------------------------
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
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // 我的解法
    public static boolean q766_My(int[][] matrix) {
        int[] before = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            if (!perLine(matrix[i], before))
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
                } else
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
    // 这一题没想出来 -^-, 看了别人的, 使用值作为索引, 再将他 * (-1) 作为标记, 很巧妙~
    public static List<Integer> q442_My(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int value = Math.abs(nums[i]);
            if (nums[value - 1] < 0) {
                rst.add(value);
            } else {
                nums[value - 1] = -nums[value - 1];
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
                int idx = i * c + j;
                rst[i][j] = nums[idx / col][idx % col];
            }
        }
        return rst;
    }

    // -----------------------------------------------------------------------------------------------------------------
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

    // -----------------------------------------------------------------------------------------------------------------
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
