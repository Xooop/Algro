package code;

import java.util.*;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/21
 */
public class Day18_10_21 {
    public static void main(String[] args) {
        int[] a = new int[]{2,2,1,1,1,2,2};
        System.out.println(q168_solution2(a));
//        Arrays.stream(a).forEach(System.out::println);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 我的做法, 跟官方提供的做法类似, 但就是要冗余很多了
    private static int q565_My(int[] nums) {
        int max = 0;
        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            process(rst, nums, i);
            if (rst.size() > ((float) nums.length / 2)) {
                max = rst.size();
                break;
            }
            max = Math.max(max, rst.size());
            rst.clear();
        }
        return max;
    }

    private static void process(List<Integer> rst, int[] nums, int i) {
        int n = nums.length;
        if (nums[i] >= n) return;
        rst.add(nums[i]);
        nums[i] += n;
        process(rst, nums, nums[i] % n);
    }
    // -----------------------------------------------------------------------------------------------------------------
    // 其实也是我自己写的, 按照solution里提到的, 进行了优化
    private static int q565_Solution(int[] nums) {
        int n = nums.length;
        int max = 0;
        // mark the visited element nums[i] with (nums.length + nums[i]).
        for (int i = 0; i < n; i++) {
            int start = i;
            int size = 0;
            while (nums[start] < n) {
                int tmp = nums[start];
                nums[start] += n;
                size++;
                start = tmp;
            }
            // when the calculated size is more than half of the nums.length, we can infer that this is the longest.
            // so we can exit the loop early.
            if (size > ((float) n / 2)) {
                max = size;
                break;
            }
            max = Math.max(max, size);
        }
        // restore the array.
        for (int i = 0; i < n; i++) {
            nums[i] %= n;
        }
        return max;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static int q168_My(int[] nums) {
        Map<Integer, Integer> value_count = new HashMap<>();
        for (int value : nums) {
            if (value_count.containsKey(value)) {
                value_count.put(value, (Integer) value_count.get(value) + 1);
            }else {
                value_count.put(value, 1);
            }
        }
        for (Map.Entry e : value_count.entrySet()) {
            if ((Integer)e.getValue() > (float)nums.length / 2) {
                return (int)e.getKey();
            }
        }
        throw null;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // Boyer-Moore 投票算法
    private static int q168_solution2(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0)
                candidate = num;
            count += num == candidate ? +1 : -1;
        }
        return candidate;
    }

    // -----------------------------------------------------------------------------------------------------------------
}
