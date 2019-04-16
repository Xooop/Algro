package Bag;

import code.MyUtils;

import java.util.*;

public class Extend {
//    public static void main(String[] args) {
//        int[][] items = new int[][]{{2, 3}, {2, 3}, {3, 2}};
//        bag_01_total_opt(items, 4);
//    }

    /**
     * 一种引申的题型是二维费用的题目, 只要将费用那块的 M -> M, N 即可
     * dp[M] -> dp[M][N]
     * 最多只能取 M 件物品也属于这种情况
     * 这事实上相当于每件物品多了一种“件数”的费用，每个物品的件数费用均为1，可以付出的最大件数费用为M。
     */
    public static int bag_2d(int[][] items, int M, int N) {
        int[][] dp = new int[M + 1][N + 1];
        for (int[] item : items) {
            int costM = item[0], costN = item[1], value = item[2], count = item[3];
            bag_2d_multi(costM, costN, value, count, M, N, dp);
        }
        return dp[M][N];
    }

    public static void bag_2d_01(int costM, int costN, int value, int M, int N, int[][] dp) {
        for (int i = M; i >= 0; i--) {
            for (int j = N; j >= 0; j--) {
                if (i - costM > 0 && j - costN > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - costM][j - costN] + value);
                }
            }
        }
    }

    public static void bag_2d_complete(int costM, int costN, int value, int M, int N, int[][] dp) {
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                if (i - costM > 0 && j - costN > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - costM][j - costN] + value);
                }
            }
        }
    }

    public static void bag_2d_multi(int costM, int costN, int value, int count, int M, int N, int[][] dp) {
        if (costM * count <= M && costN * count <= N) {
            bag_2d_complete(costM, costN, value, M, N, dp);
        } else {
            int num = (int) (Math.log(count + 1) / Math.log(2));
            for (int i = 0; i < num; i++) {
                int c = (int) Math.pow(2, i);
                bag_2d_01(costM * c, costN * c, value * c, M, N, dp);
                count -= c;
            }
            if (count != 0)
                bag_2d_01(costM * count, costN * count, value * count, M, N, dp);
        }
    }

    /**
     * 另一种引申题型是分组的背包问题
     * 即物品被分为若干组，每组中的物品相互冲突
     */

    /**
     * 这个问题变成了每组物品有若干种策略：是选择本组的某一件，还是一件都不选。也就是说设f[k][v]表示前k组物品花费费用v能取得的最大权值，则有：
     *
     * f[k][v]=max{f[k-1][v],f[k-1][v-c[i]]+w[i]|物品i属于第k组}
     *
     * 使用一维数组的伪代码如下：
     *
     * for 所有的组k
     *
     *     for v=V..0
     *
     *         for 所有的i属于组k
     *
     *             f[v]=max{f[v],f[v-c[i]]+w[i]}
     */

    /**
     * 例题：
     * 一个旅行者有一个最多能用V公斤的背包，现在有n件物品，它们的重量分别是W1，W2，...,Wn，它们的价值分别为C1,C2,...,Cn。
     * 这些物品被划分为若干组，每组中的物品互相冲突，最多选一件。求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
     * 【输入格式】
     * 第一行：三个整数，V(背包容量，V<=200)，N(物品数量，N<=30)和T(最大组号，T<=10)；
     * 第2..N+1行：每行三个整数Wi,Ci,P，表示每个物品的重量，价值，所属组号。
     * 【输出格式】
     * 仅一行，一个数，表示最大总价值。
     * sample in:
     * 10 6 3
     * 2  1  1
     * 3  3  1
     * 4  8  2
     * 6  9  2
     * 2  8  3
     * 3  9  3
     * sample out:
     * 20
     */
    public static int bag_group() {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(" ");
        int M = Integer.valueOf(line[0]), N = Integer.valueOf(line[1]);
        HashMap<Integer, List<int[]>> groupAndItems = new HashMap<>();
        for (int i = 0; i < N; i++) {
            line = in.nextLine().split(" ");
            List<int[]> items = groupAndItems.getOrDefault(Integer.valueOf(line[2]), new ArrayList<>());
            items.add(new int[]{Integer.valueOf(line[0]), Integer.valueOf(line[1])});
            groupAndItems.put(Integer.valueOf(line[2]), items);
        }
        in.close();
        int[] dp = new int[M + 1];
        for (Integer group : groupAndItems.keySet()) {
            for (int i = M; i >= 0; i--) {
                for (int[] pair : groupAndItems.get(group)) {
                    if (i - pair[0] >= 0) {
                        dp[i] = Math.max(dp[i], dp[i - pair[0]] + pair[1]);
                    }
                }
            }
        }
        return dp[M];
    }

    /**
     * 求背包问题最优值的方案
     */
    public static void bag_01_with_plan(int[][] items, int M) {
        int[][] dp = new int[items.length + 1][M + 1];
        int i, j;
        for (i = 1; i <= items.length; i++) {
            for (j = M; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i - 1][j], j - items[i - 1][0] >= 0 ? (dp[i - 1][j - items[i - 1][0]] + items[i - 1][1]) : 0);
            }
        }

        List<Integer> result = new ArrayList<>();
        i = items.length;
        j = M;
        while (dp[i][j] != 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                result.add(i);
                j -= items[i - 1][0];
            }
            i--;
        }
        Collections.reverse(result);
        System.out.println(result);
        System.out.println(dp[items.length][M]);
    }

    /**
     * 求背包问题的方案总数
     * 这里以完全背包问题为例子
     */
    public static int bag_complete_total(int[][] items, int M) {
        int[][] dp = new int[items.length + 1][M + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= items.length; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = dp[i - 1][j] + (j - items[i - 1][0] >= 0 ? dp[i][j - items[i - 1][0]] : 0);
            }
        }
        return dp[items.length][M];
    }

    /**
     * 求最优方案的总数
     */
    public static void bag_01_total_opt(int[][] items, int M) {
        int[][] dp = new int[items.length + 1][M + 1];
        int[][] g = new int[items.length + 1][M + 1];
        for (int i = 1; i <= items.length; i++) {
            for (int j = 0; j <= M; j++) {
                int cost = items[i - 1][0];
                int value = items[i - 1][1];
                dp[i][j] = Math.max(dp[i - 1][j], j - cost >= 0 ? (dp[i - 1][j - cost] + value) : 0);
                // 以前有多少种方案就是多少种方案
                if (dp[i][j] == dp[i - 1][j]) {
                    g[i][j] += g[i - 1][j];
                }
                // 取了现在这个之后，就创造了一种方案，所以最少是1
                if (j - cost >= 0 && dp[i][j] == dp[i - 1][j - cost] + value) {
                    g[i][j] += g[i - 1][j - cost] == 0 ? 1 : g[i - 1][j - cost];
                }
            }
        }
    }

    private static int totalPrice(int categoryCount, int totalVolume, int totalWeight, int[] volume, int[] weight,
                                  int[] stock, int[] price, int[] itemType) {
        Map<Integer, List<Integer>> groups = new HashMap<>();
        int i = 1;
        while (itemType[i] != 0) {
            List<Integer> group = groups.getOrDefault(itemType[i], new ArrayList<>());
            group.add(i);
            groups.put(itemType[i], group);
            i++;
        }
        System.out.println(groups);
        int[][] dp = new int[totalVolume + 1][totalWeight + 1];
        // 2 4 先上了
        List<Integer> idxs = groups.getOrDefault(2, new ArrayList<>());
        idxs.addAll(groups.getOrDefault(4, new ArrayList<>()));
        System.out.println(idxs);
        for (int idx : idxs) {
            int stk = stock[idx];
            int vol = volume[idx];
            int w = weight[idx];
            int p = price[idx];
            bag_2d_multi(vol, w, p, stk, totalVolume, totalWeight, dp);
        }
        MyUtils.print(dp);
        int[][] tmp = new int[totalVolume + 1][totalWeight + 1];
        for (int j = 0; j <= totalVolume; j++) {
            tmp[j] = Arrays.copyOf(dp[j], totalWeight + 1);
        }
        System.out.println(groups.getOrDefault(1, new ArrayList<>()));
        System.out.println(groups.getOrDefault(3, new ArrayList<>()));
        // 分别再上1, 3
        for (int idx : groups.getOrDefault(1, new ArrayList<>())) {
            int stk = stock[idx];
            int vol = volume[idx];
            int w = weight[idx];
            int p = price[idx];
            bag_2d_multi(vol, w, p, stk, totalVolume, totalWeight, dp);
        }
        MyUtils.print(dp);

        for (int idx : groups.getOrDefault(3, new ArrayList<>())) {
            int stk = stock[idx];
            int vol = volume[idx];
            int w = weight[idx];
            int p = price[idx];
            bag_2d_multi(vol, w, p, stk, totalVolume, totalWeight, tmp);
        }
        MyUtils.print(tmp);
        return Math.max(dp[totalVolume][totalWeight], tmp[totalVolume][totalWeight]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(",");
        //总共商品种类
        int categoryCount = Integer.valueOf(line[0]);
        //快递体积
        int totalVolume = Integer.valueOf(line[1]);
        //快递重量
        int totalWeight = Integer.valueOf(line[2]);

        //物品体积
        int[] volume = new int[50];
        //重量
        int[] weight = new int[50];
        //件数
        int[] stock = new int[50];
        //价格
        int[] price = new int[50];
        //类型
        int[] itemType = new int[50];

        for (int i = 1; i <= categoryCount; i++) {
            line = in.nextLine().split(",");
            volume[i] = Integer.valueOf(line[0]);
            weight[i] = Integer.valueOf(line[1]);
            stock[i] = Integer.valueOf(line[2]);
            price[i] = Integer.valueOf(line[3]);
            itemType[i] = Integer.valueOf(line[4]);
        }

        in.close();

        System.out.println(totalPrice(categoryCount, totalVolume, totalWeight, volume, weight, stock, price, itemType));

    }

}
