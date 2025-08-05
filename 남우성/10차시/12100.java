import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int result = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				result = Math.max(result, board[i][j]);
			}
		}
		
		dfs(board, 0);
		
		System.out.print(result);

	}
	
	static void dfs(int[][] board, int cnt) {
		if(cnt == 5) {
			return;
		}
		
		// case 1: 왼쪽이동
		//배열 복사
		int[][] now = new int[n][n];
		
		boolean preSum = false;
		for(int i = 0; i < n; i++) {
			Deque<Integer> line = new ArrayDeque<Integer>();
			for(int j = 0; j < n; j++) {
				if(board[i][j] == 0) continue;
				
				if(!line.isEmpty() && line.peekLast() == board[i][j] && !preSum) {
					
					int sum = line.pollLast() + board[i][j];
					result = Math.max(result, sum);
					line.add(sum);
					preSum = true;
				}else {
					line.add(board[i][j]);
					preSum = false;
				}
				
				
			}
			
			int index = 0;
			while(!line.isEmpty()) {
				int num = line.poll();
				now[i][index] = num;
				index++;
			}
		}
		
		dfs(now, cnt + 1);
		
		// case2: 오른쪽 이동
		now = new int[n][n];
		
		preSum = false;
		for(int i = 0; i < n; i++) {
			Deque<Integer> line = new ArrayDeque<Integer>();
			for(int j = n-1; j >= 0; j--) {
				if(board[i][j] == 0) continue;
				
				if(!line.isEmpty() && line.peekLast() == board[i][j] && !preSum) {
					int sum = line.pollLast() + board[i][j];
					result = Math.max(result, sum);
					line.add(sum);
					preSum = true;
				}else {
					line.add(board[i][j]);
					preSum = false;
				}
				
				
			}
			
			int index = n-1;
			while(!line.isEmpty()) {
				int num = line.poll();
				now[i][index] = num;
				index--;
			}
		}
		dfs(now, cnt + 1);
		
		// case3: 위 이동
		now = new int[n][n];
		
		preSum = false;
		for(int j = 0; j < n; j++) {
			Deque<Integer> line = new ArrayDeque<Integer>();
			for(int i = 0; i < n; i++) {
				if(board[i][j] == 0) continue;
				
				if(!line.isEmpty() && line.peekLast() == board[i][j] && !preSum) {
					int sum = line.pollLast() + board[i][j];
					result = Math.max(result, sum);
					line.add(sum);
					preSum = true;
				}else {
					line.add(board[i][j]);
					preSum = false;
				}
				
				
			}
			
			int index = 0;
			while(!line.isEmpty()) {
				int num = line.poll();
				now[index][j] = num;
				index++;
			}
		}
		dfs(now, cnt + 1);
		
		// case4: 아래 이동
		now = new int[n][n];
		
		preSum = false;
		for(int j = 0; j < n; j++) {
			Deque<Integer> line = new ArrayDeque<Integer>();
			for(int i = n-1; i >= 0; i--) {
				if(board[i][j] == 0) continue;
				
				if(!line.isEmpty() && line.peekLast() == board[i][j] && !preSum) {
					int sum = line.pollLast() + board[i][j];
					result = Math.max(result, sum);
					line.add(sum);
					preSum = true;
				}else {
					line.add(board[i][j]);
					preSum = false;
				}
				
				
			}
			
			int index = n-1;
			while(!line.isEmpty()) {
				int num = line.poll();
				now[index][j] = num;
				index--;
			}
		}
		dfs(now, cnt + 1);
	}
}
