import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo = new HashMap<>();
    private Set<V> marked = new HashSet<>();
    private final V start;

    public BreadthFirstSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(WeightedGraph<V> graph, V current) {
        Queue<V> queue = new LinkedList<>();
        queue.add(current);
        marked.add(current);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (Map.Entry<Vertex<V>, Double> entry : graph.getVertex(v).getAdjacentVertices().entrySet()) {
                V w = entry.getKey().getData();
                if (!marked.contains(w)) {
                    edgeTo.put(w, v);
                    marked.add(w);
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public List<V> getPath(V target) {
        if (!marked.contains(target)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V x = target; x != start; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
