import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class SWEA_1953 {
	static int[][] direct = {{0, 0, 0, 0}, {-1, 1, 1, -1}, {-1, 0, 1, 0}, {0, 1, 0, -1},
			{-1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, -1}, {-1, 0, 0, -1}};
	static int[][] map;
	static boolean[][] visited;
	static int N, M, R, C, L;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			//맵 입력
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < M; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = bfs(C, R);
			sb.append("#").append(test_case).append(" ").append(count).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static int bfs(int x, int y) {
		Deque<int[]> deque = new ArrayDeque<>();
		visited[y][x] = true;
		deque.offer(new int[] {x, y, 1});
		int count = 1;
		
		while(!deque.isEmpty()) {
			int[] info = deque.poll();
			int cx = info[0];
			int cy = info[1];
			int time = info[2];
			
			for(int i = 0; i < 4; i++) {
				int nx = cx;
				int ny = cy;
				if(i % 2 == 0) { //상하 이동
					if(direct[map[cy][cx]][i] == 0) continue;
					ny += direct[map[cy][cx]][i];
				} else { //좌우 이동
					if(direct[map[cy][cx]][i] == 0) continue;
					nx += direct[map[cy][cx]][i];
				}
				
				//범위 밖이거나 터널이 없음
				if(nx < 0 || nx >= M || ny < 0 || ny >= N || map[ny][nx] == 0) continue;
				//터널이 연결되어 있지 않음
				if(direct[map[cy][cx]][i] + direct[map[ny][nx]][(i + 2) % 4] != 0) continue;
				
				//방문하지 않은 곳이고 이동시간이 충분하다면 삽입
				if(!visited[ny][nx] && time + 1 <= L) {
					visited[ny][nx] = true;
					deque.offer(new int[] {nx, ny, time + 1});
					count++;
				}
			}
		}
		
		return count;
	}
}