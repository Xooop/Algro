package code.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19_03_26 {
    public static void main(String[] args) {
        System.out.println(q1013_My(new int[]{418,204,77,278,239,457,284,263,372,279,476,416,360,18}));
    }

    /**
     * int indices = 0;
     * HashMap<value, List<Integer>> valueAndIdxs
     *
     * for i -> 0 - time.len
     *     int value;
     *     value = time[i] % 60
     *     if(value == 0)
     *         indices++
     *         break
     *     if(valueAndIdxs.get(value)==null)
     *         List tmp = new ArrayList<>
     *         tmp.add(i)
     *         valueAndIdxs.put(value, tmp)
     *     else
     *         List tmp = valueAndIdxs.get(value)
     *         tmp.add(i)
     *         valueAndIdxs.put(value, tmp)
     *
     * for key in valueAndIdxs.keySet()
     *     if key == 30
     *         int len = valueAndIdxs.get(30).length
     *         int count = len * (len - 1)
     *         indices += count
     *     else if valueAndIdxs.contains(60 - key)
     *         indices += (valueAndIdxs.get(key).len * valueAndIdxs.get(60 -key).len)
     *
     * return indices / 2
     */
    private static int q1013_My(int[] time) {
        int indices = 0;
        HashMap<Integer, List<Integer>> valueAndIdxs = new HashMap<>();
        int zeros = 0;
        for (int i = 0; i < time.length; i++) {
            int value = time[i] % 60;
            if (value == 0) {
                zeros++;
                continue;
            }
            if (valueAndIdxs.get(value) == null) {
                List tmp = new ArrayList();
                tmp.add(i);
                valueAndIdxs.put(value, tmp);
            }else {
                List tmp = valueAndIdxs.get(value);
                tmp.add(i);
                valueAndIdxs.put(value, tmp);
            }
        }
        for(int key : valueAndIdxs.keySet()) {
            if (key == 30) {
                int len = valueAndIdxs.get(30).size();
                int count = len * (len - 1);
                indices += count;
            }else if (valueAndIdxs.containsKey(60 - key)) {
                indices += (valueAndIdxs.get(key).size() * valueAndIdxs.get(60 - key).size());
            }
        }
        return (indices / 2) + (zeros * (zeros - 1) / 2);
    }

    /**
     * 关键点是 new int[60] 的数组
     */
    private static int q1013_solution(int[] time) {

        return 0;
    }
}
