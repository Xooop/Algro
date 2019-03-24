package code.Array;

import java.util.Arrays;

public class Day19_03_24 {
    public static void main(String[] args) {
        int[] test = new int[]{3,3,6,5,-2,2,5,1,-9,4};
        System.out.println(q1020_My(test));
    }

    private static int q509_My(int N) {
        if (N <= 0)
            return 0;
        int[] ns = new int[N + 1];
        ns[0] = 0;
        ns[1] = 1;
        for (int i = 2; i <= N; i++) {
            ns[i] = ns[i - 1] + ns[i - 2];
        }
        return ns[N];
    }

    /**
     * sum = calEvenValue(A)
     * result[A.len]
     * for i in queries.len
     *     value = queries[i][0]
     *     index = queries[i][1]
     *     if A[index] % 2 == 0
     *         if value % 2 == 0
     *             sum += value
     *         else
     *             sum -= A[index]
     *     else
     *         if value % 2 != 0
     *             sum += (A[index] + value)
     *     result[i] = sum
     * return result
     */
    private static int[] q985_My(int[] A, int[][] queries) {
        if (queries == null || queries.length == 0 || queries[0].length == 0) {
            return null;
        }
        int sum = calEvenValue(A);
        int[] result = new int[A.length];
        for (int i = 0; i < queries.length; i++) {
            int value = queries[i][0];
            int index = queries[i][1];
            if (A[index] % 2 == 0) {
                if (value % 2 == 0)
                    sum += value;
                else
                    sum -= A[index];
            } else if (value % 2 != 0)
                sum += (A[index] + value);
            A[index] += value;
            result[i] = sum;
        }
        return result;
    }

    private static int calEvenValue(int[] A) {
        int evenTotal = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                evenTotal += A[i];
            }
        }
        return evenTotal;
    }

    /**
     * if sum(A) % 3 != 0
     *     return false
     * partValue = sum(A) / 3
     * int total = 0
     * for i -> 0 ~ A.len - 1
     *     total += A[i]
     *     if partValue == total
     *         total = 0
     * return total == 0
     */
    private static boolean q1020_My(int[] A) {
        int sum = Arrays.stream(A).sum();
        if (sum % 3 != 0)
            return false;
        int partValue = sum / 3;
        int total = 0;
        for (int i = 0; i < A.length; i++) {
            total += A[i];
            if (total == partValue)
                total = 0;
        }
        return total == 0;
    }
}


