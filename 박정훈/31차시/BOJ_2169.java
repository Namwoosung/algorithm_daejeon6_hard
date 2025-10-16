import java.io.*;
import java.util.*;

public class BOJ_2169 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] table = new int[N][M];
        int[][] dp = new int[N][M];
        
        // 입력
        for(int r = 0; r < N; r++) {
        	st = new StringTokenizer(br.readLine());
        	for(int c = 0; c < M; c++) {
        		table[r][c] = Integer.parseInt(st.nextToken());
        	}
        }
        
        dp[0][0] = table[0][0];
        for(int c = 1; c < M; c++) {
            dp[0][c] = dp[0][c-1] + table[0][c];
        }
        
        for(int r = 1; r < N; r++) {
        	int[] leftToRight = new int[M];
        	int[] rightToLeft = new int[M];
        	
        	// 왼쪽 -> 오른쪽 가는 경우
        	leftToRight[0] = dp[r-1][0] + table[r][0];
        	for(int c = 1; c < M; c++) {
        		// 위 -> 아래, 왼 -> 오 중 더 큰 값
        		leftToRight[c] = Math.max(dp[r-1][c], leftToRight[c-1]) + table[r][c];
        	}
        	
        	// 오른쪽 -> 왼쪽 가는 경우
        	rightToLeft[M-1] = dp[r-1][M-1] + table[r][M-1];
        	for(int c = M-2; c >= 0; c--) {		
        		// 위 -> 아래, 오 -> 왼 중 더 큰 값
        		rightToLeft[c] = Math.max(dp[r-1][c], rightToLeft[c+1]) + table[r][c];
        	}
        	
            // 최댓값 선택
            for(int c = 0; c < M; c++) {
                dp[r][c] = Math.max(leftToRight[c], rightToLeft[c]);
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}