import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {
    public static int n;

    public static int[][] map;
    public static int[][] dp;

    static final int INF = 1000000000; 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][1 << n]; 
        // dp[cur][visited]
        // visited: 0 ~ (1 << n) - 1

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = tsp(0, 1); // 현재 도시: 0, 방문 상태: 0001 (0번만 방문)
        System.out.println(answer);
    }

    
    static int tsp(int current, int visited) {
        if(visited == (1 << n) -1) {
            if(map[current][0] == 0) return INF;
            return map[current][0];
        }

        if(dp[current][visited] != -1) return dp[current][visited];

        dp[current][visited] = INF;

        for (int i = 0; i < n; i++) {
            if((visited & (1 << i)) == 0 && map[current][i] != 0) {
                int cost = map[current][i] + tsp(i, visited | (1 << i));
                dp[current][visited] = Math.min(dp[current][visited], cost);
            }
        }

        return dp[current][visited];
    }
}
