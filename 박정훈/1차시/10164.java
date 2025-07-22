import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][M+1];
		
		int ox = K % M; //O표시된 칸의 x인덱스
		int oy = K / M + 1; //O표시된 칸의 y인덱스
		if(ox == 0) {
			oy = K / M;
			ox = M;
		}
		
		//dp 채우기
		for(int i = 1; i <= N; i ++) {
			for(int j = 1; j <= M; j++) {
				if((K > 0) && (i > oy && j < ox) || (i < oy && j > ox) ) { //도달하지 못 함
					continue;
				}
				
				if(i == 1 || j == 1) { //시작점 옆, 아래는 1
					dp[i][j] = 1;
					continue;
				};
				
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // 위 아래 합산
			}
		}
		
		System.out.println(dp[N][M]);
	}
}