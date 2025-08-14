package codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1767 {
	
	static class Processor{
		int x;
		int y;
		Processor(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static ArrayList<Processor> list;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int N;
	static boolean[][] map;
	static int maxCnt;
	static int minLen;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int testcase=1;testcase<=T;testcase++) {
			N = Integer.parseInt(br.readLine());
			
			map = new boolean[N][N];
			list = new ArrayList<>();
			maxCnt = 0;
			minLen = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					if (st.nextToken().equals("1")) {
						map[i][j] = true;
						if(i == 0 || i == N-1 || j == 0 || j == N-1) continue;
						list.add(new Processor(i,j));
					}
				}
			}
			
			dfs(0, 0, new boolean[N][N], 0);
			
			sb.append('#').append(testcase).append(' ');
			sb.append(minLen);
			sb.append('\n');
			
		}
		System.out.println(sb);

	}
	
	public static void dfs(int idx, int length, boolean[][] used, int cnt) {
		if(idx == list.size()) {
			// 전원을 킨 프로세서가 더 많으면 무조건 갱신
			if(maxCnt < cnt) {
				maxCnt = cnt;
				minLen = length;
			}
			// 같다면 최소 비교
			else if(cnt == maxCnt) {
				minLen = Math.min(minLen, length);
			}
			
			return;
		}
		
		// 남은 프로세서 수를 더해도 최댓값에 못미친다면
		if(cnt + list.size() - idx < maxCnt) {
			return;
		}
		// 프로세서 수는 같지만 전선 길이가 더 크다면
		if(cnt + list.size() - idx == maxCnt && minLen < length) {
			return;
		}
		
		Processor p = list.get(idx);
		
		for(int dir=0;dir<4;dir++) {
			int nextX = p.x + dx[dir];
			int nextY = p.y + dy[dir];
			int len = 0;
			
			while(true) {
				//범위 밖
				if(outOfBound(nextX, nextY)) {
					break;
				}
				// 전선이 겹치는지, 프로세서가 존재하면 연결 불가능!
				if(used[nextX][nextY] || map[nextX][nextY]) {
					len = 0;
					break;
				}
				
				len++;
				used[nextX][nextY] = true;
				nextX += dx[dir];
				nextY += dy[dir];
			}
			
			// 프로세서를 선택안할 때
			if(len == 0) {
				dfs(idx+1, length, used, cnt);
			}
			// 선택할 때
			else {
				dfs(idx+1, length + len, used, cnt+1);
			}
			
			// 연결했던 곳 다시 해제
			while(true) {
				if(p.x == nextX && p.y == nextY) break;
				nextX += dx[(dir + 2) % 4];
				nextY += dy[(dir + 2) % 4];
				used[nextX][nextY] = false;
			}
		}
	}
	
	public static boolean outOfBound(int x, int y) {
		return x < 0 || y < 0 || x >= N || y >= N;
	}
	
}
