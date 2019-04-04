package code;


import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyUtils {

    public static Integer[] arrayIntToInteger(int[] array) {
//        return ArrayUtils.toObject(array);
        return Arrays.stream(array).boxed().toArray(Integer[]::new);
    }

    public static int[] arrayIntegerToInt(Integer[] array) {
        return Arrays.stream(array).mapToInt(i -> i).toArray();
    }

    /**
     * [[xx],[xx],[xx]] 对应的矩阵/数组
     *
     * @param s [[xx],[xx],[xx]] 格式的字符串
     * @return 数组/矩阵
     */
    public static Object getMatrixFromString(String s) {
        String inside = strip(s);
        if (s.trim().contains("[")) {
            String[] sArray = inside.split("],");
            List<int[]> tmp = new ArrayList<>();
            for (int i = 0; i < sArray.length; i++) {
                tmp.add((int[]) getArrayFromString(strip(sArray[i])));
            }
            int[][] array = new int[sArray.length][];
            for (int i = 0; i < tmp.size(); i++) {
                array[i] = tmp.get(i);
            }
            return array;
        } else {
            return getArrayFromString(inside);
        }
    }

    private static Object getArrayFromString(String s) {
        String[] sArray = s.split(",");
        return Arrays.stream(sArray).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * strip a String like: [1,2,3,4] to 1,2,3,4,
     * often use for String generated from List.toString
     *
     * @param s String like [1,2,3,4]
     * @return String like 1,2,3,4
     */
    public static String strip(String s) {
        s = s.trim();
        int start = 0;
        int end = s.length() - 1;
        if (s.charAt(start) == '[') {
            start++;
        }
        if (s.charAt(end) == ']') {
            end--;
        }
        return s.substring(start, end + 1);
    }

    /**
     * swap the array[p1] and array[p2]
     *
     * @param array
     * @param p1
     * @param p2
     */
    public static void swap(Object array, int p1, int p2) {
        if (array instanceof int[]) {
            int[] a = (int[]) array;
            int tmp = a[p1];
            a[p1] = a[p2];
            a[p2] = tmp;
        } else if (array instanceof String[]) {
            String[] a = (String[]) array;
            String tmp = a[p1];
            a[p1] = a[p2];
            a[p2] = tmp;
        } else {
            throw new IllegalArgumentException("array type not included, swap failed.");
        }
    }

    /**
     * get a random value from start to end, start and end inclusively
     *
     * @param start
     * @param end
     * @return random value between start and end
     */
    public static int getRandom(int start, int end) {
        return start + new Random().nextInt(end - start + 1);
    }

    /**
     * print the current running dir, in case I forget how to get it
     *
     * @return current running dir
     */
    public static String getCurrentDir() {
        return System.getProperty("user.dir");
    }

    public static void print(Object array) {
        print(array, "\t");
    }

    /**
     * for print every element in array, default separator is "\t"
     *
     * @param array array to print
     */
    public static void print(Object array, String sep) {
        if (null == sep)
            sep = "\t";
        if (array instanceof int[]) {
            int[] a = (int[]) array;
            for (int i : a) {
                System.out.print(i + sep);
            }
        } else if (array instanceof String[]) {
            String[] a = (String[]) array;
            for (String s : a) {
                System.out.print(s + sep);
            }
        } else if (array instanceof int[][]) {
            int[][] a = (int[][]) array;
            for (Object o : a) {
                print(o, sep);
            }
        } else {
            throw new IllegalArgumentException("array type not included, print failed.");
        }
        System.out.println();
    }

    public static int max(int... params) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < params.length; i++) {
            max = Math.max(params[i], max);
        }
        return max;
    }

    public static int min(int... params) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < params.length; i++) {
            min = Math.min(params[i], min);
        }
        return min;
    }

    public static int GCD(int... params) {
        int result = params[0];
        for (int i = 1; i < params.length; i++) {
            result = GCD(result, params[i]);
        }
        return result;
    }

    public static int GCD(int a, int b) {
        if (a % b == 0) return b;
        return GCD(b, a % b);
    }
}
