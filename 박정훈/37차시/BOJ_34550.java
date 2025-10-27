import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_34550 {
	static int[] count, pos;
	static int[][] sparseTable;
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 가수의 위치
		pos = new int[N + 1];
		// i번 째 도시에서 공연하는 가수의 수 
		count = new int[N + 1];
		// 희소 배열 -> j번 째에서 2^i 번 이동하면 도착하는 지점 계산
		int logN = (int) (Math.log(N) / Math.log(2));
		sparseTable = new int[logN + 1][N + 1];
		
		// 도로 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int city = Integer.parseInt(st.nextToken());
			sparseTable[0][i] = city;
		}
		
		// 희소 배열 구하기
		for(int i = 1; i <= logN; i++) {
			for(int j = 1; j <= N; j++) {
				int prev = sparseTable[i - 1][j];
				sparseTable[i][j] = sparseTable[i - 1][prev];
			}
		}
		
		// 첫 콘서트 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int city = Integer.parseInt(st.nextToken());
			pos[i] = city;
			count[city]++;
			// 밤샘 공연 열림
			if(count[city] >= K) {
				System.out.print(1);
				return;
			}
		}

		// 이분 탐색 -> 한 번 모인 가수들은 같은 도시로 이동하기 때문
		int left = 2;
		int right = N;
		int day = -1;
		while(left <= right) {
			int max = 0;
			int mid = (left + right) / 2;
			count = new int[N + 1];
			// mid일차는 mid - 1번 움직임
			int move = mid - 1;
			
			// 각 가수가 mid일차에 어느 도시에 있는 지
			for(int i = 1; i <= N; i++) {
				int city = pos[i]; // 가수의 시작 위치
				
				// move번 이동 후의 위치
				for(int k = 0; k <= logN; k++) {
					if((move & (1 << k)) != 0) {
						city = sparseTable[k][city];
					}
				}
				
				count[city]++;
				max = Math.max(max, count[city]);
			}
			
			if(max >= K) { // 밤샘 열림
				day = mid;
				right = mid - 1; // 더 빠른 날짜로 찾기
			} else {
				left = mid + 1; // 더 늦은 날짜로 찾기
			}
		}
		
		System.out.print(day);
	}
}
