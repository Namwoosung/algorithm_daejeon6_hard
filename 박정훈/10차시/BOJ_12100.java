import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_12100 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int N;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	    
	    N = Integer.parseInt(br.readLine());
	    int[][] board = new int[N][N];
	    
	    for(int i = 0; i < N; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	for(int j = 0; j < N; j++) {
	    		board[i][j] = Integer.parseInt(st.nextToken());
	    		max = Math.max(max, board[i][j]);
	    	}
	    }
	    
	    // 상우하좌 이동
	    for(int i = 0; i < 4; i++) {
	    	move(board, i, 0);
	    }
	    
	    System.out.print(max);
	}
	
	public static void move(int[][] board, int d, int count) {
		if(count == 5) return; // 최대 5번 이동
		Deque<Integer> deque = new ArrayDeque<>();
		//보드판 복사
		int[][] copyBoard = new int[N][N];
		for(int i = 0; i < N; i++) {
			copyBoard[i] = Arrays.copyOf(board[i], N);
		}
		
		if(d == 0) { //위로 이동
			for(int x = 0; x < N; x++) {
				for(int y = 0; y < N; y++) { // 위부터 큐에 넣기
					if(copyBoard[y][x] != 0) {
						deque.offer(copyBoard[y][x]);
						copyBoard[y][x] = 0;
					}
				}
				flush(copyBoard, deque, x, d);
			}
		} else if(d == 1) { //오른쪽으로 이동
			for(int y = 0; y < N; y++) {
				for(int x = N - 1; x >= 0; x--) { // 오른쪽부터 찾기
					if(copyBoard[y][x] != 0) {
						deque.offer(copyBoard[y][x]);
						copyBoard[y][x] = 0;
					}
				}
				flush(copyBoard, deque, y, d);
			}
		} else if(d == 2) { //아래로 이동
			for(int x = 0; x < N; x++) {
				for(int y = N - 1; y >= 0; y--) { // 아래부터 찾기
					if(copyBoard[y][x] != 0) {
						deque.offer(copyBoard[y][x]);
						copyBoard[y][x] = 0;
					}
				}
				flush(copyBoard, deque, x, d);
			}
		} else if(d == 3){ //왼쪽으로 이동
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) { // 왼쪽부터 찾기
					if(copyBoard[y][x] != 0) {
						deque.offer(copyBoard[y][x]);
						copyBoard[y][x] = 0;
					}
				}
				flush(copyBoard, deque, y, d);
			}
		}
		
		//다음 이동 4방향
		for(int i = 0; i < 4; i++) {
			move(copyBoard, i, count+1);
		}
	}
	
	public static void flush(int[][] board, Deque<Integer> deque, int pos, int d) {
		int x;
		int y;
		// 이동 값에 따라 x 또는 y고정
		if(d == 0) {
			x = pos;
			y = 0;
		} else if(d == 1) {
			x = N - 1;
			y = pos;
		} else if(d == 2) {
			x = pos;
			y = N - 1;
		} else {
			x = 0;
			y = pos;
		}
		
		// 큐에서 하나씩 빼서 배치
		while(!deque.isEmpty()) {
			int num = deque.poll();
			
			if(deque.isEmpty()) { //마지막 값
				board[y][x] = num;
				break;
			}
			
			if(num == deque.peek()) { // 합치기 가능
				int merge = num + deque.poll();
				board[y][x] = merge;
				max = Math.max(max, merge);
			} else { // 합치기 불가능
				board[y][x] = num;
			}
			
			// 다음 배치 위치
			x += -dx[d];
			y += -dy[d];
		}
	}
}