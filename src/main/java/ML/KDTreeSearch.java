package ML;

import java.util.*;

public class KDTreeSearch {
    public static void main(String[] args) {
        double[][] raw = new double[][]{{2, 3}, {5, 4}, {9, 6}, {4, 7}, {8, 1}, {7, 2}};
        KDTreeNode root = new KDTreeNode(raw, 0);
        List<double[]> result = findK(root, new double[]{3, 4.5}, 1);
        for (double[] vector: result) {
            for (int i = 0; i < vector.length; i++) {
                System.out.print(vector[i] + "\t");
            }
            System.out.println();
        }

    }

    public static KDTreeNode findArea(KDTreeNode root, double[] vector) {
        if (root.isLeaf()) return root;
        int axis = root.axis;
        if (vector[axis] == root.vector[axis]) return root;
        if (vector[axis] > root.vector[axis]) {
            return root.right != null ? findArea(root.right, vector) : root;
        } else {
            return root.left != null ? findArea(root.left, vector) : root;
        }
    }

    public static double[] getNearest(KDTreeNode root, double[] vector) {
        return findK(root, vector, 1).get(0);
    }

    public static List<double[]> findK(KDTreeNode root, double[] vector, int k) {
        List<double[]> result = new ArrayList<>();
        process(result, root, vector, k);
        return result;
    }

    public static void process(List<double[]> result, KDTreeNode root, double[] vector, int k) {
        // findArea
        KDTreeNode node = findArea(root, vector);
        add(result, node.vector, vector, k);
        node.isChecked = true;
        KDTreeNode parent = node.parent;
        // while parent is not null
        while (parent != null) {
            if (!parent.isChecked) {
                // check parent
                add(result, parent.vector, vector, k);
                parent.isChecked = true;
                int axis = parent.axis;
                // check if should check another node
                if (Math.abs(vector[axis] - parent.vector[axis]) < getMaxRadius(result, vector)) {
                    KDTreeNode another = vector[axis] - parent.vector[axis] > 0 ? parent.left : parent.right;
                    if (another != null) {
                        // another has no child node
                        if (another.left == null && another.right == null) {
                            add(result, another.vector, vector, k);
                            another.isChecked = true;
                        } else {
                            // another has child node
                            process(result, another, vector, k);
                        }
                    }
                }
            }
            parent = parent.parent;
        }
    }

    public static void add(List<double[]> result, double[] toCheck, double[] vector, int k) {
        if (result.size() == k && getDistance(toCheck, vector) > getMaxRadius(result, vector)) return;
        int i = 0;
        for (; i < result.size(); i++)
            if (getDistance(result.get(i), vector) > getDistance(toCheck, vector)) break;
        result.add(i, toCheck);
        if (result.size() > k) result.remove(result.size() - 1);
    }

    public static double getMaxRadius(List<double[]> result, double[] vector) {
        return result.size() > 0 ? getDistance(result.get(result.size() - 1), vector) : 0.0;
    }


    public static double getDistance(double[] v1, double[] v2) {
        double distance = 0.0;
        for (int i = 0; i < v1.length; i++) {
            distance += Math.pow(v1[i] - v2[i], 2);
        }
        return Math.sqrt(distance);
    }

}
