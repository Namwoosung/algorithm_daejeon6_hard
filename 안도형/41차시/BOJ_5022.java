import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[][] line;
	public static int[][][] map;
	public static boolean[][] mainVisited;
	
	public static int[] dx = {1, -1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken()) + 1;
        
        map = new int[N][M][2];
        mainVisited= new boolean[N][M];
        
        line = new int[4][2];
        for(int i = 0; i < 4; i++) {
        	st = new StringTokenizer(br.readLine());
        	line[i][0] = Integer.parseInt(st.nextToken());
        	line[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int aCount = bfs(0, 2);
        if(aCount != Integer.MIN_VALUE) {
        	aCount += bfs(2, 0); 
        }else {
        	System.out.println("IMPOSSIBLE");
        	return;
        }
        
        for(int i = 0; i < N; i++) 
        	Arrays.fill(mainVisited[i], false);
        
        int bCount = bfs(2, 0);
        if(bCount != Integer.MIN_VALUE) {
        	bCount += bfs(0, 2); 
        }else {
        	System.out.println("IMPOSSIBLE");
        	return;
        }
        
        System.out.println(aCount > 0 ? 
        		(bCount > 0 ? Math.min(aCount, bCount) : aCount) : 
        	(bCount > 0 ? bCount : "IMPOSSIBLE"));
	}
	
	
	public static int bfs(int startIdx, int blockIdx) {
		boolean[][] visited = new boolean[N][M];
		visited[line[startIdx][0]][line[startIdx][1]] = true;
		visited[line[blockIdx][0]][line[blockIdx][1]] = true;
		visited[line[blockIdx + 1][0]][line[blockIdx + 1][1]] = true;
		
		map[line[startIdx][0]][line[startIdx][1]][0] = -1;
		map[line[startIdx][0]][line[startIdx][1]][1] = -1;
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {line[startIdx][0], line[startIdx][1], 0});

		while(!dq.isEmpty()) {
			int[] pos = dq.poll();
			
			if(pos[0] == line[startIdx +  1][0] && pos[1] == line[startIdx +  1][1]) {
				int count = 0;
				int[] prevPos = {pos[0], pos[1]};
				
				
				while(map[prevPos[0]][prevPos[1]][0] != -1) {
					mainVisited[prevPos[0]][prevPos[1]] = true;
					int currX = prevPos[0];
					int currY = prevPos[1];
					prevPos[0] = map[currX][currY][0];
					prevPos[1] = map[currX][currY][1];
					count++;
				}
				mainVisited[prevPos[0]][prevPos[1]] = true;
				return count;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = pos[0] + dx[i];
				int ny = pos[1] + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || mainVisited[nx][ny]) continue;
				visited[nx][ny] = true;
				map[nx][ny][0] = pos[0];
				map[nx][ny][1] = pos[1];
				
				dq.add(new int[] {nx, ny, pos[2] + 1});
			}
		}
        	
		return Integer.MIN_VALUE;
	}
}
