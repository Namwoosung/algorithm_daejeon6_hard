import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class SWEA_2105 {
	static int[] dx = {1, -1, -1, 1};
	static int[] dy = {1, 1, -1, -1};
	static int max, N;
	static int[][] table;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			max = -1;
			table = new int[N][N];
			
			//입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			List<Integer> list = new ArrayList<>();
			for(int y = 0; y < N - 2; y++) {
				for(int x = 1; x < N - 1; x++) { // 0번 인덱스는 돌아오지 못 함 N-1번 인덱스는 출발 못 함 
					list.add(table[y][x]);
					dfs(x, y, x + dx[0], y + dy[0], 1, list, 0);
					list.remove(list.size() - 1);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void dfs(int startX, int startY, int x, int y, int count, List<Integer> list, int direct) {
		//시작점으로 돌아옴 (그대로 되돌아 오는 것 방지를 위해 4개이상 체크)
		if(x == startX && y == startY && count >= 4) {
			max = Math.max(max, count);
			return;
		}
		if(list.contains(table[y][x])) { //현재 디저트 먹음
			return;
		}
		
		//왼쪽 위로 올라가다가 시작지점과 같은 대각선이면 시작지점 방향으로 가야함
		if(direct == 2 && x + y == startX + startY) {
			list.add(table[y][x]);
			dfs(startX, startY, x + dx[3], y + dy[3], count + 1, list, 3);
			list.remove(list.size() - 1);
			return;
		}
		
		// 대각선으로 갈 수있는 만큼 가면서 사각형 그리는 방향으로
		for(int d = direct; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			//범위 체크
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			list.add(table[y][x]);
			dfs(startX, startY, nx, ny, count + 1, list, d);
			list.remove(list.size() - 1);
		}
	}
}