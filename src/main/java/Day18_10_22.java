/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/22
 */
public class Day18_10_22 {
    public static void main(String[] args) {
        System.out.println(q769_solution(new int[]{2, 0, 1, 3}));
    }

    private static int q122_My(int[] prices) {
        int length = prices.length;
        int max = 0;
        for (int i = 0; i < length - 1; i++) {
            if (prices[i + 1] - prices[i] > 0) {
                max += prices[i + 1] - prices[i];
            }
        }
        return max;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 相对来说, 我的就冗长很多了
    // 关键是没有理解如果一段数组的最大值 max == 这段数组的末尾 idx,
    // 即如果 max(arr[i~j]) == j, 则这段可以被分成一段.
    private static int q769_My(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int start, int end) {
        if (start > end) return 0;
        int max = start, min = end;
        int i = start - 1;
        do {
            i++;
            if (arr[i] > max)
                max = arr[i];
        } while (i < end && max > i);
        int j = end + 1;
        do {
            j--;
            if (arr[j] < min)
                min = arr[j];
        } while (j > start && min < j);

        if (i < j) {
            return 2 + process(arr, i + 1, j - 1);
        } else
            return 1;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 惊了, 居然可以这么做, 仔细想想也对
    private static int q769_solution(int[] arr) {
        int max = 0;
        int chunks = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i)
                chunks++;
        }
        return chunks;
    }
}
