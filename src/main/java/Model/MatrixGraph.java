package Model;


public class MatrixGraph extends TypedGraph {
    Vertex[] nodes;
    int[][] edges;

    public MatrixGraph(Integer nodeNum, String[] vertexs) {
        edges = new int[nodeNum][nodeNum];
        initEdges();
        nodes = new Vertex[nodeNum];
        for (int i = 0; i < vertexs.length; i++) {
            nodes[i] = new Vertex(vertexs[i]);
        }
    }

    private void initEdges() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[0].length; j++) {
                edges[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    @Override
    public Vertex getNode(String nodeName) {
        return nodes[getNodeIdx(nodeName)];
    }

    private int getNodeIdx(String nodeName) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].name.equals(nodeName)) {
                return i;
            }
        }
        throw null;
    }

    @Override
    public int getWeight(String startNode, String endNode) {
        return edges[getNodeIdx(startNode)][getNodeIdx(endNode)];
    }

    @Override
    public void addEdge(String startNode, String endNode, Integer weight) {
        if (type == GraphType.DIGRAPH)
            edges[getNodeIdx(startNode)][getNodeIdx(endNode)] = weight;
        else
            edges[getNodeIdx(startNode)][getNodeIdx(endNode)] = weight;
            edges[getNodeIdx(endNode)][getNodeIdx(startNode)] = weight;
    }
}
