import java.util.*;

public class BuggyDijkstra {
    static int[] dist;
    static List<List<int[]>> adj;

    static class Worker extends Thread {
        int node;

        Worker(int node) {
            this.node = node;
        }

        public void run() {
            for (int[] edge : adj.get(node)) {
                int v = edge[0], w = edge[1];

                //  UNSAFE update to shared array without synchronization
                if (dist[node] + w < dist[v]) {
                    dist[v] = dist[node] + w;  // race condition
                }
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(new int[]{1, 2});
        adj.get(0).add(new int[]{2, 4});
        adj.get(1).add(new int[]{2, 1});
        adj.get(1).add(new int[]{3, 7});
        adj.get(2).add(new int[]{4, 3});

        dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        //  Launch workers unsafely
        Thread t1 = new Worker(0);
        Thread t2 = new Worker(1);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  JPF might catch unexpected result due to race
        assert dist[4] == 8 : "Expected shortest path to 4 is 8";
    }
}
