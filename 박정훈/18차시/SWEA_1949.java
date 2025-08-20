import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1949 {
	static int N, K;
	static int[][] table;
	static int maxLen;
	static int maxHeight;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static boolean[][] visited;
	
	static class maxHeightPos {
		int x, y;
		
		public maxHeightPos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder answer = new StringBuilder();
	    StringTokenizer st;
	    int T = Integer.parseInt(br.readLine());
	    
	    for(int tc = 1; tc <= T; tc++) {
	    	st = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(st.nextToken());
	    	K = Integer.parseInt(st.nextToken());
	    	table = new int[N][N];
	    	visited = new boolean[N][N];
	    	maxLen = 0;
	    	maxHeight = 0;
	    	List<maxHeightPos> list = new ArrayList<>();
	    	
	    	// 지도 입력
	    	for(int i = 0; i < N; i++) {
	    		st = new StringTokenizer(br.readLine());
	    		for(int j = 0; j < N; j++) {
	    			int height = Integer.parseInt(st.nextToken());
	    			table[i][j] = height;
	    			if(height > maxHeight) { // 더 높은 봉우리가 있다면 여태 저장한 좌표 초기화하고 새로 저장
	    				maxHeight = height;
	    				list = new ArrayList<>();
	    				list.add(new maxHeightPos(j, i));
	    			} else if(height == maxHeight) { // 최고 높이 봉우리들 좌표 저장
	    				list.add(new maxHeightPos(j, i));
	    			}
	    		}
	    	}
	    	
	    	// 높은 봉우리 위치에서 탐색
	    	for(int i = 0; i < list.size(); i++) {
	    		int x = list.get(i).x;
	    		int y = list.get(i).y;
	    		visited[y][x] = true;
	    		dfs(x, y, 1, false, table[y][x]);
	    		visited[y][x] = false;
	    	}
	    	
	    	answer.append("#").append(tc).append(" ").append(maxLen).append("\n");
	    }
	    
	    System.out.print(answer);
	}
	
	public static void dfs(int x, int y, int len, boolean isDig, int height) {
		maxLen = Math.max(maxLen, len); // 최대 길이 갱신
		if(isDig && table[y][x] == 1) return; // 높이 1에서 깎지 못 하면 이을 수 없음

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위 밖
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if(height > table[ny][nx]) { // 낮은 봉우리는 땅 안 파도 가능
				visited[ny][nx] = true;
				dfs(nx, ny, len + 1, isDig, table[ny][nx]);
				visited[ny][nx] = false;
			} else if(!isDig && !visited[ny][nx]) { // 같거나 높은 경우 땅 파서 가능한 지 검사
				if(height > table[ny][nx] - K) { // 땅 파서 높이가 낮아지면 가능
					visited[ny][nx] = true;
					dfs(nx, ny, len + 1, true, height - 1); // 현재 봉우리보다 1 낮게
					visited[ny][nx] = false;
				}
			}
		}
	}
}
