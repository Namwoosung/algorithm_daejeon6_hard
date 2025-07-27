import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		double m = 0;
		double sum = 0;
		double sd = 0;
		double result = Double.MAX_VALUE;
		//완전탐색
		for(int i = 0; i <= N - K; i++) { // 시작점
			sum = 0;
			
			for(int j = i; j < N; j++) { // 배열 끝까지 탐색
				sum += arr[j];
				
				int nowLen = j - i + 1;
				
				if( nowLen >= K) { // 길이가 K 이상이 된 경우 표준편차를 계산
					m = sum / nowLen;
					
					sd = 0;
					for(int k = i; k <= j; k++) {
						sd += Math.pow(arr[k] - m, 2);
					}
					sd /= nowLen;
					sd = Math.sqrt(sd);
					
					result = Math.min(result, sd);
				}
				
			}
		}
		System.out.print(result);
	}
}