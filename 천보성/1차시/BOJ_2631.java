import java.util.*;
import java.io.*;

public class BOJ_2631 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int max = 0;
		
		//LIS 알고리즘
		for(int i=0;i<N;i++) {
			dp[i] = 1; // 자기 자신 길이
			for(int j=0;j<i;j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		// 전체인원에서 LIS를 빼면 최솟값
		System.out.println(N - max);
	}
}                        