import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {0,1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	static List<Pos> cctvs;
	static int result = Integer.MAX_VALUE;
	static int n;
	static int m;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
		int[][] board = new int[n][m];
		cctvs = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] != 0 && board[i][j] != 6) cctvs.add(new Pos(i,j));
			}
		}
		
		dfs(board, 0);
		
		System.out.print(result);
	}
	
	static void dfs(int[][] board, int index) {
		if(index == cctvs.size()) { // 모든 cctv의 감시를 반영했다면
			int cnt = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(board[i][j] == 0) cnt++;
				}
			}
			result = Math.min(result, cnt);
			return;
		}
		

		
		Pos cctv = cctvs.get(index);
		// 각 case별로 board 반영 후 dfs 호출
		if(board[cctv.x][cctv.y] == 1) {
			for(int dir = 0; dir < 4; dir++) {
				//board 복사
				int[][] now = new int[n][m]; 
				for(int i = 0; i < n; i++) {
					now[i] = Arrays.copyOf(board[i], m);
				}
				
				int nx = cctv.x + dx[dir];
				int ny = cctv.y + dy[dir];
				
				while(nx >= 0 && nx < n && ny >= 0 && ny < m && now[nx][ny] != 6) {
					if(now[nx][ny] == 0) now[nx][ny] = 7;
					nx += dx[dir];
					ny += dy[dir];
				}
				dfs(now, index + 1);
			}
		} else if(board[cctv.x][cctv.y] == 2) {
			for(int dir = 0; dir < 2; dir++) {
				int[][] now = new int[n][m]; 
				for(int i = 0; i < n; i++) {
					now[i] = Arrays.copyOf(board[i], m);
				}
				
				int nx = cctv.x + dx[dir];
				int ny = cctv.y + dy[dir];
				
				while(nx >= 0 && nx < n && ny >= 0 && ny < m && now[nx][ny] != 6) {
					if(now[nx][ny] == 0) now[nx][ny] = 7;
					nx += dx[dir];
					ny += dy[dir];
				}
				
				nx = cctv.x + dx[dir + 2];
				ny = cctv.y + dy[dir + 2];
				
				while(nx >= 0 && nx < n && ny >= 0 && ny < m && now[nx][ny] != 6) {
					if(now[nx][ny] == 0) now[nx][ny] = 7;
					nx += dx[dir + 2];
					ny += dy[dir + 2];
				}
				dfs(now, index + 1);
			}
			
		} else if (board[cctv.x][cctv.y] == 3) {
			
			for(int dir = 0; dir < 4; dir++) {
				int[][] now = new int[n][m]; 
				for(int i = 0; i < n; i++) {
					now[i] = Arrays.copyOf(board[i], m);
				}
				
				int nx = cctv.x + dx[dir];
				int ny = cctv.y + dy[dir];
				
				while(nx >= 0 && nx < n && ny >= 0 && ny < m && now[nx][ny] != 6) {
					if(now[nx][ny] == 0) now[nx][ny] = 7;
					nx += dx[dir];
					ny += dy[dir];
				}
				
				int nowDir = (dir + 1) % 4;
				nx = cctv.x + dx[nowDir];
				ny = cctv.y + dy[nowDir];
				
				while(nx >= 0 && nx < n && ny >= 0 && ny < m && now[nx][ny] != 6) {
					if(now[nx][ny] == 0) now[nx][ny] = 7;
					nx += dx[nowDir];
					ny += dy[nowDir];
				}
				dfs(now, index + 1);
			}

			
		} else if(board[cctv.x][cctv.y] == 4) {
			for(int dir = 0; dir < 4; dir++) {
				//board 복사
				int[][] now = new int[n][m]; 
				for(int i = 0; i < n; i++) {
					now[i] = Arrays.copyOf(board[i], m);
				}
				
				for(int temp = 0; temp < 3; temp++) {
					int nowDir = (dir + temp) % 4;
					int nx = cctv.x + dx[nowDir];
					int ny = cctv.y + dy[nowDir];
					
					while(nx >= 0 && nx < n && ny >= 0 && ny < m && now[nx][ny] != 6) {
						if(now[nx][ny] == 0) now[nx][ny] = 7;
						nx += dx[nowDir];
						ny += dy[nowDir];
					}
				}

				dfs(now, index + 1);
			}
			
		}else if(board[cctv.x][cctv.y] == 5) {
			//board 복사
			int[][] now = new int[n][m]; 
			for(int i = 0; i < n; i++) {
				now[i] = Arrays.copyOf(board[i], m);
			}
			for(int dir = 0; dir < 4; dir++) {
				int nx = cctv.x + dx[dir];
				int ny = cctv.y + dy[dir];
				
				while(nx >= 0 && nx < n && ny >= 0 && ny < m && now[nx][ny] != 6) {
					if(now[nx][ny] == 0) now[nx][ny] = 7;
					nx += dx[dir];
					ny += dy[dir];
				}
			}
			dfs(now, index + 1);
		}
	}
}
