import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
	static boolean isEnd;
	static int result;
	static char[][] board;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Pos{
		int x;
		int y;
		char color;
		
		public Pos(int x, int y, char color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new char[12][];
		
		for(int i = 0; i < 12; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		isEnd = false;
		result = -1;
		while(!isEnd) {
			result++;
			bomb();
		}
		
		System.out.println(result);
	}
	
	static void bomb() {
		isEnd = true;
		Deque<Pos> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[12][6];
		
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 6; j++) {
				if(board[i][j] == '.') continue; // .이면 pass
				
				if(!isVisited[i][j]) {
					List<Pos> stores = new ArrayList<>();
					queue.add(new Pos(i,j, board[i][j]));
					
					while(!queue.isEmpty()) { // 연쇄 개수 카운팅, bfs
						Pos now = queue.poll();
						isVisited[now.x][now.y] = true;
						stores.add(now);
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = now.x + dx[dir];
							int ny = now.y + dy[dir];
							
							if(nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !isVisited[nx][ny] && board[nx][ny] == now.color) {
								queue.add(new Pos(nx, ny, board[nx][ny]));
							}
						}
					}
					
					if(stores.size() >= 4) { // 4개 이상 연속되면 .으로 변경
						for(Pos removedPos : stores) {
							board[removedPos.x][removedPos.y] = '.';
						}
						isEnd = false; //한 번이라도 터지면 게임 계속 진행
					}
				}
			}
		}
		if(!isEnd) move(); // 게임이 진행되어야 하면 구슬들을 아래로 내림
	}
	
	static void move() {
		// 아래에서 위로 구슬들을 저장
		for(int j = 0; j < 6; j++) {
			List<Pos> puyos = new ArrayList<>();
			for(int i = 11; i >= 0; i--) {
				if(board[i][j] != '.') { //아래에서 부터 구슬을 순서대로 저장
					puyos.add(new Pos(i, j, board[i][j]));
					board[i][j] = '.';
				}
			}
			
			int index = 11;
			for(Pos puyo : puyos) { //아래에서 부터 저장한 puyo들을 새로 쌓기
				board[index][j] = puyo.color;
				index--;
			}
		}
	}
} = new ArrayList<>();
					queue.add(new Pos(i,j, board[i][j]));
					
					while(!queue.isEmpty()) { // 연쇄 개수 카운팅, bfs
						Pos now = queue.poll();
						isVisited[now.x][now.y] = true;
						stores.add(now);
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = now.x + dx[dir];
							int ny = now.y + dy[dir];
							
							if(nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !isVisited[nx][ny] && board[nx][ny] == now.color) {
								queue.add(new Pos(nx, ny, board[nx][ny]));
							}
						}
					}
					
					if(stores.size() >= 4) { // 4개 이상 연속되면 .으로 변경
						for(Pos removedPos : stores) {
							board[removedPos.x][removedPos.y] = '.';
						}
						isEnd = false; //한 번이라도 터지면 게임 계속 진행
					}
				}
			}
		}
		if(!isEnd) move(); // 게임이 진행되어야 하면 구슬들을 아래로 내림
	}
	
	static void move() {
		// 아래에서 위로 구슬들을 저장
		for(int j = 0; j < 6; j++) {
			List<Pos> puyos = new ArrayList<>();
			for(int i = 11; i >= 0; i--) {
				if(board[i][j] != '.') { //아래에서 부터 구슬을 순서대로 저장
					puyos.add(new Pos(i, j, board[i][j]));
					board[i][j] = '.';
				}
			}
			
			int index = 11;
			for(Pos puyo : puyos) { //아래에서 부터 저장한 puyo들을 새로 쌓기
				board[index][j] = puyo.color;
				index--;
			}
		}
	}
}