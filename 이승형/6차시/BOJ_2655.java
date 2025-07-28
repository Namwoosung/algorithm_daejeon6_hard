import java.io.*;
import java.util.*;

public class BOJ_2655 {

	static int N;
	static int [][] map;
	static boolean [][] visited;
	static int [] dx = new int[] {0,0,-1,1};
	static int [] dy = new int[] {-1,1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j]=line.charAt(j)-'0';
			}
		}
		
		int result = bfs(new Pair(0,0,0));
		System.out.println(result);
	}

	static int bfs(Pair start) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		visited[start.x][start.y] = true;
		pq.add(start);
		
		while(!pq.isEmpty()) {
			Pair now = pq.poll();
			if(now.x==N-1 && now.y==N-1) return now.value; 
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>= N || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				
				if(map[nx][ny]==1) pq.add(new Pair(nx,ny,now.value)); 
				
				if(map[nx][ny]==0) pq.add(new Pair(nx,ny,now.value+1));
			}
		}
		return -1;
	}
	
	
	
	
	static class Pair implements Comparable<Pair>{
		int x,y,value;
		public Pair(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
		
		@Override
		public int compareTo(Pair o) {
			return this.value - o.value;
		}
		
	}
}
