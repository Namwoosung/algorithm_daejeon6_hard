import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_4991 {
	static class Pos {
		int r, c, w = 0;
		
		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		Pos(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
	
	static char[][] table;
	static int[][] weight;
	static int w, h;
	static Pos robot;
	static List<Pos> dirtySpots;
	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };
	static int min;
	static boolean flag;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;     
        
        while(true) {
        	st = new StringTokenizer(br.readLine());
        	w = Integer.parseInt(st.nextToken());
        	h = Integer.parseInt(st.nextToken());
        	if(w == 0 && h == 0) break;
        	min = Integer.MAX_VALUE;
        	flag = true;
        	
        	dirtySpots = new ArrayList<>();
        	table = new char[h][w];
        	for(int r = 0; r < h; r++) {
        		String row = br.readLine();
        		for(int c = 0; c < w; c++) {
        			char col = row.charAt(c);
        			table[r][c] = col;
        			
        			if(col == 'o') { // 로봇 청소기
        				robot = new Pos(r, c);
        			} else if(col == '*') { // 더러운 곳
        				dirtySpots.add(new Pos(r ,c));
        			}
        		}
        	}
        	
        	// r -> c로 가는데 드는 최소 비용
        	int size = dirtySpots.size() + 1;
        	weight = new int[size][size];
        	// 0은 로봇 청소기, 1~size는 더러운 곳
        	for(int start = 0; start < size; start++) {
        		int[] w = bfs(start, size);
        		weight[start] = w;
        	}
        	
        	if(flag) { // 청소 가능 여부
        	// 최소 경로 찾기
	        	boolean[] visited = new boolean[size];
	        	for(int i = 1; i < size;i++) {
	        		int sum = weight[0][i]; // i번 구역으로 가는 가중치
	        		visited[i] = true;
	        		permute(0, i, visited, sum, 1);
	        		visited[i] = false;
	        	}
        	} else {
        		min = -1;
        	}
        	answer.append(min).append("\n");
        }
        System.out.println(answer);
    }

	private static void permute(int cur, int next, boolean[] visited, int sum, int depth) {
		if(depth == visited.length - 1) { // 모든 구역 청소 완료
			min = Math.min(min, sum);
			return;
		}
		
		for(int i = 1; i < visited.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				permute(next, i, visited, sum + weight[next][i], depth + 1);
				visited[i] = false;
			}
		}
	}

	public static int[] bfs(int start, int size) {
		ArrayDeque<Pos> que = new ArrayDeque<>();
		
		// 시작 위치 설정
		int r, c;
		if(start == 0) { // 로봇 청소기
			r = robot.r;
			c = robot.c;
		} else { // 더러운 곳
			r = dirtySpots.get(start - 1).r;
			c = dirtySpots.get(start - 1).c;
		}
		
		que.offer(new Pos(r, c));
		int[] result = new int[size];
		int[][] value = new int[h][w];
		for(int i = 0; i < h; i++) {
			Arrays.fill(value[i], Integer.MAX_VALUE);
		}
		
		value[r][c] = 0; // 시작 위치
		
		while(!que.isEmpty()) {
			Pos pos = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = pos.r + dr[i];
				int nc = pos.c + dc[i];
				
				// 범위 밖
				if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
				// 못 지나감
				if(table[nr][nc] == 'x') continue;
				
				if(value[nr][nc] > pos.w + 1) {
					value[nr][nc] = pos.w + 1;
					que.offer(new Pos(nr, nc, pos.w + 1));
				}
			}
		}
		
		// 결과 대입
		for(int i = 0; i < size; i++) {
			if(i == 0) {
				result[i] = value[robot.r][robot.c];
			} else {
				result[i] = value[dirtySpots.get(i - 1).r][dirtySpots.get(i - 1).c];
			}
			if(result[i] == Integer.MAX_VALUE) flag = false; 
		}
		return result;
	}
}