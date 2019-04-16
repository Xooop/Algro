package Bag;

import code.MyUtils;

import java.util.Scanner;

public class Base {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String[] line = in.nextLine().split(" ");
//        int itemNum = Integer.valueOf(line[0]);
//        int M = Integer.valueOf(line[1]);
//        int[][] items = new int[itemNum][2];
//        for (int i = 0; i < itemNum; i++) {
//            String[] tmp = in.nextLine().split(" ");
//            items[i][0] = Integer.valueOf(tmp[0]);
//            items[i][1] = Integer.valueOf(tmp[1]);
//        }
//        in.close();
////        System.out.println(bag_01(items, M));
//        System.out.println(bag_complete(items, M));
        int[][] items = new int[][]{{3, 4}, {1, 2}, {2, 5}, {3, 7}};
        System.out.println(bag_complete(items, 4));
    }

    /**
     * 01 背包问题
     */
    public static int bag_01(int[][] items, int M) {
        int[] dp = new int[M + 1];
        for (int i = 0; i < items.length; i++) {
            bag_01(items[i][0], items[i][1], M, dp);
            MyUtils.print(dp);
        }
        return dp[M];
    }

    public static void bag_01(int cost, int value, int M, int[] dp) {
        for (int i = M; i >= 0; i--) {
            dp[i] = Math.max(dp[i], i - cost >= 0 ? (dp[i - cost]) + value : 0);
        }
    }

    /**
     * 完全背包问题
     */
    public static int bag_complete(int[][] items, int M) {
        int[] dp = new int[M + 1];
        for (int i = 0; i < items.length; i++) {
            bag_complete(items[i][0], items[i][1], M, dp);
            MyUtils.print(dp);
        }
        return dp[M];
    }

    public static void bag_complete(int cost, int value, int M, int[] dp) {
        for (int i = 0; i <= M; i++) {
            dp[i] = Math.max(dp[i], i - cost >= 0 ? (dp[i - cost] + value) : 0);
        }
    }

    /**
     * 多重背包问题
     * items -> weight, value, count
     */
    public static int bag_multi(int[][] items, int M) {
        int[] dp = new int[M + 1];
        for (int i = 0; i < items.length; i++) {
            bag_multi(items[i][0], items[i][1], items[i][2], M, dp);
        }
        return dp[M];
    }

    public static void bag_multi(int cost, int value, int count, int M, int[] dp) {
        if (cost * count <= M) {
            bag_complete(cost , value, M, dp);
        }else {
            int num = (int) (Math.log(count + 1) / Math.log(2));
            for (int j = 0; j < num; j++) {
                int c = (int) Math.pow(2, j);
                bag_01(c * cost , c * value, M, dp);
                count -= c;
            }
            if (count != 0)
                bag_01(count * cost , count * value, M , dp);
        }
    }
}
