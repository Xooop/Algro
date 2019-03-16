package Model;

public interface Graph {
    int NOT_CONNECTED = Integer.MAX_VALUE;

    int getWeight(String startNode, String endNode);

    void addEdge(String startNode, String endNode, Integer weight);

    Vertex getNode(String nodeName);
}

