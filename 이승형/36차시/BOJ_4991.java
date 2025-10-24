package com.ssafy.d1023;

import java.io.*;
import java.util.*;

/* 09:50
 * 백트래킹 + bfs
 */
public class BOJ_4991 {

	static int N,M;
	static String [][] room;
	static ArrayList<Point> target;
	static int [] order;
	static int [][] memo;
	static int minCount;
	static boolean [] visited;
	
	static int [] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());
		
		while(N!=0 && M!=0) {
			room = new String[M][N];
			target = new ArrayList<>();
			minCount = Integer.MAX_VALUE;
			Point robot = null;
			
			for(int i=0;i<M;i++) {
				room[i]=br.readLine().split("");
				for(int j=0;j<N;j++) {
					if(room[i][j].equals("o")) {
						robot = new Point(i,j);
						target.add(0, robot);
					}else if(room[i][j].equals("*")) {
						target.add(new Point(i,j));
					}
				}
			}
			visited = new boolean[target.size()];
			memo = new int[target.size()][target.size()];
			for(int i=0;i<target.size();i++) {
				Arrays.fill(memo[i], -1);
			}
			backTracking(0, target.size(), 0, robot, 0);
			
			sb.append(minCount).append("\n");
			
			str = new StringTokenizer(br.readLine());
			N = Integer.parseInt(str.nextToken());
			M = Integer.parseInt(str.nextToken());
		}
		
		System.out.println(sb.toString());
	}

	static void backTracking(int depth, int size, int countSum, Point from, int fromIdx) {
		if(minCount == -1) return;
		
		if(countSum > minCount) return;
		
		if(depth == size-1) {
			minCount = Math.min(minCount, countSum);
			return;
		}
		
		for(int i=1;i<size;i++) {
			if(visited[i]) continue;
			
			Point to = target.get(i);
			int nowCount = memo[fromIdx][i];
			
			if(nowCount == -1) {
				nowCount = bfs(from, to);
				memo[fromIdx][i] = nowCount;
				memo[i][fromIdx] = nowCount;
			}
			
			if(nowCount == -1) minCount = -1;
			visited[i]=true;
			backTracking(depth+1, size, countSum + nowCount, to, i);
			visited[i]=false;
		}	
	}
	
	static int bfs(Point from, Point to) {
		Queue<Point> que = new ArrayDeque<>();
		int [][] visited = new int[M][N];
		
		for(int i=0;i<M;i++) {
			Arrays.fill(visited[i],-1);
		}
		
		que.offer(from);
		visited[from.x][from.y] = 0;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			if(now.x==to.x && now.y==to.y) {
				return visited[now.x][now.y];
			}
			
			for(int i=0;i<4;i++) {
				int nx = now.x+dx[i];
				int ny = now.y+dy[i];
				
				if(nx<0 || ny<0 || nx>=M || ny>=N || visited[nx][ny]!=-1 || room[nx][ny].equals("x")) continue;
				
				que.offer(new Point(nx,ny));
				visited[nx][ny] = visited[now.x][now.y]+1;
			}
		}
		return -1;
	}
	
	static class Point{
		int x,y;
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
}
