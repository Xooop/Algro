import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/15
 */
public class Day18_10_15 {
    public static void main(String[] args) {
//        int[][] A = new int[][]{
//                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
//        };
//        System.out.println(q695_My_new(A));

    }

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
}

