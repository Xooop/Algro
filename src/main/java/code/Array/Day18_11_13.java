package code.Array;

import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/11/13
 */
public class Day18_11_13 {
    public static void main(String[] args) {
//        Arrays.stream(q167_solution_hashMap(new int[]{2,7,11,15}, 9)).forEach(System.out::println);
        System.out.println(q697_solution_optimized(new int[]{6, 5, 5}));
    }

    private static int[] q167_My(int[] numbers, int target) {
        int[] rst = new int[2];
        int len = numbers.length;
        for (int i = 0; i < len - 1; i++) {
            int first = numbers[i];
            int second = target - first;
            for (int j = len - 1; j > i; j--) {
                if (numbers[j] == second) {
                    rst[0] = i + 1;
                    rst[1] = j + 1;
                    return rst;
                }
            }
        }
        throw null;
    }

    private static int[] q167_solution_two_pointers(int[] numbers, int target) {
        int len = numbers.length;
        int i = 0, j = len - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            }
            while (numbers[i] + numbers[j] < target)
                i++;
            while (numbers[i] + numbers[j] > target)
                j--;
        }
        throw null;
    }

    private static int[] q167_solution_binary_search(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            Integer idx = binarySearch(numbers, i, numbers.length - 1, target - numbers[i]);
            if (idx != null) {
                return new int[]{i + 1, idx + 1};
            }
        }
        throw null;
    }

    private static Integer binarySearch(int[] nums, int start, int end, int target) {
        if (start == end) {
            if (nums[start] == target)
                return start;
            else
                return null;
        }

        int mid = start + ((end - start + 1) / 2);
        if (target == nums[mid])
            return mid;
        if (target > nums[mid]) {
            return binarySearch(nums, mid + 1, end, target);
        }
        return binarySearch(nums, start, mid - 1 < start ? start : mid - 1, target);
    }

    private static int[] q167_solution_hashMap(int[] numbers, int target) {
        Map<Integer, Integer> valueAndIdx = new HashMap<>();
        int i = 1;
        for (int num : numbers) {
            if (valueAndIdx.containsKey(target - num)) {
                return new int[]{valueAndIdx.get(target - num), i};
            } else {
                valueAndIdx.put(num, i++);
            }
        }
        throw null;
    }

    private static int q697_My(int[] nums) {
        HashMap<Integer, Integer> valueAndFreq = new HashMap<>();
        for (int num : nums) {
            if (valueAndFreq.containsKey(num)) {
                valueAndFreq.put(num, valueAndFreq.get(num) + 1);
            } else
                valueAndFreq.put(num, 1);
        }

        List<Integer> maxFreqNums = new ArrayList<>();
        int maxFreq = 0;
//        for (Map.Entry<Integer, Integer> e : valueAndFreq.entrySet()) {
//            if (e.getValue() > maxFreq) {
//                maxFreqNums.clear();
//                maxFreqNums.add(e.getKey());
//                maxFreq = e.getValue();
//            } else if (e.getValue() == maxFreq) {
//                maxFreqNums.add(e.getKey());
//            }
//        }

        int len = nums.length;
        for (int maxFreqNum : maxFreqNums) {
            int start = 0;
            int end = nums.length;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == maxFreqNum) {
                    start = i;
                    break;
                }
            }

            for (int i = nums.length - 1; i >= 0; i--) {
                if (nums[i] == maxFreqNum) {
                    end = i;
                    break;
                }
            }
            len = end - start + 1 < len ? end - start + 1 : len;
        }

        return len;
    }

//    private static int q697_solution(int[] nums) {
//        Map<Integer, Integer> valueAndFirstIdx = new HashMap<>();
//        Map<Integer, Integer> valueAndLastIdx = new HashMap<>();
//        Map<Integer, Integer> valueAndFreq = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (!valueAndFirstIdx.containsKey(nums[i])) {
//                valueAndFirstIdx.put(nums[i], i);
//                valueAndFreq.put(nums[i], 1);
//            } else {
//                valueAndFreq.put(nums[i], valueAndFreq.get(nums[i]) + 1);
//            }
//        }
//
//        for (int i = nums.length - 1; i >= 0; i--) {
//            if (!valueAndLastIdx.containsKey(nums[i])) {
//                valueAndLastIdx.put(nums[i], i);
//            }
//        }
//
//        int freq = 0;
//        List<Integer> values = new ArrayList<>();
//        for (Map.Entry<Integer, Integer> e : valueAndFreq.entrySet()) {
//            if (e.getValue() > freq) {
//                values.clear();
//                values.add(e.getKey());
//                freq = e.getValue();
//            } else if (e.getValue() == freq) {
//                values.add(e.getKey());
//            }
//        }
//
//        Collections.max(values);
//
//        int len = nums.length;
//        for (Integer value : values) {
//            len = Math.min(len, valueAndLastIdx.get(value) - valueAndFirstIdx.get(value) + 1);
//        }
//
//        return len;
//    }

    // -----------------------------------------------------------------------------------------------------------------
    // 我自己的做法居然是一次循环拿到从前往后第一次出现的位置
    // 再一次循环拿到从后往前第一次出现的位置
    // 其实使用Map的性质, 在一次循环中从前往后put时就已经能得到从后往前第一次出现的位置了
    // -----------------------------------------------------------------------------------------------------------------
    // 看了别人的优化之类的, 可以新建一个类, 用那个类同时存储频率、第一次出现位置、第二次出现位置这些信息,
    // 避免之后再进行循环查询, 也是很机智了.
    // -----------------------------------------------------------------------------------------------------------------
    private static int q697_solution_optimized(int[] nums) {
        Map<Integer, Integer> valueAndFirstIdx = new HashMap<>();
        Map<Integer, Integer> valueAndLastIdx = new HashMap<>();
        Map<Integer, Integer> valueAndFreq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!valueAndFirstIdx.containsKey(nums[i])) {
                valueAndFirstIdx.put(nums[i], i);
            }
            valueAndLastIdx.put(nums[i], i);
            valueAndFreq.put(nums[i], valueAndFreq.getOrDefault(nums[i], 0) + 1);
        }

        int freq = 0;
        List<Integer> maxFreqValues = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : valueAndFreq.entrySet()) {
            if (e.getValue() > freq) {
                maxFreqValues.clear();
                maxFreqValues.add(e.getKey());
                freq = e.getValue();
            }else if (e.getValue() == freq) {
                maxFreqValues.add(e.getKey());
            }
        }

        int len = nums.length;
        for (Integer value : maxFreqValues) {
            len = Math.min(len, valueAndLastIdx.get(value) - valueAndFirstIdx.get(value) + 1);
        }

        return len;
    }
}
