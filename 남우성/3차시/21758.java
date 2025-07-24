import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//stand는 전체 꿀의 개수를 저장 <- 앞으로 계산에서 사용하기 위함
		int stand = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			stand += arr[i];
		}
		
		int result = 0;
		int now = 0;
		
		// case1: 꿀 - 벌 - 벌
		// 이 경우 [0]: 꿀, [N-1]: 벌 고정 해두고, 벌 하나만 중간에서 움직여 가며 최대값을 찾으면 됨
		now = stand + arr[0] - arr[N-1];
		for(int i = 1; i < N-1; i++) { // 벌 위치
			result = Math.max(result, now - arr[i]);
			now += arr[i];
		}
		
		// case2: 벌 - 꿀 - 벌
		// 이 경우 [0]: 벌, [N-1]: 벌 고정해두고, 꿀 하나만 움직여 가며 최대값을 검색
		// 최대값은 [1] ~ [N-2] 까지 다 더해놓고 +[i]로 계산
		now = stand - arr[0] - arr[N-1];
		for(int i = 1; i < N-1; i++) {
			result = Math.max(result, now + arr[i]);
		}

		// case3: 벌 - 벌 - 꿀
		// 이 경우 [0]: 벌, [N-1]: 꿀 고정 해두고, 벌 하나만 움직여가며 최대값을 탐색
		now = stand + arr[N-1] - arr[0];
		for(int i = N-2; i > 0; i--) {
			result = Math.max(result, now - arr[i]);
			now += arr[i];
		}
		
		System.out.print(result);
	}
}