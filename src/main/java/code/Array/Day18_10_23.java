package code.Array;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/23
 */
public class Day18_10_23 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[\\d]+[\\u4E00-\\u9FA5]*");
        System.out.println(pattern.matcher("2345亿").matches());
    }

    private static boolean q217_My(int[] nums){
        if (nums.length == 0 && nums.length == 1) return false;
        Set<Integer> numbers = new HashSet<>();
        for (int num : nums) {
            if (!numbers.add(num)) {
                return true;
            }
        }
        return false;
    }
}
