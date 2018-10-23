import java.util.HashSet;
import java.util.Set;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/23
 */
public class Day18_10_23 {
    public static void main(String[] args) {

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
