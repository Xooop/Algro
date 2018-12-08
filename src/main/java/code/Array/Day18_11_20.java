package code.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/11/20
 */
public class Day18_11_20 {
    public static void main(String[] args) {
        //System.out.println(q628_solution(new int[]{1, 2, 3, 4}));
    }

    private static int q628_My(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    private static int q628_solution(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2)
                min2 = num;
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }

    private static class q729_solution {
        private TreeMap<Integer, Integer> startAndEnd;

        public q729_solution() {
            startAndEnd = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer pre = startAndEnd.floorKey(start);
            Integer next = startAndEnd.ceilingKey(start);
            if ((pre == null || startAndEnd.get(pre) <= start) && (next == null || end <= next)) {
                startAndEnd.put(start, end);
                return true;
            }
            return false;
        }
    }


//    private static class q729_solution_1 {
//        private List<Integer[]> events;
//
//        public q729_solution_1() {
//            events = new ArrayList<>();
//        }
//
//        public boolean book(int start, int end)
//    }
}
