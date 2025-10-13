import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;
    static List<int[]> vx = new ArrayList<>();
    static List<int[]> vy = new ArrayList<>();
    static List<int[]> vz = new ArrayList<>();

    static int findParent(int x) {
        if (parent[x] != x)
            parent[x] = findParent(parent[x]);
        return parent[x];
    }

    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            vx.add(new int[]{x, i});
            vy.add(new int[]{y, i});
            vz.add(new int[]{z, i});
        }

        vx.sort(Comparator.comparingInt(a -> a[0]));
        vy.sort(Comparator.comparingInt(a -> a[0]));
        vz.sort(Comparator.comparingInt(a -> a[0]));

        // priority queue (min-heap) - smallest cost first
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n - 1; i++) {
            pq.offer(new int[]{Math.abs(vx.get(i)[0] - vx.get(i + 1)[0]), vx.get(i)[1], vx.get(i + 1)[1]});
            pq.offer(new int[]{Math.abs(vy.get(i)[0] - vy.get(i + 1)[0]), vy.get(i)[1], vy.get(i + 1)[1]});
            pq.offer(new int[]{Math.abs(vz.get(i)[0] - vz.get(i + 1)[0]), vz.get(i)[1], vz.get(i + 1)[1]});
        }

        int result = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int cost = edge[0];
            int a = edge[1];
            int b = edge[2];

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }

        System.out.println(result);
    }
}
