import java.util.Arrays;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/16
 */
public class Day18_10_16 {
    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 0, 3, 12};
        q283_solution2(A);
        Arrays.stream(A).forEach(System.out::println);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 还十分傻逼的用冒泡法....
    public static void q283_My(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (flag || nums[j] == 0) {
                    flag = true;
                    if (nums[j + 1] != 0) {
                        int tmp = nums[j + 1];
                        nums[j + 1] = nums[j];
                        nums[j] = tmp;
                    }
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 很简洁的方法实现, 利用了一个下标用于当前已标记不为零的位置, 之后出现不为零的则直接赋值即可
    // 为什么可以这样做: 因为总会存在不为零的数, 所以这个用于标记不为零的下标的速度慢于正常遍历的速度,
    // 所以不会出现将没有遍历的元素覆盖的情况出现.
    private static void q283_solution1(int[] nums) {
        int processedNumIdx = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != 0) {
                nums[processedNumIdx++] = nums[i];
            }
        for (int i = processedNumIdx; i < nums.length; i++)
            nums[i] = 0;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 上面是采用赋值的形式，然后会有很多不必要的赋值操作
    // 这里用的是交换的方法, 最明显的不用最后赋值 0
    private static void q283_solution2(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                swap(nums, lastNonZeroFoundAt++, cur);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
