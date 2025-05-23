import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo = new HashMap<>();
    private final Map<V, Double> distTo = new HashMap<>();
    private final PriorityQueue<Vertex<V>> pq;
    private final V start;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.get(v.getData())));
        for (Vertex<V> v : graph.getAllVertices()) {
            distTo.put(v.getData(), Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);
        pq.add(graph.getVertex(start));
        while (!pq.isEmpty()) {
            Vertex<V> u = pq.poll();
            for (Map.Entry<Vertex<V>, Double> neighbor : u.getAdjacentVertices().entrySet()) {
                relax(u, neighbor.getKey(), neighbor.getValue(), graph);
            }
        }
    }

    private void relax(Vertex<V> u, Vertex<V> v, double weight, WeightedGraph<V> graph) {
        V uData = u.getData();
        V vData = v.getData();
        double newDist = distTo.get(uData) + weight;
        if (newDist < distTo.get(vData)) {
            distTo.put(vData, newDist);
            edgeTo.put(vData, uData);
            pq.remove(v);  // update priority
            pq.add(v);
        }
    }

    @Override
    public List<V> getPath(V target) {
        if (!distTo.containsKey(target)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V at = target; at != null && !at.equals(start); at = edgeTo.get(at)) {
            path.addFirst(at);
        }
        if (!path.isEmpty()) path.addFirst(start);
        return path;
    }
}
