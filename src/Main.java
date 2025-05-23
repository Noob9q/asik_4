public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "E", 10);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "Z", 6);
        graph.addEdge("E", "Z", 3);

        Search<String> bfs = new BreadthFirstSearch<>(graph, "A");
        System.out.println("BFS Path from A to Z: " + bfs.getPath("Z"));

        Search<String> dijkstra = new DijkstraSearch<>(graph, "A");
        System.out.println("Dijkstra Path from A to Z: " + dijkstra.getPath("Z"));
    }
}
