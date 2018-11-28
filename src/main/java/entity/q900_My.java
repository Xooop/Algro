package entity;

import java.util.ArrayList;
import java.util.List;

public class q900_My {
    private List<Integer> array;

    public q900_My(int[] A) {
        array = new ArrayList<>();
        int i = 0;
        while (i < A.length) {
            for (int j = 0; j < A[i]; j++) {
                array.add(A[i + 1]);
            }
            i += 2;
        }
    }

    public int next(int n) {

        return 0;
    }

    public List<Integer> getArray() {
        return array;
    }

    public void setArray(List<Integer> array) {
        this.array = array;
    }
}
