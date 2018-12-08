package code.Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day18_11_29_q39_marked {
    public static void main(String[] args) {
//        int[][] test = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
//        q48_My(test);
//        for (int i = 0; i < test.length; i++) {
//            for (int j = 0; j < test[0].length; j++) {
//                System.out.print("" + test[i][j] + " ");
//            }
//            System.out.println();
//        }
        //int[] test = new int[]{2, 3, 6, 7};
        //System.out.println(q39_My(test, 7));
        String test = "0101110";
        System.out.println(q926_My(test));
    }

    private static void q48_My(int[][] matrix) {
        for (int i = 1; i <= matrix.length / 2; i++) {
            int[] tmp;
            int head = 0 + i - 1;
            int tail = matrix.length - 1 - (i - 1);
            tmp = matrix[head];
            matrix[head] = matrix[tail];
            matrix[tail] = tmp;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int tmp;
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    private static int[] q922_My(int[] A) {
        int[] rst = new int[A.length];
        int evenIdx = 0;
        int oddIdx = 1;
        for (int i : A) {
            if (i % 2 == 0) {
                rst[evenIdx] = i;
                evenIdx += 2;
            } else {
                rst[oddIdx] = i;
                oddIdx += 2;
            }
        }
        return rst;
    }

    // 参照了别人的解法，因为我没想出来怎么避免重复这种情况出现...
    // 别人做法的思路其实是让每个结果有序，即
    //                 1                2             3
    //           1     2     3       2     3          3
    //         1 2 3  2 3    3
    //                           ...
    // 可以看到输出的结果都是有序的, 也可以说是从小到大
    // 实现这种结果的算法，就是当遍历过当前位置之后，只能从挑当前位置及其之后的元素。
    private static List<List<Integer>> q39_My(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();
        process(rst, new ArrayList<>(), candidates, target, 0);
        return rst;
    }

    private static void process(List<List<Integer>> rst, List<Integer> one, int[] candidates, int target, int start) {
        if (target == 0) {
            rst.add(one);
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            List<Integer> copy = new ArrayList<>(one);
            copy.add(candidates[i]);
            process(rst, copy, candidates, target - candidates[i], i);
        }
    }

    // 诶... 自己做的不对，而且还遗漏一个条件吧..
    private static int q926_My(String S) {
        return 0;
    }

    // 官方解法就很机智了: 叫Prefix Sum, 很形象, 记录当前位置之前1的个数P[i](不包括自己),
    // 那么P[0]为初始的0, 那么之前需要翻转的次数为P[i],之后需要翻转的次数为S.length() - 计算翻转位置 - (P[S.length] - P[计算翻转位置])
    private static int q926_solution(String S) {
        int[] sums = new int[S.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            sums[i + 1] = sums[i] + S.charAt(i) == '1' ? 1 : 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            min = Math.min(min, sums[i] + sums[sums.length - 1] - sums[i] );
        }
        return min;
    }
}
