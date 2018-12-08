package code.Array;

import java.util.HashSet;
import java.util.Set;

public class Day18_12_03 {
    public static void main(String[] args) {
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(q746_solution(cost));
    }

    private static int q746_My(int[] cost) {
        int[] mins = new int[cost.length + 1];
        mins[0] = cost[0];
        mins[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            mins[i] = Math.min(mins[i - 1], mins[i - 2]) + cost[i];
        }
        return Math.min(mins[mins.length - 3], mins[mins.length - 2]);
    }

    private static int q746_solution(int[] cost) {
        int min1 = 0;
        int min2 = 0;
        for (int i = 0; i < cost.length; i++) {
           int tmp = Math.min(min1, min2) + cost[i];
           min2 = min1;
           min1 = tmp;
        }
        return Math.min(min1, min2);
    }

    private static int q768_My(int[] arr) {


        return 0;
    }
}
