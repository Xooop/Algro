package code.DP;

import java.util.Arrays;

public class Day18_12_06 {
    public static void main(String[] args) {
        q877_My(new int[]{9,9,10,1,7,3});
    }

    private static boolean q877_My(int[] piles) {
        int[] sorted = new int[piles.length];
        int i = 0, j = piles.length - 1, count = 0;
        while (i <= j) {
            if (piles[i] < piles[j]) {
                sorted[count] = piles[j];
                j--;
            }else {
                sorted[count] = piles[i];
                i++;
            }
            count++;
        }
        int odd = 0;
        int even = 0;
        for (int k = 0; k < sorted.length; k++) {
            if ((k & 1) == 0) {
                odd += sorted[k];
            }else {
                even += sorted[k];
            }
        }
        return odd > even;
    }

    private static boolean q877_solution(int[] piles) {
        int N = piles.length;
        int[][] f = new int[N][N];
        for (int size = 2; size < N; size++) {
            for (int i = 0, j = i + size - 1; i < N + 1 - size; i++) {
                if (size % 2 == 0) {

                }
            }
        }

        return false;
    }
}
