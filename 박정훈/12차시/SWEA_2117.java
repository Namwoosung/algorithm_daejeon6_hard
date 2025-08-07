import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_2117 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] city = new int[N][N];
			int maxHouse = 0;
			int total = 0;
			//입력
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; x++) {
					int n = Integer.parseInt(st.nextToken());
					city[y][x] = n;
					total++; //집 총 개수
				}
			}
			//얻을 수 있는 최대 수익
			int maxCost = total * M;
			
			//모든 구역을 시작점으로 탐색
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					int k = 1;
					int cost = (k * k) + ((k - 1) * (k - 1));
					while(maxCost - cost >= 0) {
						int house = bfs(city, x, y, k);
						if((house * M) - cost >= 0) { 
							maxHouse = Math.max(maxHouse, house);
						}
						k++;
						cost = (k * k) + ((k - 1) * (k - 1)); //비용 계산
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(maxHouse).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int bfs(int[][] city, int x, int y, int maxK) {
		Deque<int[]> deque = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		deque.offer(new int[] {x, y, 1}); // 2번 인덱스는 maxK까지만 감
		visited[y][x] = true;
		int house = 0;
		
		while(!deque.isEmpty()) {
			int[] info = deque.poll();
			int cx = info[0];
			int cy = info[1];
			int ck = info[2];
			// 집 개수 세기
			if(city[cy][cx] == 1) {
				house++;
			}
			//4방향
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				//범위 밖
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(!visited[ny][nx] && ck < maxK) {
					visited[ny][nx] = true;
					deque.offer(new int[] {nx, ny, ck + 1});
				}
			}
		}
		return house;
	}

}