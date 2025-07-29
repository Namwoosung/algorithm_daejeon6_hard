import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

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
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		int[][] weights = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] line = br.readLine().split("");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.valueOf(line[j]);
				weights[i][j] = Integer.MAX_VALUE;
			}
		}
		
		Deque<Pos> deque = new ArrayDeque<>();
		deque.add(new Pos(0, 0));
		weights[0][0] = 0;
		while(!deque.isEmpty()) {
			Pos now = deque.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					
					if(board[nx][ny] == 1) { //흰 방
						if(weights[nx][ny] > weights[now.x][now.y]) { // 더 짧은 경로 존재
							weights[nx][ny] = weights[now.x][now.y];
							deque.add(new Pos(nx, ny));
						}
					} else { //검은 방
						if(weights[nx][ny] > weights[now.x][now.y] + 1) { // 더 짧은 경로 존재
							weights[nx][ny] = weights[now.x][now.y] + 1;
							deque.add(new Pos(nx, ny));
						}
					}
					
				}
			}
			
		}
		
		System.out.print(weights[N-1][N-1]);
	}
}
