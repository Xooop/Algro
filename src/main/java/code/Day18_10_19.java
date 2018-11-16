package code;

/**
 * @author: gsj
 * @version: 1.0
 * @date: created on 2018/10/19
 */
public class Day18_10_19 {
    public static void main(String[] args) {
        System.out.println(q495_My(new int[]{1, 2}, 2));
    }

    private static int q495_My(int[] timeSeries, int duration) {

        int total = 0;
        int lastTime = 0;
        for (int timeSery : timeSeries) {
            if (timeSery < lastTime) {
                total += timeSery - lastTime + duration;
            } else {
                total += duration;
            }
            lastTime = timeSery + duration;
        }
        return total;
    }

    private static int[] q667_My(int n, int k) {
        

        return null;
    }
}
