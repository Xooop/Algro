package code.Array;

import code.MyUtils;

public class Day19_04_01 {
    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,1};
        MyUtils.print(q942_My("DDID"), null );

    }

    private static boolean q665_My(int[] nums) {
        int fall = getFall(nums, 0);
        if (fall == nums.length) return true;
        if (getFall(nums, fall) != nums.length) return false;
        return check(nums, fall);
    }

    private static int getFall(int[] nums, int start) {
        start++;
        while(start < nums.length && nums[start] >= nums[start - 1])
            start++;
        return start;
    }

    private static boolean check(int[] nums, int fall) {
        if (fall == nums.length - 1)
            return true;
        if (fall < 2 || (nums[fall - 2] <= nums[fall - 1] && nums[fall - 1] <= nums[fall + 1]) || (nums[fall - 2] <= nums[fall] && nums[fall] <= nums[fall + 1]))
            return true;
        return false;
    }

    private static int q581_solution_1(int[] nums) {
        int min = Integer.MAX_VALUE;
        int i;
        for (i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                min = Math.min(min, nums[i]);
            }
        }
        if (min == Integer.MAX_VALUE) return 0;
        int max = Integer.MIN_VALUE;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i + 1] < nums[i]) {
                max = Math.max(max, nums[i]);
            }
        }
        System.out.println("max: " + max);
        System.out.println("min: " + min);
        i = 0;
        for (; nums[i] <= min; i++);
        System.out.println(i);
        int j = nums.length - 1;
        for (; nums[j] >= max; j--);
        System.out.println(j);
        return j - i + 1;
    }

    private static int sd(int n) {
        int[] tmp = new int[n + 1];
        tmp[0] = 0;
        for (int i = 1; i <= n; i++) {
            tmp[i] = Math.min(tmp[i - 1], i - 2 > 0 ? tmp[i - 2] : n) + 1;
        }
        return tmp[n];
    }

    private static int q70_My(int n) {
        int[] tmp = new int[n + 1];
        tmp[0] = 1;
        for (int i = 1; i <= n; i++) {
            tmp[i] = tmp[i - 1] + (i - 2 >= 0 ? tmp[i - 2] : 0);
        }
        return tmp[n];
    }

    /**
     * tmp[i] = Math.max(tmp[i - 1], tmp[i - 2] + nums[i])
     */
    private static int q198_My(int[] nums) {
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = Math.max((i - 1 >= 0 ? tmp[i - 1] : 0), (i - 2 >= 0 ? tmp[i - 2] : 0) + nums[i]);
        }
        return nums.length - 1 >= 0 ? tmp[nums.length - 1] : 0;
    }

    private class NumArray {
        int[] sumArray;

        public NumArray(int[] nums) {
            sumArray = new int[nums.length];
            sumArray[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sumArray[i] = sumArray[i - 1] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sumArray[j] - (i - 1 >= 0 ? sumArray[i - 1] : 0);
        }
    }

    private static int[] q942_My(String S) {
        int N = S.length();
        int lo = 0, hi = N;
        int[] ans = new int[N + 1];
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == 'I')
                ans[i] = lo++;
            else
                ans[i] = hi--;
        }

        ans[N] = lo;
        return ans;
    }
}
