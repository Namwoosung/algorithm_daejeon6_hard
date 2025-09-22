import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int K;
	static int[][] board;
	static List<Pos> top; // 최대 높이 좌표 저장
	static int result;
	static Set<Pos> route;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Pos{
		int x;
		int y;
		int nH; //nH는 현재 높이를 의미. board의 값일 수도 있고, k만큼 깍은 값일 수도 있어서 class내의 필드로 저장해둠

		public Pos(int x, int y,int nH) {
			this.x = x;
			this.y = y;
			this.nH = nH;
		}

		@Override
		public boolean equals(Object obj) {
			Pos other = (Pos) obj;
			return ((this.x == other.x) && (this.y == other.y));
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			top = new ArrayList<>();
			
			int maxH = -1;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] > maxH) {
						top.clear();
						maxH = board[i][j];
						top.add(new Pos(i, j, board[i][j]));
					}else if(board[i][j] == maxH) {
						top.add(new Pos(i, j, board[i][j]));
					}
				}
			}
			
			// 봉우리를 시작지점으로 설정해서 경로 탐색 시작
			route = new HashSet<>();
			result = 0;
			for(int i = 0; i < top.size(); i++) {
				route.clear();
				makeRoute(top.get(i), 1, true);
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	
	static void makeRoute(Pos pos, int len, boolean change) {
		route.add(pos);
		result = Math.max(result, len);
		
		for(int dir = 0; dir < 4; dir++) {
			int nx = pos.x + dx[dir];
			int ny = pos.y + dy[dir];
			if(!(nx >= 0 && nx < N && ny >= 0 && ny < N)) continue; // 좌표 밖이면 pass

			Pos next = new Pos(nx, ny, board[nx][ny]);
			if(route.contains(next)) continue; // 이미 포함되어 있으면 pass
			
			if(pos.nH > next.nH) makeRoute(next, len+1, change); // 다음 위치가 현재보다 낮으면 그냥 길 만들기
			else { // 아니라면 change 여부에 따라 달라짐
				if(!change) continue; // 깍을 기회가 없으면 pass
				else {
					if(next.nH - K < pos.nH) { // 깍았을 때, 더 작아질 수 있는 경우
						next.nH = pos.nH - 1; // 현재위치보다 1만큼 작게 하는게 최선(k를 다 깍을 필요 x)
						makeRoute(next, len+1, false);
					}
				}
			}
			
		}
		
		// 복원
		route.remove(pos);
	}
}