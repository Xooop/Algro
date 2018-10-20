import java.util.Arrays;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/20
 */
public class Day18_10_20 {
    public static void main(String[] args) {
        int[] rst = new int[6];
        int v = 1;
        for (int i = 0; i < 2; i++) {
            rst[i] = v++;
        }
//        Arrays.stream(rst).forEach(System.out::println);
        Arrays.stream(q667_solution(6, 4)).forEach(System.out::println);
    }

    // 阵亡... 想不出来, 只有看 solution 了...5555
    private static int[] q667_My(int n, int k) {



        return null;
    }

    private static int[] q667_solution(int n, int k) {
        if (k >= n) return null;
        int[] rst = new int[n];
        int v = 1;
        for (int i = 0; i < n - k - 1; i++) {
            rst[i] = v++;
        }
        for (int i = 0; i < k + 1; i++) {
            // + n - k
            rst[i + n - k - 1] = i % 2 == 0 ? (i / 2) + 1 + n - k : n - (i / 2) + n - k;
        }
        return rst;
    }

    // 排序使 k 为 1
    private static void turnDif1(int[] array, int end) {
        for (int i = 0; i < end; i++) {
            array[i] = i + 1;
        }
    }

    // 排序使 k 为 n - 1, 最后加上差值 start
    private static void turnDif2(int[] array, int start, int end) {
        int n = end - start + 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                array[start + i] = (i / 2) + 1 + start;
            }else {
                array[start + i] = n - (i / 2) + start;
            }
        }
    }
}
