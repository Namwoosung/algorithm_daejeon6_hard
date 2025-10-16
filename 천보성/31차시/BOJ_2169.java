import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2169 {
	
	static final int INIT_NUM = -10000000;
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[N][M][3];
		
	
		for(int i=0;i<M;i++) {
			Arrays.fill(dp[0][i], INIT_NUM);
		}
		
		dp[0][0][1] = map[0][0];
		
		for(int j=1;j<M;j++) {
			dp[0][j][1] =  dp[0][j-1][1] + map[0][j]; 
		}
		
		
		
		for(int i=1;i<N;i++) {
			
			dp[i][0][0] =  Math.max(dp[i-1][0][0], Math.max(dp[i-1][0][1], dp[i-1][0][2])) + map[i][0];
			dp[i][0][1] = dp[i][0][0];
			
			for(int j=0;j<M;j++) {
				
				dp[i][j][0] = Math.max(dp[i-1][j][0], Math.max(dp[i-1][j][1], dp[i-1][j][2])) + map[i][j];			
				dp[i][j][1] = Math.max(dp[i][j-1][0], dp[i][j-1][1]) + map[i][j];					
			}
			
			dp[i][M-1][2] = dp[i][M-1][0];
			
			for(int j=M-2;j>=0;j--) {	
			
				dp[i][j][2] = Math.max(dp[i][j+1][0], dp[i][j+1][2]) + map[i][j];
			
			}
		}
		
		System.out.println(Math.max(dp[N-1][M-1][0], Math.max(dp[N-1][M-1][1], dp[N-1][M-1][2])));
	}
}
