package code;

import java.util.Arrays;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/17
 */
public class Day18_10_17 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        Arrays.stream(q238_Solution(nums)).forEach(System.out::println);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 我是没想出来怎么做, 所以看了别人做的, 写了个实现
    // 很巧妙的做法:
    // 算数组中的某个数前面的所有数的乘积(算乘积时, 每次都只需要一个数来存储前面所有数的乘积)
    // 再算数组中某个数后面的所有数的乘积(将前面的过程反过来使用就行了)
    private static int[] q238_Solution(int[] nums) {
        if (nums.length == 0) return nums;
        int len = nums.length;
        int[] output = new int[len];
        output[0] = 1;
        for (int i = 1; i < len; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }
        int tmp = 1;
        for (int i = len - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            output[i] *= tmp;
        }
        return output;
    }
    // -----------------------------------------------------------------------------------------------------------------
}
