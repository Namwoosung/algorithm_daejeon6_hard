import java.io.*;
import java.util.*;

class SWEA_1249
{	
	static int N;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		int time;
		
		Point(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Point p) {
			return this.time - p.time;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j] = s.charAt(j) - '0';
					
				}
			}
			
			sb.append('#').append(test_case).append(' ');
			sb.append(findMinTime());
			sb.append('\n');
		}
		System.out.println(sb);

	}
	
	public static int findMinTime() {
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			
			if(now.x == N-1 && now.y == N-1) {
				return now.time;
			}
			
			for(int i=0;i<4;i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || visited[nextX][nextY]) continue;
				visited[nextX][nextY] = true;
				pq.add(new Point(nextX, nextY, now.time + map[nextX][nextY]));
				
			}
		}
		return -1;
	}
}