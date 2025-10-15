package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2098 {
	public static int N, bitN;
	public static int[][] dp, arr;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        bitN = (1 << N) - 1;
        arr = new int[N][N];
        dp = new int[N][bitN];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++)
            	arr[i][j] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i < N; i++) {
        	Arrays.fill(dp[i], -1);
        }
        
        System.out.println(solution(0, 1));
	}
	
	public static int solution(int start, int count) {
		if(count == bitN) {
			if(arr[start][0] == 0) return 100000000;
			return arr[start][0];
		}
		
		if(dp[start][count] != -1) return dp[start][count];
		int best = 100000000;
		
		for(int i = 0; i < N; i++) {
			if(arr[start][i] == 0 || (count & (1 << i)) != 0) continue;
			int next = count | (1 << i);
			best = Math.min(best, solution(i, next) + arr[start][i]);
		}
		
		return dp[start][count] = best;
	}
}
