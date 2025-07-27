import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[R][C];
		int[] cleaner = new int[2];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == -1) {
					cleaner[0] = i;
					cleaner[1] = j;
				}
			}
		}
		cleaner[0] -= 1;

		int[][] storeCal; 
		for(int e = 0; e < T; e++) {
			
			storeCal = new int[R][C]; // 각 시행마다 수행해야할 연산을 저장해 놓을 배열
			
			// 먼지 확산
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					
					if(board[i][j] > 0) {
						for(int k = 0; k < 4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							
							if(nx >= 0 && nx < R && ny >= 0 && ny < C && board[nx][ny] != -1) { //전파 가능한 경우
								storeCal[nx][ny] += (board[i][j] / 5);
								storeCal[i][j] -= (board[i][j] / 5);
							}
						}
					}
				}
			}
			
			// 반영
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					board[i][j] += storeCal[i][j];
				}
			}
			
			// 공기 청정기 동작
			int rx = - 1;
			int ry = 0;
			int x = cleaner[0] + rx;
			int y = cleaner[1] + ry;
			
			while(!(x == cleaner[0] && y == cleaner[1])) { // 위 방향
				
				// 다음 좌표
				int nx = x + rx;
				int ny = y + ry;
				
				if(nx < 0) { // 방향 변경
					rx = 0;
					ry = 1;
				} else if(ny >= C) {
					rx = 1;
					ry = 0;
				} else if(nx > cleaner[0]) {
					rx = 0;
					ry = -1;
				}
				nx = x + rx;
				ny = y + ry;
				
				if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
					board[x][y] = board[nx][ny];
				}
				x = nx; y = ny;
			}
			board[cleaner[0]][cleaner[1]+1] = 0;
			
			rx = 1;
			ry = 0;
			x = cleaner[0]+1 + rx;
			y = cleaner[1] + ry;
			while(!(x == cleaner[0]+1 && y == cleaner[1])) { // 아래 방향
				int nx = x + rx;
				int ny = y + ry;
				
				if(nx >= R) { // 방향 변경
					rx = 0;
					ry = 1;
				} else if(ny >= C) {
					rx = -1;
					ry = 0;
				} else if(nx < cleaner[0] + 1) {
					rx = 0;
					ry = -1;
				}
				nx = x + rx;
				ny = y + ry;
				
				if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
					board[x][y] = board[nx][ny];
				}
				x = nx; y = ny;
			}
			board[cleaner[0]+1][cleaner[1]+1] = 0;
		}
		
		int result = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] > 0) result += board[i][j];
			}
		}
		
		System.out.print(result);
	}
}


/*
// 풀이는 같은데, 가독성이 좋은 코드(변수명, 배열 관리, 반복문 처리, 메서드 분리 등)
 * import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	static int R, C, T;
	static int[][] board;
	static int airCleanerTop, airCleanerBottom;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		board = new int[R][C];

		boolean firstCleanerFound = false;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) {
					if (!firstCleanerFound) {
						airCleanerTop = i;
						firstCleanerFound = true;
					} else {
						airCleanerBottom = i;
					}
				}
			}
		}

		for (int t = 0; t < T; t++) {
			diffuseDust();
			cleanUpper();
			cleanLower();
		}

		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] > 0) result += board[i][j];
			}
		}
		System.out.println(result);
	}

	static void diffuseDust() {
		int[][] diffusion = new int[R][C];

		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				if (board[x][y] > 0) {
					int spread = board[x][y] / 5;
					int spreadCount = 0;

					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];

						if (nx >= 0 && nx < R && ny >= 0 && ny < C && board[nx][ny] != -1) {
							diffusion[nx][ny] += spread;
							spreadCount++;
						}
					}
					diffusion[x][y] -= spread * spreadCount;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				board[i][j] += diffusion[i][j];
			}
		}
	}

	static void cleanUpper() {
		// 위쪽 반시계 방향 순환
		for (int i = airCleanerTop - 1; i > 0; i--)
			board[i][0] = board[i - 1][0];
		for (int i = 0; i < C - 1; i++)
			board[0][i] = board[0][i + 1];
		for (int i = 0; i < airCleanerTop; i++)
			board[i][C - 1] = board[i + 1][C - 1];
		for (int i = C - 1; i > 1; i--)
			board[airCleanerTop][i] = board[airCleanerTop][i - 1];
		board[airCleanerTop][1] = 0;
	}

	static void cleanLower() {
		// 아래쪽 시계 방향 순환
		for (int i = airCleanerBottom + 1; i < R - 1; i++)
			board[i][0] = board[i + 1][0];
		for (int i = 0; i < C - 1; i++)
			board[R - 1][i] = board[R - 1][i + 1];
		for (int i = R - 1; i > airCleanerBottom; i--)
			board[i][C - 1] = board[i - 1][C - 1];
		for (int i = C - 1; i > 1; i--)
			board[airCleanerBottom][i] = board[airCleanerBottom][i - 1];
		board[airCleanerBottom][1] = 0;
	}
}

 */

*/