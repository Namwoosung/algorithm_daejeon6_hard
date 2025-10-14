import java.io.*;
import java.util.*;

public class BOJ_2098 {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 16000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][1 << N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1); // -1은 계산 하지 않은 상태
        }
        
        System.out.println(tsp(0, 1));
    }
    
    static int tsp(int cur, int visited) {
        // 모든 도시 방문 완료
        if (visited == (1 << N) - 1) {
            return W[cur][0] == 0 ? INF : W[cur][0]; // 0이면 돌아갈 수 없는 경로
        }
        
        if (dp[cur][visited] != -1) { // 이미 계산된 경로
            return dp[cur][visited];
        }
        
        dp[cur][visited] = INF; // INF는 계산 중 표시
        
        for (int next = 0; next < N; next++) {
            // 이미 방문했거나 갈 수 없는 도시
            if ((visited & (1 << next)) != 0 || W[cur][next] == 0) {
                continue;
            }
            
            int cost = W[cur][next] + tsp(next, visited | (1 << next));
            dp[cur][visited] = Math.min(dp[cur][visited], cost);
        }
        
        return dp[cur][visited];
    }
}