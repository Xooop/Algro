package code.DP;

import code.MyUtils;

public class Machine {
    public static void main(String[] args) {
        coffee(7, 4, new int[]{2,3,4,10});

    }

    /**
     * n 个人，m 台机器，每台煮一杯咖啡需要的时间 c[i],求需要的最短时间
     */
    public static void coffee(int n, int m, int[] c) {
        int[] dp = new int[m];
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for (int j = 0; j < m; j++) {
                int tmp = dp[j] + c[j];
                if (min > tmp) {
                    idx = j;
                    min = tmp;
                }
            }
            dp[idx] += c[idx];
        }
        MyUtils.print(dp);
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }


}
