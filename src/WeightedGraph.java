import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices = new HashMap<>();

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V destination, double weight) {
        addVertex(source);
        addVertex(destination);
        vertices.get(source).addAdjacentVertex(vertices.get(destination), weight);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }
}
