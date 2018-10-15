import java.util.HashSet;
import java.util.Set;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/12
 */
public class Day18_10_12 {
    public static void main(String[] args) {
        System.out.println(sum(new int[]{1,2,3,4}));
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static int q485_My(int[] nums) {
        int max = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                count++;
            if (count > 0 && nums[i] == 0) {
                if (max < count)
                    max = count;
                count = 0;
            }
        }
        if (count != 0 && max < count)
            max = count;
        return max;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static int[] q888_My(int[] A, int[] B) {
        int[] out = new int[2];
        int dif = sum(A) - sum(B);
        int need;
        if (dif < 0) {
            need = (-dif) / 2;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (B[j] == A[i] + need) {
                        out[0] = A[i];
                        out[1] = B[j];
                        return out;
                    }
                }
            }
        }
        if (dif > 0) {
            need = dif / 2;
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[j] == B[i] + need) {
                        out[0] = A[j];
                        out[1] = B[i];
                        return out;
                    }
                }
            }
        }
        if (dif == 0) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (B[j] == A[i]) {
                        out[0] = out[1] = A[i];
                        return out;
                    }
                }
            }
        }
        return out;
    }

    private static int sum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 很简洁的实现...
    private static int[] q888_Solution1(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int i : A)
            sumA += i;
        for (int i : B)
            sumB += i;
        int dif = (sumB - sumA) / 2;
        HashSet<Integer> setB = new HashSet<>();
        for (int i : B)
            setB.add(i);
        for (int i : A) {
            if (setB.contains(i + dif)) {
                return new int[]{i, i + dif};
            }
        }
        throw null;
    }





}
