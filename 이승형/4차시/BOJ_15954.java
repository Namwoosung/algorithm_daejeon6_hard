
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/*
 * 슬라이딩 윈도우
 * N의 구간에서 K만큼의 슬라이딩 윈도우로 표준편차 계산
 * 
 * 평균 계산 -> 편차 계산(값-평균) -> 분산(==편차 제곱(양수)) -> 표준편차(분산의 제곱근) 
 * 구간 설정, 표준편차구하기 메서드 호출, Math.min으로 최신화 
 * */
public class BOJ_15954 {
	
	static int N, K;
	static int arr [];
	static long arrSum [];
	
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(str.nextToken());
		K = Integer.parseInt(str.nextToken());
		
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		arrSum = new long[N];
		
		arrSum[0]=arr[0];
		for(int i=1;i<N;i++) {
			arrSum[i] = arrSum[i-1]+arr[i];
		}
		
		double minValue = Double.MAX_VALUE;
		for(int k=K;k<=N;k++) {
			for(int i=0;i<=N-k;i++) {
				double standardDeviation = getStandardDeviation(i,i+k, k);
				minValue = Math.min(minValue, standardDeviation);
			}
		}
		System.out.println(String.format("%.12f",minValue));
		
	}
	
	// end는 포함 안함
	static double getStandardDeviation(int start, int end, int k) {
		long sum = (start == 0) ? arrSum[end-1] : arrSum[end-1]-arrSum[start-1];
		double average = (double)(sum)/(double)k;
		double tempSum = 0;
		for(int i=start;i<end;i++) {
			tempSum+=Math.pow((double)arr[i] - average,2);
		}
		double variance = tempSum / k;
		
		return Math.sqrt(variance);
	}
}
