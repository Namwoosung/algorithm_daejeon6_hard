package com.ssafy.d1020;

import java.io.*;
import java.util.*;


//2, 3, 4, 5, 6, 7
	// 큰
		// 1, 7, 4, 5, 9, 8
	// 작은
		// 1, 7, 4, 2, 0, 8
		
		// 가장 큰 수 -> 성냥을 적게 쓰면서 크고, 많은 수 생성
			// n>=4 
				// 홀수일때 7 , 나머지 1 
				// 짝수일 때 1
		// 가장 작은 수 -> 성냥을 많이 쓰면서 작고, 적은 수 생성
			// n>=8 
				// 첫 자리 1, 나머지 0, 나머지 작은 수
			// n=7 -> 8
			// n=6 -> 0
			// n=5 -> 2
			// n=4 -> 4
			// n=3 -> 7
			// n=2 -> 1
		
public class BOJ_3687 {
	
	static int [] nums;
	static long [] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			int N = Integer.parseInt(br.readLine());
			String maxNum = getMaxNumber(N);
			
			dp = new long[101];
			Arrays.fill(dp, Long.MAX_VALUE);
			buildMinNumber(N);
			long minNum = dp[N];
			
			sb.append(minNum).append(" ").append(maxNum).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	
	static String getMaxNumber(int N) {
		StringBuilder result = new StringBuilder();
		if(N%2!=0) {
			N-=3;
			result.append("7");
		}
		while(N!=0) {
			result.append("1");
			N-=2;
		}
		return result.toString();
	}
	
	static void buildMinNumber(int N) {
		dp[2]=1;
		dp[3]=7;
		dp[4]=4;
		dp[5]=2;
		dp[6]=6;  // 첫째 자리는 0이 될수 없으니..
		dp[7]=8;
		dp[8]=10;
		
		long [] cost = new long[] {0, 0, 1, 7, 4, 2, 0, 8};
		
		for(int i=9;i<=N;i++) {
			for(int j=2;j<cost.length;j++) {
				dp[i]=Math.min(dp[i], dp[i-j]*10 +cost[j]);
			}
		}
		
	}
}
