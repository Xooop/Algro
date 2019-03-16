package Model;

public interface Graph {
    int getWeight(String startNode, String endNode);
    void addEdge(String startNode, String endNode, Integer weight);
}

