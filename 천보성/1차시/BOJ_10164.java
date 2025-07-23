import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10164 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		/*경우의 수 문제
		 * 중간 거쳐야 하는 좌표까지의 경우의 수 * 중간부터 마지막 (n-1,n-1) 까지 경우의 수
		*/
		if(K != 0) {
			int x = K/M;
			int y = K%M;
			
			if(y != 0) x += 1;
			if(y == 0) y += M;
		
			int pre = countRoute(x, y);
			int post = countRoute(N-x+1, M-y+1);
			System.out.println(pre * post);
		}
		else {
			System.out.println(countRoute(N, M));
		}
	
	}

	private static int countRoute(int x, int y) {
		int[][] dp = new int[x][y];
		
		for(int i=0;i<x;i++) {
			dp[i][0] = 1;
		}
		for(int j=0;j<y;j++) {
			dp[0][j] = 1;
		}
		for(int i=1;i<x;i++) {
			for(int j=1;j<y;j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		
		return dp[x-1][y-1];
	}

}
