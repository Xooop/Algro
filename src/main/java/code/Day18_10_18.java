package code;

import com.sun.org.apache.xml.internal.utils.res.IntArrayWrapper;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/18
 */
public class Day18_10_18 {
    public static void main(String[] args) {
        int[] test = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(q448_another(test));
    }

    // 因为条件限制了时间复杂度O(n), 不使用额外的空间
    public static List<Integer> q448_My(int[] nums) {
        List<Integer> missing = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int original = Math.abs(nums[i]);
            if (nums[original - 1] > 0) {
                nums[original - 1] *= -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0)
                nums[i] *= -1;
            else
                missing.add(i + 1);
        }
        return missing;
    }

    // 如果不限制
    public static List<Integer> q448_another(int[] nums) {
        List<Integer> missing = new ArrayList<>();
        Map<Integer, Integer> value_position = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            value_position.put(i + 1, -1);
        }
        for (int i = 0; i < nums.length; i++) {
            value_position.put(nums[i], i + 1);
        }
        for (Map.Entry e : value_position.entrySet()) {
            if ((Integer) e.getValue() == -1) {
                missing.add((Integer) e.getKey());
            }
        }
        return missing;
    }
}
