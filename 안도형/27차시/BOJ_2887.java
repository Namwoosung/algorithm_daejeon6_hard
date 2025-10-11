package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2887 {
    static class Planet {
        int id;
        int[] dist = new int[3];
        Planet(int id, int x, int y, int z) {
            this.id = id;
            dist[0] = x;
            dist[1] = y;
            dist[2] = z;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Planet[] planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(i, x, y, z);
        }

        ArrayList<int[]>[] node = new ArrayList[N];
        for (int i = 0; i < N; i++) 
        	node[i] = new ArrayList<>();

        for (int d = 0; d < 3; d++) {
            final int idx = d;
            Arrays.sort(planets, (a, b) -> a.dist[idx] - b.dist[idx]);
            for (int j = 0; j < N - 1; j++) {
                int u = planets[j].id;
                int v = planets[j + 1].id;
                int cost = Math.abs(planets[j].dist[idx] - planets[j + 1].dist[idx]);
                node[u].add(new int[]{v, cost});
                node[v].add(new int[]{u, cost});
            }
        }

        boolean[] visited = new boolean[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});

        long total = 0;
        int count = 0;

        while (!pq.isEmpty() && count < N) {
            int[] cur = pq.poll();

            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            total += cur[1];
            count++;

            for (int[] next : node[cur[0]]) {
                if (!visited[next[0]]) {
                    pq.add(new int[]{next[0], next[1]});
                }
            }
        }

        System.out.println(total);
    }
}
