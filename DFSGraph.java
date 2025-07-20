import java.util.*;

public class DFSGraph {
    static void dfs(Graph g, int u, boolean[] visited) {
        visited[u] = true;
        for (int v : g.adj.get(u)) {
            if (!visited[v])
                dfs(g, v, visited);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1); g.addEdge(0, 2);
        g.addEdge(1, 3); g.addEdge(2, 4);

        boolean[] visited = new boolean[5];
        dfs(g, 0, visited);

        assert visited[3] : "Node 3 should be visited";
        assert visited[4] : "Node 4 should be visited";
    }
}

// Graph class definition
class Graph {
    int V;
    List<List<Integer>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);  // Undirected
    }
}

