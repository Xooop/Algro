package code;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/28
 */
public class Day18_10_28_q717_marked {
    public static void main(String[] args) {
//        System.out.println(q717_My(new int[]{1,1,1,0}));
        System.out.println(isOneBitCharacter(new int[]{1,1,1,1,0,0,1,0}));
    }

    private static boolean q717_My(int[] bits) {
        if (bits.length == 1) {
            return bits[0] == 0;
        }
        return canRepresent(bits, 0, bits.length - 2);
    }

    private static boolean canRepresent(int[] bits, int start, int end) {
        if (start == end) {
            if (bits[start] == 0) {
                return true;
            } else {
                return false;
            }
        }

        if (start + 1 == end) {
            if ((bits[start] == 1 && bits[start + 1] == 1) || (bits[start] == 1 && bits[start + 1] == 0)) {
                return true;
            }
        }

        if (bits[start] == 0 && canRepresent(bits, start + 1, end)) {
            return true;
        }
        if (bits[start] == 1 && bits[start + 1] == 1 && canRepresent(bits, start + 2, end)) {
            return true;
        }
        if (bits[start] == 1 && bits[start + 1] == 0 && canRepresent(bits, start + 2, end)) {
            return true;
        }

        return false;
    }

    public static boolean isOneBitCharacter(int[] bits) {
        int i = bits.length - 2;
        while (i >= 0 && bits[i] > 0) i--;
        return (bits.length - i) % 2 == 0;
    }
}
