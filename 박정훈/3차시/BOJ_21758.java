import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21758 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] honey = new int[N];
		int[] sumR = new int[N]; //오른쪽 누적합
		int[] sumL = new int[N]; //왼쪽 누적합
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 꿀 입력
		for(int i = 0; i < N; i++) {
			honey[i] = Integer.parseInt(st.nextToken());
			// 오른쪽 누적합
			if(i == 0) {
				sumR[i] = honey[i];
			} else {
				sumR[i] = sumR[i-1] + honey[i];
			}
		}
		
		//왼쪽 누적합
		for(int i = N - 1; i >= 0; i--) {
			if(i == N - 1) {
				sumL[i] = honey[i]; 
			} else {
				sumL[i] = sumL[i+1] + honey[i];
			}
		}
		
		//벌통이 가운데 -> 벌은 무조건 양끝에서 출발하는 것이 최대 벌통은 꿀의 양이 가장 큰 곳
		int maxIndex = 0;
		int maxHoney = 0;
		for(int i = 1; i < N - 1; i++) { // 꿀의 양이 가장 많은 곳
			if(maxHoney < honey[i]) {
				maxHoney = honey[i];
				maxIndex = i;
			}
		}
		// 벌의 시작점에서 벌통까지의 누적합
		max = (sumL[0] - honey[0] - sumL[maxIndex + 1]) + (sumR[N - 1] - honey[N - 1] - sumR[maxIndex - 1]);
		
		//벌통이 왼쪽 -> 한 마리 벌은 오른쪽 고정 다른 한 마리가 순회하며 최대값 갱신
		for(int i = 1; i < N - 1; i++) {
			int sum = sumR[N-1] - honey[N-1] - honey[i]; // 한 마리의 벌은 무조건 오른쪽 끝
			max = Math.max(max, sum + sumR[i] - honey[i]);
		}
		
		//벌통이 오른쪽 -> 한 마리 벌은 왼쪽 고정 다른 한 마리가 순회하며 최대값 갱신
		for(int i = 1; i < N - 1; i++) {
			int sum = sumL[0] - honey[0] - honey[i]; // 한 마리의 벌은 무조건 오른쪽 끝
			max = Math.max(max, sum + sumL[i] - honey[i]);
		}
		
		System.out.print(max);
	}
}