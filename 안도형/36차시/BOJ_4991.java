package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_4991 {
	public static int dustCount, maxTotal, bfsCount, W, H;
	public static int[] dr = {1, -1, 0, 0};
	public static int[] dc = {0, 0, 1, -1};
	
	public static int[][] visited;
	public static char[][] map;
	public static int[][] dust = new int[11][2];
	public static int[][] dustDp;
	
	public static boolean isNotFound = false;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        visited = new int[20][20];
        map = new char[20][20];
        
        while(true) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        
	        W = Integer.parseInt(st.nextToken());
	        H = Integer.parseInt(st.nextToken());
	        if(W == 0 && H == 0) break;
	        
	        dustCount = 0; 
	        maxTotal = Integer.MAX_VALUE;

	        for(int i = 0; i < H; i++) {
	        	String s = br.readLine();
	        	for(int j = 0; j < W; j++) {
	        		map[i][j] = s.charAt(j);
	        		
	        		if(map[i][j] == 'o') {
	        			dust[0][0] = i;
	        			dust[0][1] = j;
	        		}else if(map[i][j] == '*') {
	        			dustCount++;
	        			dust[dustCount][0] = i;
	        			dust[dustCount][1] = j;
	        		}
	        	}
	        }
	        
	        dustDp = new int[dustCount + 1][dustCount + 1];
	        boolean reachable = false;
	        for(int i = 0; i <= dustCount; i++) {
	        	if(reachable) break;
	        	bfs(dust[i][0], dust[i][1], i);
	        	
	        	for(int j = 1; j <= dustCount; j++) {
	        		if(dustDp[0][j] == 0) {
	        			reachable = true;
	        			break;
	        		}
	        	}
	        }
	        
	        if (reachable) {
                System.out.println(-1);
                continue;
            }
	        
	        dfs(0, 0, 0, new boolean[dustCount + 1]);
	        System.out.println(maxTotal);
        }
	}
	
	
	static void dfs(int count, int total, int dustIdx, boolean[] visited) {
		if(maxTotal < total) return;
		if(count == dustCount) {
			maxTotal = Math.min(maxTotal, total);
			return;
		}
		
		for(int i = 1; i <= dustCount; i++) {
			if(!visited[i]) {
				visited[i] = true;	
				dfs(count+1, total + dustDp[dustIdx][i], i, visited);
				visited[i] = false;
			}
		}
	}
	
	static void bfs(int row, int col, int startIdx) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {row, col, 0});
		visited[row][col] = bfsCount;
		bfsCount++;
		
		while(!dq.isEmpty()) {
			int[] curr = dq.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				
				if(nr < 0 || nr >= H || nc < 0 || nc >= W || bfsCount == visited[nr][nc] || map[nr][nc] == 'x') continue;
				visited[nr][nc] = bfsCount;
				
				if(map[nr][nc] == '*') {
					for(int j = 1; j <= dustCount; j++) {
						if(dust[j][0] == nr && dust[j][1] == nc) {
							dustDp[startIdx][j] = curr[2] + 1;
							dustDp[j][startIdx] = curr[2] + 1;
							break;
						}
					}
				}

				dq.add(new int[] {nr, nc, curr[2] + 1});
			}
		}
	}
	
	static void printDust() {
		for(int i = 0; i <= dustCount; i++) {
			System.out.print(dustDp[0][i] + " ");
		}
		System.out.println();
	}
}
