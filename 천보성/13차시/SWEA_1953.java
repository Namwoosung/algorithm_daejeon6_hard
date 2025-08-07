import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {
	static class Point{
		int x;
		int y;
		int time;
		Point(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	static int N, M, L;
	static int[][] map;
	static int[][][] dxy = {
			{},
			{{1,0}, {0,1}, {-1,0}, {0,-1}},
			{{-1,0}, {1,0}},
			{{0,-1}, {0,1}},
			{{-1,0}, {0,1}},
			{{1,0}, {0,1}},
			{{1,0}, {0,-1}},
			{{-1,0}, {0,-1}}
	};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int testcase=1;testcase<=T;testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append('#').append(testcase).append(' ');
			sb.append(bfs(R, C));
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	public static int bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(r, c, 1));
		boolean[][] visited = new boolean[N][M];
		visited[r][c] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();

			if(now.time > L) {
				return cnt;
			}
			
			cnt++;			
			int type = map[now.x][now.y];
			
			for(int i=0;i<dxy[type].length;i++) {
				int nextX = now.x + dxy[type][i][0];
				int nextY = now.y + dxy[type][i][1];
				
				if(checkOutOfBound(nextX, nextY)||  map[nextX][nextY] == 0 || visited[nextX][nextY]) continue;
				if(!checkLink(dxy[type][i][0], dxy[type][i][1], map[nextX][nextY])) continue;
				
				visited[nextX][nextY] = true;
				queue.add(new Point(nextX, nextY, now.time + 1));
			}
		}
		
		return cnt;
	}
	
	public static boolean checkOutOfBound(int x, int y) {
		return x < 0 || y < 0 || x >= N || y >= M;
	}
	
	public static boolean checkLink(int dx, int dy , int type) {
		if( (dx == 1 && dy == 0) && (type == 3 || type == 5 || type == 6)) {
			return false;
		}
		else if((dx == 0 && dy == 1) && (type == 2 || type == 4 || type == 5)) {
			return false;
		}
		else if((dx == -1 && dy == 0) && (type == 3 || type == 4 || type == 7)) {
			return false;
		}
		else if((dx == 0 && dy == -1) && (type == 2 || type == 6 || type == 7)) {
			return false;
		}
		return true;
		/*
		 * dir 1 -> 3, 5, 6 x
		 * dir 2 -> 2, 4, 5 x
		 * dir 3 -> 3, 4, 7 x
		 * dir 4 -> 2, 6, 7 x
		 */
	}
	
}
