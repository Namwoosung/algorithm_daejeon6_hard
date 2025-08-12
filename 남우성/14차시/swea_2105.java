import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	// 순서대로 좌하, 우하, 우상 방향 저장
	static int[] dx = {1, 1, -1};
	static int[] dy = {-1, 1, 1};
	
	static int result;
	static int[][] board;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = Integer.parseInt(br.readLine());
			
			board = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			result = -1;
			
			for(int i =0 ; i< N; i++) {
				for(int j = 0; j < N; j++) {
					// i,j는 마름모의 위 곡짓점을 의미
					checkLeftDot(i, j);
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	// 왼쪽 위 대각선을 설정
	static void checkLeftDot(int i, int j) {
		Set<Integer> desert = new HashSet<>();
		desert.add(board[i][j]);
		
		// 마름모의 왼쪽 꼭짓점을 의미
		int ex = i + dx[0];
		int ey = j + dy[0];
		while(ex >= 0 && ex < N && ey >= 0 && ey <N) { //왼쪽 꼭짓점이 범위 안에 있어야 함
			if(desert.contains(board[ex][ey])) return; // 동일한 디저트가 있으면 바로 종료
			desert.add(board[ex][ey]);
			checkAllDot(i, j, ex, ey, desert);
			// 꼭짓점을 다음 위치로 이동
			ex += dx[0];
			ey += dy[0];
		}
		
	}
	
	//왼쪽 아래 대각선, 오른족 위 대각선을 설정
	static void checkAllDot(int sx, int sy, int ex, int ey, Set<Integer> desert) {
		//Set 복사
		Set<Integer> nowDesert = new HashSet<>();
		nowDesert.addAll(desert);
		
		int nx1 = sx; int ny1 = sy;
		int nx2 = ex; int ny2 = ey;
		nx1 += dx[1]; ny1 += dy[1]; nx2 += dx[1]; ny2 += dy[1]; // 각가 두 점을 다음 위치로 이동(우하 방향으로 이동)
		while((nx1 >= 0 && nx1 < N && ny1 >= 0 && ny1 <N) && (nx2 >= 0 && nx2 < N && ny2 >= 0 && ny2 <N)) { // 모든 점이 범위 안에 존재
			// 디저트가 존재하면 종료, 아니면 추가
			if(nowDesert.contains(board[nx1][ny1])) return;
			nowDesert.add(board[nx1][ny1]);
			if(nowDesert.contains(board[nx2][ny2])) return;
			nowDesert.add(board[nx2][ny2]);
			
			checkBoard(nx1, ny1, nx2, ny2, nowDesert);
			
			// 다음 점으로 이동
			nx1 += dx[1]; ny1 += dy[1]; nx2 += dx[1]; ny2 += dy[1];
		}
	}
	
	// 마지막으로 오른쪽 아래 대각선을 확인
	static void checkBoard(int nx1, int ny1, int nx2, int ny2, Set<Integer> desert) {
		// Set 복사
		Set<Integer> nowDesert = new HashSet<>();
		nowDesert.addAll(desert);
		
		int nx = nx2 + dx[2];
		int ny = ny2 + dy[2];
		
		while(!(nx == nx1 && ny == ny1)) {
			if(nowDesert.contains(board[nx][ny])) return;
			nowDesert.add(board[nx][ny]);
			nx += dx[2];
			ny += dy[2];
		}
		
		result = Math.max(result, nowDesert.size());
	}
}
