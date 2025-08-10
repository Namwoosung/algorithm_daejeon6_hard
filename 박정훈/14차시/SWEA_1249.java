import java.util.*;
import java.io.*;

class SWEA_1249
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			/////////////////////////////////////////////////////////////////////////////////////////////
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int[][] minValue = new int[N][N]; // 각 칸에 도착하는 최소 시간
            int[] dx = new int[] { 1, -1, 0, 0 }; // 이동 방향
            int[] dy = new int[] { 0, 0, 1, -1 }; // 이동 방향
            Queue<int[]> que = new LinkedList<>();
            que.add(new int[] {0, 0, 0}); // 시작점
            
            for(int i = 0; i < N; i++) {
                sb = new StringBuilder(br.readLine());
            	for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(String.valueOf(sb.charAt(j))); 
                    minValue[i][j] = Integer.MAX_VALUE;
                }
            }
            
            while(!que.isEmpty()) {
            	int[] info = new int[3];
            	info = que.poll();
            	
            	// 4방향 이동
            	for(int i = 0; i < 4; i++) {
            		int x = info[0];
            		int y = info[1];
            		int time = info[2];
            		
            		x += dx[i];
            		y += dy[i];
            		if(x < 0 || x >= N || y < 0 || y >= N) { // 범위를 벗어남
            			continue;
            		}
            		
            		if(x == N-1 && y == N-1) { // 목적지 도착
            			minValue[y][x] = Math.min(minValue[y][x], time);
            		} else {
	            		time += map[y][x]; // 이동한 칸의 복구 시간 더하기
	            		if(time < minValue[y][x]) { // 현재 경로가 다른 경로보다 적은 시간이라면
	            			minValue[y][x]= time; // 최소 시간 갱신
	            			que.add(new int[] {x, y, time}); // 큐에 추가
	            		}
            		}
            	}
            }
            
            // 최소 시간 출력
            System.out.println("#" + test_case + " " + minValue[N-1][N-1]);  
			/////////////////////////////////////////////////////////////////////////////////////////////
		}
	}
}