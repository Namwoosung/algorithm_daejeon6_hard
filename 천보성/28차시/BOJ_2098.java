import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {
	
	static int N;
	static int[][] cost,dp;
	static final int INF_1 = 2000000;
	static final int INF_2 = 10000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N][N];
		dp = new int[N][(1 << N) - 1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<N;j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			Arrays.fill(dp[i], INF_2);			
		}
		
		System.out.println(dfs(0,1));
		
	}
	public static int dfs(int now, int bit) {
		
		if(bit == (1<<N) - 1) {
			if(cost[now][0] == 0) {
				return INF_1;
			}
			return cost[now][0];
		}
		
		if(dp[now][bit] != INF_2) {
			return dp[now][bit];
		}
		
		for(int i=0;i<N;i++) {
			if((bit & (1 << i)) == 0 && cost[now][i] != 0) {
				
				dp[now][bit] = Math.min(dp[now][bit], cost[now][i] + dfs(i, bit | (1<<i)));
			}
		}
		return dp[now][bit];
	}
}                                                                                                                   
