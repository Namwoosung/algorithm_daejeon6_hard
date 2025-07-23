import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21758 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] prefixSum = new int[N+1];
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			prefixSum[i+1] = prefixSum[i] + arr[i];
		}
		
		//벌통이 우측 끝에 있을때, 벌 한마리는 좌측 끝 고정
		//벌통이 좌측에 있을때, 벌 한마리는 우측 끝 고정
		int max = 0;
		
		for(int i=1;i<N;i++) {
			int bee1 = prefixSum[N] - arr[0] - arr[i];
			int bee2 = prefixSum[N] - prefixSum[i+1];
			
			int bee3 = prefixSum[N] - arr[N-1] - arr[N-1-i];
			int bee4 = prefixSum[N-1-i];
			max = Math.max(max, Math.max(bee1 + bee2, bee3 + bee4));
		}
		
		//벌통이 가운데 있으려면 N이 3이면서 가운데 수가 가장 커야함
		if(N==3) {
			max = Math.max(max, arr[1] * 2);
		}
		
		System.out.println(max);
		
	}

}
