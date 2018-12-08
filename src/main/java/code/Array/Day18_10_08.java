package code.Array;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/8
 */
public class Day18_10_08 {
    public static void main(String[] args) {

    }

    // -----------------------------------------------------------------------------------------------------------------

    private static boolean q896_My(int[] A) {
        int trend = 0;
        for(int i = 0; i < A.length - 1; i++) {
            if((A[i + 1] - A[i]) * trend < 0)
                return false;
            if(trend == 0) {
                trend = A[i + 1] - A[i];
            }
        }
        return true;
    }

    private static boolean q896_My_modified(int[] A) {
        int trend = 0;
        for (int i = 0; i < A.length - 1; i++) {
            int value = A[i + 1] - A[i];
            if (trend == 0) trend = value;
            if (trend > 0 && value < 0) return false;
            if (trend < 0 && value > 0) return false;
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 官方的一个很厉害的解法, 我是服了...
    private static boolean q896_solution1(int[] A) {
        boolean decreasing = true;
        boolean increasing = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1])
                decreasing = false;
            if (A[i] > A[i + 1])
                increasing = false;
        }
        return decreasing || increasing;
    }
    // -----------------------------------------------------------------------------------------------------------------

    private static int q485_My(int[] nums) {
        int max = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                count++;
            if (count > 0 && nums[i] == 0) {
                if (max < count)
                    max = count;
                count = 0;
            }
        }
        if (count != 0 && max < count)
            max = count;
        return max;
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static int[] q888_My(int[] A, int[] B) {
        int[] out = new int[2];
        int dif = sum(A) - sum(B);
        int need;
        if (dif < 0) {
            need = (-dif) / 2;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (B[j] == A[i] + need) {
                        out[0] = A[i];
                        out[1] = B[j];
                        return out;
                    }
                }
            }
        }
        if (dif > 0) {
            need = dif / 2;
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[j] == B[i] + need) {
                        out[0] = A[j];
                        out[1] = B[i];
                        return out;
                    }
                }
            }
        }
        if (dif == 0) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (B[j] == A[i]) {
                        out[0] = out[1] = A[i];
                        return out;
                    }
                }
            }
        }
        return out;
    }

    private static int sum(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return sum;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 很简洁的实现...
    private static int[] q888_Solution1(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int i : A)
            sumA += i;
        for (int i : B)
            sumB += i;
        int dif = (sumB - sumA) / 2;
        HashSet<Integer> setB = new HashSet<>();
        for (int i : B)
            setB.add(i);
        for (int i : A) {
            if (setB.contains(i + dif)) {
                return new int[]{i, i + dif};
            }
        }
        throw null;
    }
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // 我也不知道为啥我要用栈来做这件事，可能想的是可以回溯出路径吧，但相比后两种来说，我减少了迭代次数，但后两种看起来更简洁
    private static int q695_My(int[][] grid) {
        Stack<Position> nodes = new Stack<>();
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    nodes.push(new Position(i, j));
                    grid[i][j] = -1;
                    int tmp = getALand(grid, nodes);
                    if (tmp > max)
                        max = tmp;
                }
            }
        }
        return max;
    }

    /**
     * 每次出栈一个元素,再入栈右边与下面值为 1 的 Position
     *
     * @param grid
     * @param nodesToSearch
     * @return
     */
    private static int getALand(int[][] grid, Stack nodesToSearch) {
        if (nodesToSearch.empty()) return 0;
        Position node = (Position) nodesToSearch.pop();
        int i = node.x;
        int j = node.y;
        if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
            nodesToSearch.push(new Position(i, j + 1));
            grid[i][j + 1] = -1;
        }
        if (i < grid.length - 1 && grid[i + 1][j] == 1) {
            nodesToSearch.push(new Position(i + 1, j));
            grid[i + 1][j] = -1;
        }
        if (i > 0 && grid[i - 1][j] == 1) {
            nodesToSearch.push(new Position(i - 1, j));
            grid[i - 1][j] = -1;
        }
        if (j > 0 && grid[i][j - 1] == 1) {
            nodesToSearch.push(new Position(i, j - 1));
            grid[i][j - 1] = -1;
        }
        int size = getALand(grid, nodesToSearch) + 1;
        return size;
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 根据别人的代码, 优化自己的
    public static int q695_My_new(int[][] grid) {
        if (grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int max = 0;
        Stack<int[]> nodes = new Stack<>();
        boolean[][] seen = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && !seen[i][j]) {
                    nodes.push(new int[]{i, j});
                    max = Math.max(max, getALand(grid, seen, nodes));
                }
            }
        }
        return max;
    }

    private static int getALand(int[][] grid, boolean[][] seen, Stack<int[]> nodes) {
        // 栈空意味着不需要继续迭代, 已经获取了这块陆地的最大值
        if (nodes.empty()) return 0;

        // 当出栈的坐标不符合要求时, 返回 0 + 栈中其他元素的邻接陆地面积
        int[] node = nodes.pop();
        int x = node[0];
        int y = node[1];
        if (x < 0 || y < 0 || x == grid.length || y == grid[0].length || grid[x][y] == 0 || seen[x][y]) {
            return (0 + getALand(grid, seen, nodes));
        }

        // 当出栈的坐标符合要求时, 返回 1 + 栈中其他元素的邻接陆地面积
        int[] delx = new int[]{0, 0, 1, -1};
        int[] dely = new int[]{-1, 1, 0, 0};
        seen[x][y] = true;
        for (int i = 0; i < 4; i++) {
            nodes.push(new int[]{x + delx[i], y + dely[i]});
        }
        return (1 + getALand(grid, seen, nodes));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 深度优先, 也太简洁了吧, 诶.....
    public static int q695_solution1(int[][] grid) {
        if (grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int max = 0;
        int[][] seen = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, getAreaFromThisNode(grid, seen, i, j));
                }
            }
        }
        return max;
    }

    private static int getAreaFromThisNode(int[][] grid, int[][] seen, int r, int c) {
        if (r >= grid.length || c >= grid[0].length || r < 0 || c < 0 || seen[r][c] == 1 || grid[r][c] == 0)
            return 0;
        seen[r][c] = 1;
        return 1 + getAreaFromThisNode(grid, seen, r + 1, c)
                + getAreaFromThisNode(grid, seen, r, c + 1)
                + getAreaFromThisNode(grid, seen, r - 1, c)
                + getAreaFromThisNode(grid, seen, r, c - 1);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // 还十分傻逼的用冒泡法....
    public static void q283_My(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (flag || nums[j] == 0) {
                    flag = true;
                    if (nums[j + 1] != 0) {
                        int tmp = nums[j + 1];
                        nums[j + 1] = nums[j];
                        nums[j] = tmp;
                    }
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 很简洁的方法实现, 利用了一个下标用于当前已标记不为零的位置, 之后出现不为零的则直接赋值即可
    // 为什么可以这样做: 因为总会存在不为零的数, 所以这个用于标记不为零的下标的速度慢于正常遍历的速度,
    // 所以不会出现将没有遍历的元素覆盖的情况出现.
    private static void q283_solution1(int[] nums) {
        int processedNumIdx = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != 0) {
                nums[processedNumIdx++] = nums[i];
            }
        for (int i = processedNumIdx; i < nums.length; i++)
            nums[i] = 0;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 上面是采用赋值的形式，然后会有很多不必要的赋值操作
    // 这里用的是交换的方法, 最明显的不用最后赋值 0
    private static void q283_solution2(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                swap(nums, lastNonZeroFoundAt++, cur);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    // -----------------------------------------------------------------------------------------------------------------
}
