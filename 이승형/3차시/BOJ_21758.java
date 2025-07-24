
import java.util.*;
import java.io.*;

public class BOJ_21758 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] arr = new int[N];
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(str.nextToken());
		}
		
		int [] leftSum = new int[N];
		int [] rightSum = new int[N];
		
		// left 누적합
		leftSum[0] = arr[0];
		for(int i=1;i<N; i++) {
			leftSum[i] = leftSum[i-1]+arr[i];
		}
		
		// right 누적합
		rightSum[N-1] = arr[N-1];
		for(int i=N-2; i>=0;i--) {
			rightSum[i] = rightSum[i+1]+arr[i];
		}
		
		int maxResult = Integer.MIN_VALUE;

		int leftBee = 0;
		int middleBee = 0;
		int rightBee = 0;

		// 통 - 벌 - 벌
		for(int i=1;i<N-1;i++) {
			rightBee = leftSum[N-1] - arr[N-1] - arr[i];
			middleBee = leftSum[i] - arr[i];
			maxResult = Math.max(maxResult, rightBee+middleBee);
		}

		// 벌 - 벌 - 통
		for(int i=1;i<N-1;i++) {
			leftBee = rightSum[0] - arr[0] - arr[i];
			middleBee = rightSum[i] - arr[i];
			maxResult  = Math.max(maxResult, leftBee + middleBee);
		}

		// 벌 - 통 - 벌
		int maxIdx = findMaxIndex(arr, 1, N-2);
		leftBee = rightSum[0]-arr[0]-rightSum[maxIdx+1];
		rightBee = leftSum[N-1]-arr[N-1]-leftSum[maxIdx-1];
		maxResult = Math.max(maxResult, leftBee+rightBee);
		System.out.println(maxResult);

	}


	static int findMaxIndex(int [] arr, int start, int end) {
		int maxIdx = start;
		int maxValue = arr[start];
		for(int i=start+1;i<=end;i++) {
			if(maxValue<arr[i]) {
				maxIdx = i;
				maxValue = arr[i];
			}
		}
		return maxIdx;
	}
}
