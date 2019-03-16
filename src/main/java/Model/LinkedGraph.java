package Model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedGraph implements Graph {
    private static final int NOT_CONNECTED = Integer.MAX_VALUE;

    private List<Vertex> nodes = new ArrayList<>();
    // Map: <$(startNode.name)-$(endNode.name), weight>
    private Map<String, Integer> edges = new HashMap<>();

    public LinkedGraph() {
    }

    private String getEdgeKey(String startNodeName, String endNodeName) {
        if (StringUtils.isEmpty(startNodeName)) {
            throw new IllegalArgumentException("StartNodeName is empty");
        }
        if (StringUtils.isEmpty(endNodeName)) {
            throw new IllegalArgumentException("EndNodeName is empty");
        }
        String joiner = "-";
        return startNodeName + joiner + endNodeName;
    }

    @Override
    public int getWeight(String startNode, String endNode) {
        return edges.getOrDefault(getEdgeKey(startNode, endNode), NOT_CONNECTED);
    }

    @Override
    public void addEdge(String startNode, String endNode, Integer weight) {
        edges.put(getEdgeKey(startNode, endNode), weight);
    }

    private class Vertex {
        private String name;
        private List<Vertex> connectedNode;

        private Vertex(String name) {
            this.name = name;
            this.connectedNode= new ArrayList<>();
        }
    }
}
