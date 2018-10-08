import java.util.Arrays;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/9/26
 */
public class Day18_09_26 {

    public static void main(String[] args) {
        Arrays.stream(
                q905_Solutions(new int[]{3, 1, 2, 4})
        ).forEach(System.out::println);
    }


    public static int[] q905_My(int[] A) {
        int len = A.length;
        int[] output = new int[len];
        for (int p = 0, i = 0, j = len - 1; p < len; p++) {
            if (A[p] % 2 == 0) {
                output[i] = A[p];
                i++;
            } else {
                output[j] = A[p];
                j--;
            }
            if (i == j) {
                output[i] = A[p + 1];
                break;
            }
        }
        return output;
    }

    private static int[] q905_Solutions(int[] A) {
        // 1: 将 i 用 i % 2 表示, 再用数组的排序去排序, 所以时间复杂度是 N log(N)
//        return Arrays.stream(A).boxed()
//                .sorted(Comparator.comparingInt(i -> i % 2))
//                .mapToInt(Integer::intValue)
//                .toArray();

        // 2: 两个指针, 一个 p 指向开头, 一个 q 指向最后, 两个数使用 i -> i % 2 表示, 那么就有以下 4 种形式:
        // (1, 0) 俩数需要交换, p 后移, q 前移
        // (0, 1) 俩数位置正确, p 后移, q 前移
        // (0, 0) 前面一个数位置正确, p 后移, q 不变
        // (1, 1) 后面一个数位置正确, p 不变, q 前移
        int p = 0, q = A.length - 1;
        while (p < q) {
//            我的版本很冗长...233
////            if (A[p] % 2 == 1) {
////                if (A[q] % 2 == 0) {
////                    int tmp = A[p];
////                    A[p] = A[q];
////                    A[q] = tmp;
////                    p++;
////                    q--;
////                }else {
////                    q--;
////                }
////            }else{
////                if (A[q] % 2 == 1) {
////                    p++;
////                    q--;
////                }else {
////                    p++;
////                }
////            }
            /*官方版本, 十分简洁...*/
            if (A[p] % 2 > A[q] % 2) {
                int tmp = A[p];
                A[p] = A[q];
                A[q] = tmp;
            }
            if (A[p] % 2 == 0) p++;
            if (A[q] % 2 == 1) q--;
        }
        return A;
    }

}
