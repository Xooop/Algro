package Model;

import java.io.*;

public class TestGraph {
    public static void main(String[] args) throws IOException {
        File file = new File("files/GraphAttr.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        // nodes num, edges num
        String[] nums = bufferedReader.readLine().split(" ");
        int nodeNum = Integer.valueOf(nums[0]);
        int edgeNum = Integer.valueOf(nums[1]);
        // all the nodes name
        String[] nodes = bufferedReader.readLine().split(" ");
        Graph G = new MatrixGraph(nodeNum, nodes);
        // all the edges, A B 1
        for (int i = 0; i < edgeNum; i++) {
            String[] edges = bufferedReader.readLine().split(" ");
            G.addEdge(edges[0], edges[1], Integer.valueOf(edges[2]));
        }
        System.out.println(G.getWeight("2", "5"));
    }
}
