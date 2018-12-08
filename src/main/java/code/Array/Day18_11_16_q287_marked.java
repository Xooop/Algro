package code.Array;

import java.io.*;
import java.util.Arrays;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/11/16
 */
public class Day18_11_16_q287_marked {
    public static void main(String[] args) {
//        System.out.println(q287_solution(new int[]{1, 4, 6, 6, 6, 2, 3}));
//        System.out.println(q268_My(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static int q287_My(int[] nums) {
        // -----------------------------------------------------------------------------------------------------------------
        // 暴力求解法
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (j == i)
//                    continue;
//                if (nums[i] == nums[j]) {
//                    return nums[i];
//                }
//            }
//        }
//        throw null;
        // -----------------------------------------------------------------------------------------------------------------
        // 排序之后, 查看是否跟前一个相同
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        throw null;
        // -----------------------------------------------------------------------------------------------------------------
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Floyd's Tortoise and Hare (Cycle Detection)
    // idx:  0 1 2 3 4 5 6
    // value:1 4 6 6 6 2 3
    private static int q287_solution(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        int p = nums[0];
        while (p != tortoise) {
            p = nums[p];
            tortoise = nums[tortoise];
        }
        return tortoise;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static int q268_My(int[] nums) throws IOException {
        int total = (nums.length + 1) * (nums.length) / 2;
        for (int num : nums) {
            total -= num;
        }
        FileOutputStream outputStream = new FileOutputStream(new File("./1.txt"));
        outputStream.flush();
        return total;


    }

}
