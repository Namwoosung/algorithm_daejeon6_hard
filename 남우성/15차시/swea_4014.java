import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] board;
	static int N;
	static int X;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); X = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			
			for(int i = 0; i < N; i++) {
				if(checkRow(i)) result++;
				if(checkCol(i)) result++;
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static boolean checkRow(int index) {
		int before = board[index][0];
		boolean[] isIncline = new boolean[N]; // 경사로 중첩 방지를 위해 경사로를 넣으면 해당 위치를 저장
		
		for(int i = 1; i < N; i++) {
			if(board[index][i] == before) continue; //이전과 같으면 고려 x
			else if(board[index][i] - before == 1) { //한 칸 높아진 경우
				if(i-X < 0) return false; //경사로를 넣기엔 범위를 넘어간 경우

				// 현재 위치부터 X만큼은 전부 before과 같아야함
				int stand = before;
				for(int k = i-1; k >= i-X; k--) {
					if(board[index][k] != stand || isIncline[k]) return false; // 높이가 기준과 다르거나 이미 경사로가 존재하면 실패
					isIncline[k] = true;
				}
				
			}else if(before - board[index][i] == 1) { //한 칸 낮아진 경우
				if(i+X-1 >= N) return false; //경사로를 넣기엔 범위를 넘어간 경우
				
				int stand = board[index][i];
				for(int k = i; k <= i+X-1; k++) {
					if(board[index][k] != stand || isIncline[k]) return false; // 높이가 기준과 다르거나 이미 경사로가 존재하면 실패
					isIncline[k] = true;				}
			}
			else return false; // 높이 차이가 2이상이면 실패
			
			before = board[index][i];
		}
		return true;
	}
	
	static boolean checkCol(int index) {
		int before = board[0][index];
		boolean[] isIncline = new boolean[N]; // 경사로 중첩 방지를 위해 경사로를 넣으면 해당 위치를 저장
		
		for(int i = 1; i < N; i++) {
			if(board[i][index] == before) continue; //이전과 같으면 고려 x
			else if(board[i][index] - before == 1) { //한 칸 높아진 경우
				if(i-X < 0) return false; //경사로를 넣기엔 범위를 넘어간 경우

				// 현재 위치부터 X만큼은 전부 before과 같아야함
				int stand = before;
				for(int k = i-1; k >= i-X; k--) {
					if(board[k][index] != stand || isIncline[k]) return false; // 높이가 기준과 다르거나 이미 경사로가 존재하면 실패
					isIncline[k] = true;
				}
				
			}else if(before - board[i][index] == 1) { //한 칸 낮아진 경우
				if(i+X-1 >= N) return false; //경사로를 넣기엔 범위를 넘어간 경우
				
				int stand = board[i][index];
				for(int k = i; k <= i+X-1; k++) {
					if(board[k][index] != stand || isIncline[k]) return false; // 높이가 기준과 다르거나 이미 경사로가 존재하면 실패
					isIncline[k] = true;				}
			}
			else return false; // 높이 차이가 2이상이면 실패
			
			before = board[i][index];
		}
		return true;
	}
}