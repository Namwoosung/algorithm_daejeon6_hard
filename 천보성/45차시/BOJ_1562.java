import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1562 {
	
	static final long MOD = 1000000000L;
	static final int END = 1 << 10;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][][] dp = new long[N+1][10][END];
		
		for(int i=1;i<=9;i++) {
			dp[1][i][1 << i] = 1;
		}
		for(int i=2;i<=N;i++) {
			for(int j=0;j<=9;j++) {
				for(int k=0;k<END;k++) {
					
					if((k & (1 << j)) == 0) continue;
					
					int preK = k ^ (1 << j);
					
					if(j == 0) {
						dp[i][j][k] = (dp[i][j][k] + dp[i-1][1][k]) % MOD;
						dp[i][j][k] = (dp[i][j][k] + dp[i-1][1][preK]) % MOD;
					}
					else if(j == 9) {						
						dp[i][j][k] = (dp[i][j][k] + dp[i-1][8][k]) % MOD;
						dp[i][j][k] = (dp[i][j][k] + dp[i-1][8][preK]) % MOD;
					}
					else {
						dp[i][j][k] = (dp[i][j][k] + dp[i-1][j-1][k]) % MOD;
						dp[i][j][k] = (dp[i][j][k] + dp[i-1][j-1][preK]) % MOD;

						dp[i][j][k] = (dp[i][j][k] + dp[i-1][j+1][k]) % MOD;
						dp[i][j][k] = (dp[i][j][k] + dp[i-1][j+1][preK]) % MOD;
					}
				}
			}
		}
		
		long ans = 0;
		int target = (1 << 10) - 1;
		
		for(int i=0;i<=9;i++) {
			ans = (ans + dp[N][i][target]) % MOD;
		}
		
		System.out.println(ans);
	 }
	 
}
