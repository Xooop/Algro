package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/11/15
 */
public class Day18_11_15 {
    public static void main(String[] args) {
//        int[][] test = new int[][]{ {1,1,1},
//                                    {1,0,1},
//                                    {1,1,1}};
        int[][] test = new int[][]{{1}};
        int[][] rst = q661_My(test);
//        System.out.println(q835_My());
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                System.out.print(rst[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int q835_My(int[][] A, int[][] B) {

//        Point

        return 0;
    }

    private static int[][] q661_My(int[][] M) {
        if (M.length == 0) return null;
        int rst[][] = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                rst[i][j] = getAverage(M, i, j);
            }
        }
        return rst;
    }

    private static int getAverage(int[][] M, int i, int j) {
        List<Integer> paramX = new ArrayList<>(Arrays.asList(-1, 0, 1));
        List<Integer> paramY = new ArrayList<>(Arrays.asList(-1, 0, 1));
        if (i == 0) {
            paramX.remove(0);
        }
        if (i == M.length - 1) {
            paramX.remove(paramX.size() - 1);
        }
        if (j == 0) {
            paramY.remove(0);
        }
        if (j == M[0].length - 1) {
            paramY.remove(paramY.size() - 1);
        }
        int sum = 0;
        int count = 0;
        for (Integer x : paramX) {
            for (Integer y: paramY) {
                sum += M[i + x][j + y];
                count++;
            }
        }
        return sum / count;
    }
}
