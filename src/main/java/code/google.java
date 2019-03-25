package code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class google {

    public static void main(String[] args) throws IOException {
//        String firstLine = "N(1的阶乘到N的阶乘)" + "\t" + "e 的准确位数" + "\t" + "e 的准确值" + "\n";
//        final String filePath = "files/raw.dat";
//        contentToFile(filePath, firstLine);
//        BigDecimal e = BigDecimal.ONE;
//        BigDecimal fact = BigDecimal.ONE;
//        final int PRECITION = 10000;
//        MathContext mc = new MathContext(PRECITION);
//        int N = 2000;
//        int iterNum = 50;
//        int M = N / iterNum;
//        for (int i = 0; i < M; i++) {
//            BigDecimal tmp = new BigDecimal(i * iterNum);
//            for (int j = 1; j < iterNum; j++) {
//                BigDecimal idx = tmp.add(new BigDecimal(j));
//                fact = fact.multiply(idx);
//                e = e.add(BigDecimal.ONE.divide(fact, mc), mc);
//            }
//            char[] e1 = e.toString().toCharArray();
//            fact = fact.multiply(tmp.add(new BigDecimal(iterNum)));
//            e = e.add(BigDecimal.ONE.divide(fact, mc), mc);
//            char[] e2 = e.toString().toCharArray();
//            int k;
//            for (k = 0; k <= PRECITION; k++) {
//                if (e1[k] != e2[k]) {
//                    k--;
//                    break;
//                }
//            }
//            char[] rst = new char[k + 1];
//            System.arraycopy(e1, 0, rst, 0, k + 1);
//            String content = tmp.add(new BigDecimal(iterNum)).toString() + "\t"
//                    + (k + 1) + "\t"
//                    + String.valueOf(rst) + "\n";
//            contentToFile(filePath, content);
//        }
    }

    private static String getE(int PRECISION, int N) {
        BigDecimal e = BigDecimal.ONE;
        BigDecimal fact = BigDecimal.ONE;
        MathContext mc = new MathContext(PRECISION);
        for (int i = 1; i < N; i++) {
            fact = fact.multiply(new BigDecimal(i));
            e = e.add(BigDecimal.ONE.divide(fact, mc), mc);
        }
        char[] e1 = e.toString().toCharArray();
        fact = fact.multiply(new BigDecimal(String.valueOf(N)));
        e = e.add(BigDecimal.ONE.divide(fact, mc), mc);
        char[] e2 = e.toString().toCharArray();
        int i;
        for (i = 0; e1[i] == e2[i]; i++);
        char[] rst = new char[i];
        System.arraycopy(e1, 0, rst, 0, i);
        return String.valueOf(rst);
    }

    private static void contentToFile(String filePath, String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    /**
     * 找出自然对数 e 出现的的第一个十位的质数
     */
    public static BigDecimal getTheNumber() {
        // calculate the e string

        // get the 10 number string and test if it is a zhishu

        return null;
    }


}
