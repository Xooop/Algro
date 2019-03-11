package code;

import java.io.*;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        System.out.println(getNum("(()", "())"));
    }

//    public static int getTreeHeight(Node node) {
//        if (node.pleftChild == null && node.pRightChild == null) {
//            return 1;
//        }
//        if (node.pleftChild == null) {
//            return 1 + getTreeHeight(node.pRightChild);
//        }
//        if (node.pRightChild == null) {
//            return 1 + getTreeHeight(node.pleftChild);
//        }
//        return 1 + Math.max(getTreeHeight(node.pleftChild), getTreeHeight(node.pRightChild));
//    }
//    private static void process() throws IOException {
//        File file = new File("../files/ratings.dat");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        while (bufferedReader.ready()) {
//            String line = bufferedReader.readLine();
//            String[] words = line.split("::");
//            Arrays.stream(words).sorted()
//        }
//
//
//    }

    static int num = 0;

    public static int getNum(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        if ((ch1.length + ch2.length) % 2 != 0)
            return 0;
        int i = 0;
        int j = 0;
        int total = 0;
        process(ch1, ch2, i, j, total);
        return (num % (int) (Math.pow(10, 9) + 7));
    }

    private static void process(char[] ch1, char[] ch2, int i, int j, int total) {
        if (ch1.length == i || ch2.length == j) {
            num++;
            return;
        }
        if (ch1[i] == '(') {
            int tmp = total + 1;
            process(ch1, ch2, i + 1, j, tmp);
        } else if (ch1[i] == ')') {
            int tmp = total - 1;
            if (tmp < 0) {
                return;
            }
            process(ch1, ch2, i + 1, j, tmp);
        }

        if (ch2[j] == '(') {
            int tmp = total + 1;
            process(ch1, ch2, i, j + 1, tmp);
        } else if (ch2[j] == ')') {
            int tmp = total - 1;
            if (tmp < 0) {
                return;
            }
            process(ch1, ch2, i, j + 1, tmp);
        }
    }
}
