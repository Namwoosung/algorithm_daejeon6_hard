import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static long[][] dp;
	static int[][] graph;
	static long answer;
	static int maxRoute;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		maxRoute = (1 << N);
		
		dp = new long[maxRoute][N];
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Long.MAX_VALUE;
		// dp 초기화
		for(int i = 0; i < maxRoute; i++) {
			for(int j = 0; j < N; j++) {
				dp[i][j] = Long.MAX_VALUE;
			}
		}
		
		tsp(1, 0, 0);
		
		System.out.println(answer);
	}
	
	static void tsp(int route, int nowNode, long nowDist) {
		if(dp[route][nowNode] <= nowDist) return;
		if(nowDist >= answer) return;
		
		dp[route][nowNode] = nowDist;
		
		if(route == (1 << N) - 1) {
			if(graph[nowNode][0] != 0) {
				answer = Math.min(answer, nowDist + graph[nowNode][0]);
			}
			return;
		}
		
		
		for(int i = 0; i < N; i++) {
			if((route & (1 << i)) != 0 || graph[nowNode][i] == 0) continue;
			
			tsp(route | (1 << i), i, nowDist + graph[nowNode][i]);
		}
	}
}