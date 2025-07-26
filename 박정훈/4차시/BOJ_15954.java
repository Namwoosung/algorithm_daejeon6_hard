import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15954 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] favorite = new int[N];
		double minSD = Double.MAX_VALUE; //최소 표준편차
		
		// 입력
		for(int i = 0; i < N; i++) {
			favorite[i] = Integer.parseInt(st.nextToken());
		}
		
		//탐색
		for(int i = K; i <= N; i++) { // K개 이상 선택
			for(int start = 0; start <= N - i; start++) {
				int end = start + i;
				int sum = 0;
				
				// 합계
				for(int j = start; j < end; j++) {
					sum += favorite[j];
				}
				
				double m = (double) sum / i; //평균
				
				// 분산
				double vSum = 0;
				for(int j = start; j < end; j++) {
					vSum += Math.pow(favorite[j] - m, 2);
				}
				double v = vSum / i;
				
				//표준편차
				minSD = Math.min(minSD, Math.sqrt(v));
			}
		}
		
		System.out.print(minSD);
	}
}