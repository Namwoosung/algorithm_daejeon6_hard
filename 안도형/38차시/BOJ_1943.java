package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1943 {
	public static int N, targetMoney;
	public static boolean isHalf;
	public static ArrayList<int[]> arr;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        
        for(int t = 0; t < 3; t++) {
        	int[] dp = new int[50001];
        	N = Integer.parseInt(br.readLine());
        	int total = 0;
        	isHalf = false;
        	arr = new ArrayList<>();
        	
	        for(int i = 0; i < N; i++) {
	        	st = new StringTokenizer(br.readLine());
	        	int a = Integer.parseInt(st.nextToken());
	        	int b = Integer.parseInt(st.nextToken());
	        	
	        	arr.add(new int[] {a, b});
	        	total += a * b;
	        }
	        
	        if (total % 2 != 0) {
	            System.out.println(0);
	            continue;
	        }
	        
	        targetMoney = total / 2;
	        outer:
	        for(int[] coins : arr) {
	        	for(int i = 1; i <= coins[1]; i <<= 1) {
	        		coins[1] -= i;
	        		int coin = coins[0] * i;
	        		for(int j = targetMoney; j >= coin; j--) {
	        			dp[j] = Math.max(dp[j], dp[j - coin] + coin);
	        			if(dp[j] == targetMoney) {
	        				isHalf = true;
	        				break outer;
	        			}
	        		}
	        	}
	        }
	        
        	System.out.println(isHalf ? 1 : 0);
        }
	}
}
