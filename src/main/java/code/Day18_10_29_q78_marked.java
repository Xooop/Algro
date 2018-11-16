package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/29
 */
public class Day18_10_29_q78_marked {
    public static void main(String[] args) {
        System.out.println(q78_solution(new int[]{1,2,3,4}).size());
    }

    private static List<List<Integer>> q78_My(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        addOrNot(rst, new ArrayList<>(), nums, 0);
        return rst;
    }

    private static void addOrNot(List<List<Integer>> rst, List<Integer> oneRst, int[] nums, int curIdx) {
        if (curIdx == nums.length) {
            rst.add(oneRst);
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                List<Integer> one = new ArrayList<>(oneRst);
                addOrNot(rst, one, nums, curIdx + 1);
            }
            if (i == 1) {
                List<Integer> two = new ArrayList<>(oneRst);
                two.add(nums[curIdx]);
                addOrNot(rst, two, nums, curIdx + 1);
            }
        }
    }

    // 回溯法
    private static List<List<Integer>> q78_solution(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        backtrack(rst, new ArrayList<>(), nums, 0);
        return rst;
    }

    private static void backtrack(List<List<Integer>> rst, List<Integer> subset, int[] nums, int level) {
//        if (level == nums.length) {
//            return;
//        }
//        List<Integer> copy = new ArrayList<>(subset);
//        rst.add(copy);
//        backtrack(rst, subset, nums, level + 1);
//        subset.add(nums[level]);
//        backtrack(rst, subset, nums, level + 1);
//        subset.remove(subset.size() - 1);
        List<Integer> copy = new ArrayList<>(subset);
//        if (level == nums.length) {
//            rst.add(copy);
//            return;
//        }
        rst.add(copy);
        for (int i = level; i < nums.length; i++) {
            subset.add(nums[level]);
            backtrack(rst, subset, nums, i + 1);
            subset.remove(subset.size() - 1);
//            backtrack(rst, subset, nums, level + 1);
        }

    }
}
