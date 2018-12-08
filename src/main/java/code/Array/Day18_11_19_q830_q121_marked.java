package code.Array;

import org.omg.CORBA.INTERNAL;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/11/19
 */
public class Day18_11_19_q830_q121_marked {
    public static void main(String[] args) {
//        System.out.println(q830_solution("abcdddeeeeaabbbcd"));
//        System.out.println(q62_My(7, 3));
        System.out.println(q121_solution_2(new int[]{7, 1, 5, 3, 6, 4}));
    }

    //Input: "abcdddeeeeaabbbcd"
    //Output: [[3,5],[6,9],[12,14]]
    private static List<List<Integer>> q830_solution(String S) {
        List<List<Integer>> rst = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < S.length(); j++) {
            if (j == S.length() - 1 || S.charAt(j) != S.charAt(j + 1)) {
                if (j - i + 1 >= 3) {
                    rst.add(Arrays.asList(i, j));
                }
                i = j + 1;
            }
        }
        return rst;
    }

    private static int q62_My(int m, int n) {
        int[][] count = new int[n][m];
        count[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                process(j, i, count);
            }
        }
        return count[n - 1][m - 1];
    }

    private static void process(int col, int row, int[][] count) {
        if (col == 0 && row == 0) {
            return;
        }
        if (row == 0) {
            count[row][col] = count[row][col - 1];
            return;
        }
        if (col == 0) {
            count[row][col] = count[row - 1][col];
            return;
        }
        count[row][col] = count[row][col - 1] + count[row - 1][col];
    }

    private static int q121_solution_1(int[] prices) {
        if (prices.length <= 1) return 0;
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
    }

    // 这个解法参照官方solution写的, 意思很明显, 每次找一个谷底, 然后找到这个谷底下的最大收益
    // 当谷底更新后, 最大收益如果比之前的最大收益大则更新, 否则则是之前的。
    private static int q121_solution_2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                continue;
            }
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }
}
