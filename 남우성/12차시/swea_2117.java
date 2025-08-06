import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[][] board;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			int maxHome = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1) maxHome++;
				}
			}
			
			int maxIncome = maxHome * M;
			int result = 0;
			int size = 0;
			while(true) {
				size++;
				int nowCost = size * size + (size - 1) * (size - 1);
				if(nowCost > maxIncome) break; // 현재 비용이 최대 수익보다 커지면 종료
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						int nowHome = checkHome(i, j, size);
						if(nowHome * M >= nowCost) result = Math.max(result, nowHome); // 비용감당이 되는 경우만 result update
					}
				}
				
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static int checkHome(int x, int y, int size) {
		int stand = 0; // y좌표 설정을 위한 변수
		int cnt = 0;
		for(int i = x - size + 1; i <= x + size - 1; i++) {
			stand++;
			int dy = Math.abs(stand - size); //size -1 -> size -2 -> ... -> 0 -> ... -> size - 2 -> size - 1
			for(int j = y - size + 1 + dy; j <= y + size - 1 - dy; j++) {
				if(i >= 0 && i < N && j >= 0 && j < N) {
					if(board[i][j] == 1) cnt++;
				}
			}
		}
		return cnt;
	}
}
