import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			
			int maxH = 0;
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, arr[i]);
			}
			
			// 물울 주기 위해 필요한 홀수 날짜와 짝수 날짜의 개수를 카운팅
			int oddCnt = 0;
			int evenCnt = 0;
			for(int i = 0; i < N; i++) {
				int diff = maxH - arr[i];
				
				if(diff % 2 == 1) { // 차이가 홀수면 무조건 홀 수 중 하루 필요
					oddCnt++;
				}
				
				evenCnt += diff/2; // 일단 전부다 짝수날 준다고 가정
			}
			
			int result = Integer.MAX_VALUE;
			if(oddCnt > evenCnt) { // 만약 홀수가 더 큰 경우는 그냥 확정
				result = oddCnt * 2 - 1;
			}else { // 짝수가 더 큰 경우면 짝수 1일을 홀수 2일로 옮겨주면서 최소 날짜를 탐색
				
				// 먼저 차이를 계산해서 2개가 비슷해 지는 정도로 이동
				int diff = evenCnt - oddCnt;
				int stand = diff / 3;
				evenCnt -= stand;
				oddCnt += stand * 2;
				
				boolean flag = true;
				while(flag) { //홀수가 짝수보다 커 지는 경우가 생길 때 까지 검사
					if(evenCnt >= oddCnt) {
						result = Math.min(result, evenCnt * 2);
					}else {
						result = Math.min(result, oddCnt * 2 - 1);
						flag = false;
					}
					evenCnt--;
					oddCnt += 2;
				}
				
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
