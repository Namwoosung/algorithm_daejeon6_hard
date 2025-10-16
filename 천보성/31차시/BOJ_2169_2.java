import java.util.*;
import java.io.*;

public class BOJ_2169_2 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[M];

        dp[0] = map[0][0];
        for (int j = 1; j < M; j++) {
            dp[j] = dp[j-1] + map[0][j];
        }

        for (int i = 1; i < N; i++) {
            int[] left = new int[M];
            int[] right = new int[M];

            left[0] = dp[0] + map[i][0];
            
            for (int j = 1; j < M; j++) {
                left[j] = Math.max(dp[j], left[j-1]) + map[i][j];
            }

            right[M-1] = dp[M-1] + map[i][M-1];
            
            for (int j = M - 2; j >= 0; j--) {
                right[j] = Math.max(dp[j], right[j+1]) + map[i][j];
            }

        
            for (int j = 0; j < M; j++) {
                dp[j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[M-1]);
	}
}
