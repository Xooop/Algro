package ML;

import java.util.Arrays;
import java.util.Comparator;

public class KDTreeNode {
    double[] vector;
    int axis;
    KDTreeNode parent;
    KDTreeNode left;
    KDTreeNode right;
    boolean isChecked = false;

    public KDTreeNode(double[][] raw, int axis) {
        this.axis = axis;
        raw = Arrays.stream(raw).sorted(Comparator.comparingDouble(line -> line[axis])).toArray(double[][]::new);
        int mid = raw.length / 2;
        this.vector = raw[mid];
        if (mid == 0) return;
        int k = raw[0].length;
        int nextAxis = (axis + 1) % k;

        // 对左边的Tree进行构建
        double[][] leftRaw = Arrays.copyOfRange(raw, 0, mid);
        this.left = new KDTreeNode(leftRaw, nextAxis);
        left.parent = this;

        // 对右边的Tree进行构建
        if (mid + 1 == raw.length) return;
        double[][] rightRaw = Arrays.copyOfRange(raw, mid + 1, raw.length);
        this.right = new KDTreeNode(rightRaw, nextAxis);
        right.parent = this;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return "KDTreeNode{" +
                "vector=" + Arrays.toString(vector) +
                ", axis=" + axis +
                ", left=" + left +
                ", right=" + right +
                ", isChecked=" + isChecked +
                '}';
    }
}
