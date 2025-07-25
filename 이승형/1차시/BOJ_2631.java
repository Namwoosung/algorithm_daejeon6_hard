
import java.util.*;
import java.io.*;

public class BOJ_2631 {

	static int N;
	static int [] arr;
	static int [] dp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(N-LIS_DP());
		
	}
	
	static int LIS_DP() {
		int maxValue = 1;
		for(int i=0;i<N;i++) {
			dp[i]=1;
			for(int j=0;j<i;j++) {
				if(arr[j]<arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					maxValue = Math.max(maxValue, dp[i]);
				}
			}
		}
		return maxValue;
	}
	
	static int LIS_BinarySearch() {
		return -1;
	}
	
	static int binarySearch() {
		return -1;
	}
	
}
