import java.util.*;
import java.io.*;

public class BOJ_19236 {
	
	static class Fish implements Comparable<Fish>{
		
		int num;
		int x;
		int y;
		int dir;
		
		Fish(int num, int x, int y, int dir){
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		@Override
		public int compareTo(Fish f) {
			return this.num - f.num;
		}
	}
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static Fish[][] mapOrigin; // 원본 맵
	static int max = 0; // 최댓값
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		mapOrigin = new Fish[4][4];
		
		for(int i=0;i<4;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<4;j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				mapOrigin[i][j] = new Fish(a, i, j, b);
			}
		}
		
		Fish temp = mapOrigin[0][0];
		mapOrigin[0][0] = null;
		Fish shark = new Fish(0, 0, 0, temp.dir); //상어 시작 위치
		
		dfs(temp.num, shark, mapOrigin, 0);
		System.out.print(max);
		
	}
	
	public static void moveFish(Fish shark, Fish[][] map) {
	
		PriorityQueue<Fish> pq = new PriorityQueue<>(); // 번호 순서대로 움직이기 위한 PQ
		
		// pq에 물고기 넣기
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(map[i][j] != null) {
					pq.add(map[i][j]);
				}
			}
		}
		
		
		while(!pq.isEmpty()) {
			Fish now = pq.poll();
			
			// 8방향 좌표 설정
			for(int i=now.dir;i<now.dir+8;i++) {
				int d = i % 8 - 1;
				if(d == -1) d= 7;
				
				int nextX = now.x + dx[d];
				int nextY = now.y + dy[d];
				
				if(nextX < 0 || nextY < 0 || nextX >= 4 || nextY >= 4) continue; // 경계 넘어 못감
				if(shark.x == nextX && shark.y == nextY) continue; // 상어 있는 곳 못감
				
				if(map[nextX][nextY] == null) {  // 빈자리일 때
					map[now.x][now.y] = null;
				}
				else {         // 물고기가 존재할 때 스위칭
					Fish temp = map[nextX][nextY];
					temp.x = now.x;
					temp.y = now.y;
					map[now.x][now.y] = temp;					
				}
				
				map[nextX][nextY] = now;    // 자리 이동
				now.x = nextX;				// 물고기의 x 좌표, y 좌표, 방향 수정
				now.y = nextY;
				now.dir = d + 1;           
				break;
			}
		}
	}
	
	public static void dfs(int total, Fish shark, Fish[][] map, int depth) {
		if(total > max) {
			max = total;     // 최댓값 갱신
		}
		
		if(depth >= 16) {    // 최대로 이동했을때가 16, 그 이상은 갈 필요 없음
			return;
		}
		
		moveFish(shark, map);   // 물고기 먼저 이동
		
		int dir = shark.dir - 1;
		int nextX = shark.x + dx[dir];
		int nextY = shark.y + dy[dir];
				
		// 상어의 움직일 수 있는 루트 탐색
		while(nextX >= 0 && nextY >= 0 && nextX < 4 && nextY < 4) {
			
			if(map[nextX][nextY] != null) {
				Fish temp = map[nextX][nextY];
				map[nextX][nextY] = null;
				dfs(total + temp.num, new Fish(0, nextX, nextY, temp.dir), copyMap(map), depth + 1);
				map[nextX][nextY] = temp;
				
			}
			
			nextX = nextX + dx[dir];
			nextY = nextY + dy[dir];
			
		}
		
	}
	
	/*
	 * 맵 복사, 객체도 새로 만들어 줘야 함. clone() 쓰면 객체는 얕은 복사라 X
	 */
	public static Fish[][] copyMap(Fish[][] map) {
		Fish[][] mapClone = new Fish[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				
				if(map[i][j] == null) {     
					mapClone[i][j] = null;
					continue;
				}
				mapClone[i][j] = new Fish(map[i][j].num, map[i][j].x, map[i][j].y, map[i][j].dir);
			}
		}
		
		return mapClone;
	}
}
