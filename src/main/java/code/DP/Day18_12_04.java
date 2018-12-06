package code.DP;

import java.util.Arrays;

public class Day18_12_04 {
    public static void main(String[] args) {
        Arrays.stream(q338_My(4)).boxed().forEach(System.out::print);
    }

    private static int[] q338_My(int num) {
        int[] f = new int[num + 1];
        for (int i = 0; i < num + 1; i++) {
            f[i] = f[i >> 1] + (i & 1);
        }
        return f;
    }
}
