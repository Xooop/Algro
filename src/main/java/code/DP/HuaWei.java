package code.DP;

import code.MyUtils;

import java.util.*;
import java.util.stream.Collectors;

public class HuaWei {
    public static void main(String[] args) {
//        hw_2(200, 0, 200, 10, 200, 50, 200, 30, 200, 25);
//        System.out.println(coffee(1000, 4, 2, 10, new int[]{2, 3, 4, 5}));
        System.out.println(coffee(7, 4, 1, 1, new int[]{2,3,4,5}));
//        System.out.println(coffee(5, 2, 1, 1, new int[]{1, 1}));


    }

    /**
     * 以蜂巢为平面坐标原点的5片花丛A、B、C、D、E的坐标，坐标值为整数。
     * 输入: 200 0 200 10 200 50 200 30 200 25
     * 输出: 456
     */
    public static int hw_2(int... input) {
        int[][] data = new int[6][2];
        for (int i = 0; i < input.length; i++) {
            data[(i / 2) + 1][i % 2] = input[i];
        }
        Arrays.fill(data[0], 0);
        double[][] grid = new double[6][6];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = getDistance(data, i, j);
            }
        }


        return 0;
    }

    public static double getDistance(int[][] data, int i, int j) {
        long tmp = (data[i][0] - data[j][0]) * (data[i][0] - data[j][0])
                + (data[i][1] - data[j][1]) * (data[i][1] - data[j][1]);
        return Math.sqrt(tmp);
    }

    /**
     *
     */
    private static int coffee(int n, int m, int x, int y, int v[]) {
        List<Integer> people = new ArrayList<>();
        int[] arrange = coffeeArrange(n, v);
        int[] maker = Arrays.copyOf(v, v.length);
        int time = 0;
        int washTime = 0;
        while (true) {
            // exit or not
            boolean isExit = true;
            for (int i = 0; i < m; i++) {
                if (arrange[i] != 0) {
                    isExit = false;
                    break;
                }
            }
            isExit &= (washTime == 0);
            isExit &= (people.size() == 0);
            if (isExit) break;
            // timer
            time++;
            // make coffee part
            for (int i = 0; i < m; i++) {
                if (arrange[i] != 0) {
                    maker[i]--;
                    if (maker[i] == 0) {
                        arrange[i]--;
                        maker[i] = v[i];
                        people.add(time);
                    }
                }
            }
            // x -> wash cup time, y -> disappear time
            // dis part
            for (int i = 0; i < people.size(); i++) {
                if (time - people.get(i) == y) {
                    people.remove(i);
                    i--;
                }
            }
            // wash cup part
            if (washTime == 0) {
                if (people.size() > 0) {
                    people.remove(0);
                    washTime = x;
                }
            } else {
                washTime--;
            }
        }
        return time;
    }

    /**
     * n 个人, m 台咖啡机, v[] -> 每个咖啡机煮咖啡的时间
     */
    private static int[] coffeeArrange(int n, int v[]) {
        Arrays.sort(v);
        int[] person = new int[v.length];
        double total = 0.0;
        int totalPerson = 0;
        for (int i = 0; i < v.length; i++) {
            total += ((double) 1) / ((double) v[i]);
        }
        for (int i = 0; i < v.length; i++) {
            double tmp = ((double) 1) / ((double) v[i]);
            tmp /= total;
            person[i] = (int) (tmp * n);
            totalPerson += person[i];
        }
        int diff = n - totalPerson;
        int[] time = new int[v.length];
        int[] tmp = new int[v.length];
        for (int i = 0; i < person.length; i++) {
            time[i] = (person[i] + 1) * v[i];
            tmp[i] = time[i];
        }
        Arrays.sort(tmp);
        for (int i = 0; i < diff; i++) {
            for (int j = 0; j < time.length; j++) {
                if (time[j] == tmp[i]) {
                    time[j] *= -1;
                    person[j]++;
                    break;
                }
            }
        }
        return person;
    }
}
