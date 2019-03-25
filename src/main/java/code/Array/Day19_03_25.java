package code.Array;

public class Day19_03_25 {
    public static void main(String[] args) {
        System.out.println(q674_My(new int[]{1,3,5,4,2,3,4,5}));
    }

    /**
     * maxLen = Integer.min
     * tmp = 1
     * for i -> 0 ~ nums.len - 2
     *     if nums[i] < nums[i + 1]
     *         tmp++
     *     else
     *         maxLen = max(tmp, maxLen)
     * maxLen = max(tmp, maxLen)
     * return maxLen
     */
    private static int q674_My(int[] nums) {
        if (nums.length == 0) return 0;
        int maxLen = Integer.MIN_VALUE;
        int tmp = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1])
                tmp++;
            else {
                maxLen = Math.max(tmp, maxLen);
                tmp = 1;
            }
        }
        System.out.println(tmp);
        maxLen = Math.max(tmp, maxLen);
        return maxLen;
    }
}
