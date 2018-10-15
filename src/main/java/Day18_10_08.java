/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/8
 */
public class Day18_10_08 {
    public static void main(String[] args) {

    }

    // -----------------------------------------------------------------------------------------------------------------

    private static boolean q896_My(int[] A) {
        int trend = 0;
        for(int i = 0; i < A.length - 1; i++) {
            if((A[i + 1] - A[i]) * trend < 0)
                return false;
            if(trend == 0) {
                trend = A[i + 1] - A[i];
            }
        }
        return true;
    }

    private static boolean q896_My_modified(int[] A) {
        int trend = 0;
        for (int i = 0; i < A.length - 1; i++) {
            int value = A[i + 1] - A[i];
            if (trend == 0) trend = value;
            if (trend > 0 && value < 0) return false;
            if (trend < 0 && value > 0) return false;
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 官方的一个很厉害的解法, 我是服了...
    private static boolean q896_solution1(int[] A) {
        boolean decreasing = true;
        boolean increasing = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1])
                decreasing = false;
            if (A[i] > A[i + 1])
                increasing = false;
        }
        return decreasing || increasing;
    }
}
