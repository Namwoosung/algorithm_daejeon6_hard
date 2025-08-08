import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;



public class Solution {

	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int cost;

		public Pos(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pos other) {
			return Integer.compare(this.cost, other.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			for(int i = 0; i < N; i++) {
				board[i] = Arrays.stream(br.readLine().split(""))
						.mapToInt(Integer::parseInt)
						.toArray();
			}
			
			boolean[][] isVisited = new boolean[N][N];	// 방문 처리
			PriorityQueue<Pos> pq = new PriorityQueue<>(); // 가장 경로가 낮은 부분부터 접근하기 위해 PQ로 관리

			// 0번 넣고 탐색 시
			pq.add(new Pos(0, 0, board[0][0]));
			isVisited[0][0] = true;
			int result = 0;
			while(!pq.isEmpty()) {
				Pos now = pq.poll();
				
				if(now.x == N-1 && now.y == N-1) { // 끝에 도달한 경우 바로 종료(PQ를 사용해서 처음 도달한 경우 무조건 최적임이 보장됨)
					result = now.cost;
					break;
				}
				
				for(int dir = 0; dir < 4; dir++) {
					int nx = now.x + dx[dir];
					int ny = now.y + dy[dir];
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
						if(!isVisited[nx][ny]) { // 방문 한 적 없는 경우만 queue에 추가
							pq.add(new Pos(nx, ny, now.cost + board[nx][ny]));
							isVisited[nx][ny] = true;
						}
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
