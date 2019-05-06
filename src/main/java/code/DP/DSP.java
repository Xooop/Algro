package code.DP;

import code.MyUtils;

import java.io.*;
import java.util.Arrays;

public class DSP {
    public static void main(String[] args) throws IOException {
//        System.out.println(tsp(getDistance()));
//        int i = 3;
//        if ((i & (i << 2)) != 0) {
//            System.out.println(true);
//        }else {
//            System.out.println(false);
//        }
        System.out.println(tsp_solution_2(getDistance()));
//        double[][] distance = new double[][]{{0,3,6,7},{5,0,2,3},{6,4,0,2},{3,7,5,0}};
//        System.out.println(tsp_solution(distance));
    }

    private static double[][] getDistance() throws IOException {
        String path = "./files/DSP.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        int N = Integer.valueOf(bufferedReader.readLine());
        double[][] position = new double[N][2];
        int idx = 0;
        while(bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            position[idx][0] = Double.valueOf(line.split(" ")[1]);
            position[idx][1] = Double.valueOf(line.split(" ")[2]);
            idx++;
        }
        bufferedReader.close();
        double[][] distance = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j)
                    distance[i][j] = 0.0;
                else {
                    distance[i][j] = Math.sqrt(Math.pow(position[i][0] - position[j][0], 2) + Math.pow(position[i][1] - position[j][1], 2));
                }
            }
        }
        return distance;
    }

    private static double tsp(double[][] distance) {
        int nodeNum = distance.length;
        int status = (1 << (nodeNum - 1)) - 1;
        double[][] dp = new double[nodeNum - 1][status];
        double min = Integer.MAX_VALUE;
        for (int i = 1; i < nodeNum; i++) {
            int value = 1 << (i - 1);
            min = Math.min(min, distance[0][i] + process(distance, dp, i - 1, status - value));
        }
        return min;
    }

    private static double process(double[][] distance, double[][] dp, int i, int status) {
        if (dp[i][status] != 0)
            return dp[i][status];
        if (status == 0) {
            dp[i][status] = distance[i + 1][0];
            return distance[i + 1][0];
        }
        double min = Integer.MAX_VALUE;
        int j = 0;
        int tmp = status;
        while(tmp != 0) {
            if ((tmp & 1) == 1) {
                min = Math.min(min, distance[i + 1][j + 1] + process(distance, dp, j, status - (1 << j)));
            }
            tmp >>= 1;
            j++;
        }
        dp[i][status] = min;
        return min;
    }

    private static double tsp_solution(double[][] distance) {
        int N = distance.length - 1;
        int M = 1 << N;
        double[][] dp = new double[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // idx in distance
                int idx1 = j + 1;
                if (i == 0) {
                    dp[j][i] = distance[idx1][0];
                    continue;
                }
                int s1 = 1 << j;
                // 可以加一个这个条件，减少运算
                if ((i & s1) != 0) {
                    dp[j][i] = dp[j][i - s1];
                    continue;
                }
                for (int k = 0; k < N; k++) {
                    // idx in distance
                    int idx2 = k + 1;
                    int s2 = 1 << k;
                    if ((i & s2) != 0) {
                        dp[j][i] = Math.min(dp[j][i] == 0.0 ? Double.MAX_VALUE : dp[j][i],
                                dp[k][i - s2] + distance[idx1][idx2]);
                    }
                }
            }
        }
        double min = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // idx in distance
            int idx = i + 1;
            min = Math.min(min, distance[0][idx] + dp[i][M - 1]);
        }
        return min;
    }

    public static double tsp_solution_2(double[][] distance) {
        int N = distance.length - 1;
        // 状态数
        int M = 1 << N;
        double[][] dp = new double[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // i1 -> j 对应的 distance 中的位置
                // s1 -> j 这个城市对应的状态
                int i1 = j + 1; int s1 = 1 << j;
                if (i == 0) {
                    dp[j][i] = distance[i1][0];
                    continue;
                }
                if ((i & s1) != 0) {
//                    dp[j][i] = dp[j][i - s1];
                    continue;
                }
                for (int k = 0; k < N; k++) {
                    // i2 -> k 对应的 distance 中的位置
                    // s2 -> k 这个城市对应的状态
                    int i2 = k + 1; int s2 = 1 << k;
                    if ((i & s2) != 0) {
                        dp[j][i | s1] = Math.min(dp[j][i | s1] == 0.0 ? Double.MAX_VALUE : dp[j][i | s1]
                                , distance[i2][i1] + dp[k][i]);
                    }
                }
            }
        }
        double min = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // idx in distance
            int idx = i + 1;
            min = Math.min(min, distance[0][idx] + dp[i][M - 1]);
        }
        return min;
    }

}
