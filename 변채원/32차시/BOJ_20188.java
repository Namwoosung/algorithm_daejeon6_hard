import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20188 {
    public static int n;
    public static int[] depth;

    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;

    public static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        depth = new int[n + 1];
        graph = new ArrayList[n + 1];
        
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        
        result = 0;
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            calcDepth(i);
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(depth[i] + " ");
        }

        System.out.println(result);
    }

    public static void calcDepth(int start) {
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> one = new ArrayDeque<>();
        q.add(new int[] {start, 0});

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int current = node[0];
            int d = node[1];

            visited[current] = true;
            if(depth[current] < d)
                depth[current] = d;

            for (int i = 0; i < graph[current].size(); i++) {
                int next = graph[current].get(i);

                if(!visited[next]) {
                    visited[next] = true;
                    if(current != 1) {
                        q.add(new int[] {next, d + 1});
                        result += Math.max(depth[start], depth[next]);
                    }
                    else 
                        one.add(new int[] {next, d + 1});
                }
            }
        }

        while (!one.isEmpty()) {
            int[] node = one.poll();
            int current = node[0];
            int d = node[1];

            visited[current] = true;

            for (int i = 0; i < graph[current].size(); i++) {
                int next = graph[current].get(i);

                if(!visited[next]) {
                    visited[next] = true;
                    result += Math.max(depth[start], depth[next]);
                    one.add(new int[] {next, d + 1});
                }
            }
        }
    }
}