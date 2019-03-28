package code.Array;

import code.MyUtils;

import java.util.Arrays;

public class Day19_03_28 {
    public static void main(String[] args) {
//        System.out.println(q747_My(new int[]{0,0,3,2}));
//        System.out.println(q26_My(new int[]{1,1,2}));
        System.out.println(q643_My(new int[]{-1}, 1));
    }

    private static int q747_My(int[] nums) {
        if (nums.length == 1)
            return 0;
        int[] max = new int[2];
        Arrays.fill(max, -1);
        int second = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max[1]) {
                second = max[1];
                max[1] = nums[i];
                max[0] = i;
            }else if (nums[i] > second) {
                second = nums[i];
            }
        }
        System.out.println(max[1]);
        System.out.println(second);
        if (max[1] >= (second * 2)) {
            return max[0];
        }
        return -1;
    }

    /**
     * two pointers
     * i = 0
     * j = 1
     * for j < nums.len
     *     if nums[j] == nums[i]
     *         j++
     *     else {
     *         tmp = nums[j]
     *         num[j] = nums[i + 1]
     *         nums[i + 1] = tmp
     *         i++
     *     }
     * return i + 1;
     *
     *
     */
    private static int q26_My(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[i + 1] = nums[j];
                i++;
            }
        }
        return i + 1;
    }

    /**
     *
     */
    private static double q643_My(int[] nums, int k) {
//        double max = Double.NEGATIVE_INFINITY;
//        for (int i = 0; i <= nums.length - k; i++) {
//            int sum = 0;
//            for (int j = i; j < i + k; j++) {
//                sum += nums[j];
//            }
//            max = Math.max(max, (double)sum / k);
//        }
//        return max;

        int i = 0;
        int j = k;
        return 0.0;
    }
}
