package Model;

public abstract class TypedGraph implements Graph{
    GraphType type = GraphType.UNDIGRAPH;

    protected void setGraphType(GraphType type) {
        this.type = type;
    }
}
