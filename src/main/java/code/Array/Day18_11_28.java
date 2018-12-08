package code.Array;

import entity.RLEIterator;
import entity.q900_My;

public class Day18_11_28 {
    public static void main(String[] args) {
        int[] test = new int[]{635,606,576,391,370,981,36,21,961,185,124,210,801,937,22,426,101,260,221,647,350,180,504,39,101,989,199,358,732,839,919,169,673,967,58,676,846,342,250,597,442,174,472,410,569,509,311,357,838,251};
//        int total = 0;
//        for (int i = 0; i < test.length; i++) {
//            if (i % 2 == 0) {
//                total += test[i];
//            }
//        }
//        System.out.println(total);
//        q900_My RLE = new q900_My(test);
        RLEIterator RLE = new RLEIterator(test);
        System.out.println(RLE.next(5039));
        System.out.println(RLE.next(62));
        System.out.println(RLE.next(3640));
        System.out.println(RLE.next(671));
        System.out.println(RLE.next(67));
        System.out.println(RLE.next(395));
        System.out.println(RLE.next(262));
        System.out.println(RLE.next(839));
        System.out.println(RLE.next(74));
        System.out.println(RLE.next(43));
        System.out.println(RLE.next(42));
        System.out.println(RLE.next(77));
        System.out.println(RLE.next(13));
    }
}
