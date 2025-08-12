import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_2105 {
	
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {-1,1,1,-1};
	static int N;
	static int[][] map;
	static boolean[] visited;
	static boolean[] dir;
	static int startX;
	static int startY;
	static int max;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1;testcase<=T;testcase++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			dir = new boolean[4];
			visited = new boolean[101];
			max = -1;
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<N-2;i++) {
				for(int j=0;j<N-1;j++) {
					startX = i;
					startY = j;
					dfs(i, j, 0, 1, 0);

				}
			}
			
			sb.append('#').append(testcase).append(' ');
			sb.append(max);
			sb.append('\n');
			
		}
		System.out.println(sb);
	}
	
	public static void dfs(int x, int y, int type,int depth, int cnt) {
		if(x == startX && y == startY && depth > 4) {
			max = Math.max(max, cnt);
			return;
			
		}
		for(int i=0;i<4;i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(checkOutOfBound(nextX, nextY)) continue;
			if(visited[map[nextX][nextY]] || dir[i]) continue;
			if(i == (type+2) % 4) continue;
			
			visited[map[nextX][nextY]] = true;
			
			if(type != i) {
				dir[type] = true;
				dfs(nextX, nextY, i, depth + 1, cnt + 1);
				dir[type] = false;
			}
			else {
				dfs(nextX, nextY, i, depth + 1, cnt + 1);
			}
			
			visited[map[nextX][nextY]] = false;

		}
	}
	
	public static boolean checkOutOfBound(int x, int y) {
		return x < 0 || y < 0 || x >= N || y >= N;
	}
}
