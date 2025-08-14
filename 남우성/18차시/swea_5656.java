import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int K;
	static int M;
	static int N;
	static int result = 0;
	
	static class Brick{
		int x;
		int y;
		int power;
		
		Brick(int x, int y, int power){
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
			int[][] board = new int[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = Integer.MAX_VALUE;
			
			breakBrick(1, board);
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static void breakBrick(int turn, int[][] board) {
		if(turn > K) { // 모든 턴을 다 한 경우
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(board[i][j] != 0) cnt++;
				}
			}
			result = Math.min(result, cnt);
			return;
		}
		
		int[][] now = new int[N][M];
		for(int col =0; col < M; col++) { // 최대 M개의 case에 대해 모두 전개
			// 배열을 복사
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					now[i][j] = board[i][j];
				}
			}
			
			Deque<Brick> queue = new ArrayDeque<>();
			// 처음 깨지는 벽돌을 queue에 추가
			for(int i = 0; i < N; i++) {
				if(now[i][col] != 0) {
					queue.add(new Brick(i, col, now[i][col]));
					now[i][col] = 0; // 깨졌으니 0으로 초기화
					break;
				}
			}
			
			// 연쇄 작용 처리
			while(!queue.isEmpty()) {
				Brick nBrick = queue.poll();
				
				for(int i = nBrick.x - nBrick.power + 1; i <= nBrick.x + nBrick.power - 1; i++) {
					if(i >= 0  && i < N && now[i][nBrick.y] != 0) {
						queue.add(new Brick(i, nBrick.y, now[i][nBrick.y]));
						now[i][nBrick.y] = 0;
					}
				}
				
				for(int j = nBrick.y - nBrick.power + 1; j <= nBrick.y + nBrick.power -1; j++) {
					if( j >= 0  && j < M && now[nBrick.x][j] != 0) {
						queue.add(new Brick(nBrick.x, j, now[nBrick.x][j]));
						now[nBrick.x][j] = 0;
					}
				}
			}
			// 아래로 내리기
			fallDown(now);
			breakBrick(turn + 1, now); // 다음 호출
		}
	}
	
	// 벽돌을 아래로 내리는 함수
	static void fallDown(int[][] board) {
		List<Integer> bricks;
		for(int j = 0; j < M; j++) {
			bricks = new ArrayList<>();
			for(int i = N-1; i >=0; i--) {
				if(board[i][j] == 0) continue;
				bricks.add(board[i][j]);
				board[i][j] = 0;
			}
			for(int i = 0; i < bricks.size(); i++) {
				board[N-1-i][j] = bricks.get(i);
			}
		}
	}
}
