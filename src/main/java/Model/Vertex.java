package Model;

public class Vertex {
    public String name;
    public Vertex next;
    public Integer distance;

    public Vertex(String name) {
        this.name = name;
        this.next = null;
    }
}
