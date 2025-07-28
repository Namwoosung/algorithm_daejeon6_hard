import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];
            boolean[] visited = new boolean[N + 1];

            for (int j = 0; j < N - 1; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());


            while (node1 != 0) {
                visited[node1] = true;
                node1 = parent[node1];
            }

            while (node2 != 0) {
                if (visited[node2]) {
                    sb.append(node2).append("\n");
                    break;
                }
                node2 = parent[node2];
            }
        }

        System.out.print(sb);
    }
}
