import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][][] ternal = { //7개의 터널을 저장, 각각 dx와 dy로 저장
			{},
			{
				{0, 0, 1, -1},
				{1, -1, 0, 0}
			},
			{
				{-1, 1},
				{0, 0}
			},
			{
				{0, 0},
				{1, -1}
			},
			{
				{-1, 0},
				{0, 1}
			},
			{
				{1, 0},
				{0, 1}
			},
			{
				{1, 0},
				{0, -1}
			},
			{
				{-1, 0},
				{0, -1}
			}
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken()); int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[][] board = new int[N][M];
			boolean[][] isVisited = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Deque<Pos> pos = new ArrayDeque<Pos>();
			pos.add(new Pos(R,C));
			isVisited[R][C] = true;
			int result = 0;
			for(int i =0; i < L; i++) {
				if(pos.isEmpty()) break; // 더 검사할 곳이 없으면 바로 종료
				
				int nowIter = pos.size();
				for(int temp = 0; temp < nowIter; temp++) { //현재 queue에 있는 애들을 기준으로만 탐색 진행
					Pos now = pos.poll();
					result++;
					
					int type = board[now.x][now.y];
					for(int dir = 0; dir < ternal[type][0].length; dir++) {
						int xDir = ternal[type][0][dir];
						int yDir = ternal[type][1][dir];
						int nx = now.x + xDir;
						int ny = now.y + yDir;
						
						if(nx >= 0 && nx < N && ny >= 0 && ny < M && !isVisited[nx][ny] && board[nx][ny] != 0) {
							// 검사 조건이 다 만족했다면, 파이프가 연결될 수 있는 모양인지를 검사
							boolean flag = false;
							
							int nextType = board[nx][ny];
							for(int nextDir = 0; nextDir < ternal[nextType][0].length; nextDir++) {
								int nxDir = ternal[nextType][0][nextDir];
								int nyDir = ternal[nextType][1][nextDir];
								
								if(xDir != 0) {
									if((xDir * -1) == nxDir && yDir == nyDir) flag = true; // x 방향 연결인 경우
								}
								else {
									if(xDir == nxDir && (yDir * -1) == nyDir) flag = true; // y방향 연결인 경우
								}

							}
							if(flag) {
								pos.add(new Pos(nx, ny));
								isVisited[nx][ny] = true;
							}
						}
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
			
		}
		System.out.print(sb);
	}
}
