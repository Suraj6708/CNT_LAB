import java.util.*;

public class Dijkstra {
    static class Node {
        int v, w;
        Node(int v, int w) { this.v = v; this.w = w; }
    }

    public static void dijkstra(List<List<Node>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.w - b.w);
        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v;

            for (Node next : graph.get(u)) {
                int v = next.v, w = next.w;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " -> " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Node(1, 4));
        graph.get(0).add(new Node(2, 1));
        graph.get(2).add(new Node(1, 2));
        graph.get(1).add(new Node(3, 1));
        graph.get(2).add(new Node(3, 5));
        graph.get(3).add(new Node(4, 3));

        dijkstra(graph, 0);
    }
}
