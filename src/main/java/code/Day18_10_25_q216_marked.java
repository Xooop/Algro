package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/25
 */
public class Day18_10_25_q216_marked {
    public static void main(String[] args) {
        System.out.println(q216_solution(3, 15));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 没做出来...
    private static void q216_My(int k, int n) {

    }

    private static List<List<Integer>> q216_solution(int k, int n) {
        List<List<Integer>> rst = new ArrayList<>();
        process(rst, new ArrayList<>(), 1, k, n);
        return rst;
    }


    private static boolean process(List<List<Integer>> rst, List<Integer> ans, int start, int k, int n) {
        List<Integer> copy = new ArrayList<>(ans);
        if (k < 0 || n < 0) {
            return false;
        }
        if (k == 0 && n == 0) {
            rst.add(copy);
            return false;
        }

//        if (k == 0) {
//            if (n > 0)
//                return true;
//            if (n == 0) {
//                rst.add(copy);
//            }
//            return false;
//        }else if (n <= 0) {
//            return false;
//        }

        for (int i = start; i <= 9; i++) {
            ans.add(i);
            boolean shouldContinue = process(rst, ans, i + 1, k - 1, n - i);
            ans.remove(ans.size() - 1);
            if (!shouldContinue) {
                break;
            }
        }

        return true;
    }
}
