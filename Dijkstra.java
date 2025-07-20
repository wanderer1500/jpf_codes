import java.util.*;

public class Dijkstra {
    static int[] dijkstra(List<List<int[]>> adj, int src) {
        int V = adj.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            int node = u[0], d = u[1];
            if (d > dist[node]) continue;

            for (int[] edge : adj.get(node)) {
                int v = edge[0], w = edge[1];
                if (dist[node] + w < dist[v]) {
                    dist[v] = dist[node] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(new int[]{1, 2});
        adj.get(0).add(new int[]{2, 4});
        adj.get(1).add(new int[]{2, 1});
        adj.get(1).add(new int[]{3, 7});
        adj.get(2).add(new int[]{4, 3});

        int[] dist = dijkstra(adj, 0);

        assert dist[4] == 8 : "Shortest path to 4 should be 8";
    }
}
