package com.ssafy.d1029;

import java.io.*;
import java.util.*;

/* 15:57
 * 부분집합의 합 dp
 */
public class BOJ_1943 {

	static int N, sum, totalCount;
	static int [] price, count, coins;
	static boolean [] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0;tc<3;tc++) {
			int result;
			N = Integer.parseInt(br.readLine());
			totalCount = 0;
			sum = 0;
			price = new int[N];
			count = new int[N];
			int idx = 0;
			
			for(int i=0;i<N;i++) {
				StringTokenizer str = new StringTokenizer(br.readLine());
				int value = Integer.parseInt(str.nextToken());
				int cnt = Integer.parseInt(str.nextToken());
				totalCount += cnt;
				sum += value*cnt;
				price[i]=value;
				count[i]=cnt;
			}
			
			if(sum%2 != 0) {
				result = 0;
				sb.append(result).append("\n");
				continue;
			}
			
			coins = new int[totalCount];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<count[i];j++) {
					coins[idx++]=price[i];
				}
			}
			
			dp = new boolean[sum/2+1];
			dp[0] = true;
			

			result = (canDivideCoins() ? 1 : 0);
			sb.append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean canDivideCoins() {
		int targetSum = dp.length-1;
		for(int value : coins) {
			for(int j = targetSum; j>=value; j--) {
				dp[j] = dp[j] || dp[j-value];
			}
		}
		return dp[targetSum];
	}

}
