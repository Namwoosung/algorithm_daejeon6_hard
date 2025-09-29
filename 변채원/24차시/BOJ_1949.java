import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1949 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[N + 1];
        dp = new int[N + 1][2]; // 0: 선택, 1: 비선택
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            dp[i][0] = n;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
        
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int start) {
        visited[start] = true;

        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);

            if(!visited[next]) {
                dfs(next);
                dp[start][0] += dp[next][1]; // 자신이 우수 마을 > 자식은 무조건 비우수
                dp[start][1] += Math.max(dp[next][0], dp[next][1]); // 자신이 비우수 마을
            }
        }
    }
}
