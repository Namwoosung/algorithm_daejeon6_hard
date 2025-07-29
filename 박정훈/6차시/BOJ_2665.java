import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][] visited;
	static int[][] maze;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		maze = new int[n][n];
		visited = new boolean[n][n];
		
		//입력
		for(int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder(br.readLine());
			for(int j = 0; j < n; j++) {
				maze[i][j] = Integer.parseInt(String.valueOf(sb.charAt(j)));
			}
		}
		
		//탐색
		visited[0][0] = true;
		int answer = bfs(0, 0, n);
		
		System.out.print(answer);
	}
	
	public static int bfs(int x, int y, int n) {
		//가장 적게 부순 경로부터 탐색
		PriorityQueue<int[]> pque = new PriorityQueue<>((a,b) -> a[2] - b[2]);
		pque.offer(new int[] {x, y, 0});
		
		while(!pque.isEmpty()) {
			int[] info = pque.poll();
			int cx = info[0];
			int cy = info[1];
			int cb = info[2];
			//도착
			if(cx == n - 1 && cy == n - 1) return cb;
			
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				//범위 밖이거나 방문한 곳
				if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[ny][nx]) continue;
				visited[ny][nx] = true;
				// 흰 방
				if(maze[ny][nx] == 1) {
					pque.offer(new int[] {nx, ny, cb});
				} else { //검은 방
					pque.offer(new int[] {nx, ny, cb + 1});
				}
			}
		}
		
		return 0;
	}
}