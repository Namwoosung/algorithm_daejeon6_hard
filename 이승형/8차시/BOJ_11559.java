
import java.util.*;
import java.io.*;


/*
 * 1. 입력
 * 2. dfs로 check
 * 		체크 성공시 flag true
 * 3. dfs로 삭제
 * 		삭제 완료 시 flag false
 * 4. 중력 실행
 * 
 */

public class BOJ_11559 {
	
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0, -1, 1};
	static String [][] board = new String[12][6];
	static boolean [][] visited;
	static boolean flag;
	static int result;
	static int count;
	
	public static void main(String[] args) throws IOException{	
		inputs();
		
		solve();

		System.out.println(result);
	}
	
	static void inputs() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<12;i++) {
			board[i] = br.readLine().split("");
		}
	}
	
	static void solve() {
		result = 0;
		while(true) {
			boolean resultFlag = false;
			for(int i=11;i>0;i--) {
				for(int j=0;j<6;j++) {
					if(board[i][j].equals(".")) continue;
					
					count = 1;
					flag = false;
					visited = new boolean[12][6];
					Point now = new Point(i,j,board[i][j]);
					
					// 체크 시작
					dfs(0, now); 
					// 삭제 시작 + 중력 이동 
					if(flag) {
						visited = new boolean[12][6];
						dfs(1,now);
						resultFlag = true;
						flag = false;
					}
				}
			}
			
			if(!resultFlag) {
				break;
			}
			
			doGravitation();
			result++;
		}		
	}
	
	// 체크모드 0, 삭제모드 1
	static void dfs(int mode, Point point) {
		if(mode==0) {
			if(count>=4) {
				flag = true;
				return;
			}
		}else {
			board[point.x][point.y] = ".";
		}
		
		visited[point.x][point.y] = true;
		
		for(int i=0;i<4;i++) {
			int nx = point.x + dx[i];
			int ny = point.y + dy[i];
			
			if(nx<0 || ny<0 || nx>=12 || ny>=6 
					|| visited[nx][ny] 
							|| !board[nx][ny].equals(point.color)) continue;
			
			visited[nx][ny] = true;
			count ++;
			dfs(mode, new Point(nx,ny, point.color));
		}
	}
	
	static void doGravitation() {
		for(int i=0;i<6;i++) {
			ArrayList<Point> temp = new ArrayList<>();
			for(int j=11;j>=0;j--) {
				if(board[j][i].equals(".")) continue;
				temp.add(new Point(j,i,board[j][i]));
				board[j][i]=".";
			}
			int idx = 11;
			for(int j=0;j<temp.size();j++) {
				board[idx--][i]=temp.get(j).color;
			}
		}
	}
	
	static class Point {
		int x,y;
		String color;
		
		public Point(int x, int y, String color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}
}