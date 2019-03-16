package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedGraph extends TypedGraph {

    private List<Vertex> nodes = new ArrayList<>();
    // Map: <$(startNode.name)-$(endNode.name), weight>
    private Map<String, Integer> edges = new HashMap<>();

    LinkedGraph(String[] vertexs) {
        for (String vertex : vertexs) {
            nodes.add(new Vertex(vertex));
        }
    }

    @Override
    public int getWeight(String startNode, String endNode) {
        if (this.type == GraphType.DIGRAPH)
            return edges.getOrDefault(getEdgeKey(startNode, endNode), NOT_CONNECTED);
        else
            return Math.min(edges.getOrDefault(getEdgeKey(startNode, endNode), NOT_CONNECTED),
                    edges.getOrDefault(getEdgeKey(endNode, startNode), NOT_CONNECTED));
    }

    @Override
    public void addEdge(String startNode, String endNode, Integer weight) {
        addAdjNode(startNode, endNode);
        edges.put(getEdgeKey(startNode, endNode), weight);
    }

    private String getEdgeKey(String startNodeName, String endNodeName) {
        String joiner = "-";
        return startNodeName + joiner + endNodeName;
    }

    @Override
    public Vertex getNode(String nodeName) {
        for (Vertex node : nodes) {
            if (node.name.equals(nodeName)) {
                return node;
            }
        }
        throw null;
    }

    private void addAdjNode(String startNode, String endNode) {
        Vertex start = getNode(startNode);
        Vertex end = new Vertex(endNode);
        end.next = start.next;
        start.next = end;
    }
}
