import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N][M];
		int[] mustPos = {0, 0};
		if(K > 0) { // K가 존재하는 경우, 좌표를 계산
			mustPos[0] = (K-1) / M;
			mustPos[1] = (K-1) % M;
		}
		
		// dp로 전체 좌표의 경우의 수를 계산
		for(int i = 0; i < M; i++) {
			dp[0][i] = 1;
		}
		for(int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}
		for(int i = 1; i < N; i++){
			for(int j = 1; j < M; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		
		// K 존재 여부로 case를 나눔
		if( K == 0) {
			bw.write(String.valueOf(dp[N-1][M-1]));
		}else {
			bw.write(String.valueOf(dp[mustPos[0]][mustPos[1]] * dp[N - 1 - mustPos[0]][M - 1 - mustPos[1]]));
		}
		
		bw.flush();
		
	}
}
