import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Cell implements Comparable<Cell>{
		int x;
		int y;
		int life;
		int nowTime;
		boolean active;
		public Cell(int x, int y, int life, int nowTime) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.nowTime = nowTime;
			active = false;
		}
		
		// 생명력 기준 내림차순 정렬 => 추후 세포 증식 시 더 큰 생명력이 먼저 선점하기 위해
		@Override
		public int compareTo(Cell other) {
			return other.life - this.life;
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken()); int K = Integer.parseInt(st.nextToken());
			
			// 세포 전파 가능 여부 + 동일 세포 전파 시 결정을 위한 배열
			// k의 최대값이 300이라 1000정도 크기면 모든 case를 커버가능
			int[][] board = new int[1000][1000];
			//  살아있는 Cell을 관리(매번 board를 전부 탐색하는 건 비효율적 => 살아있는 cell만 관리)
			List<Cell> aliveCell = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					// 대충 0, 0을 500, 500으로 정규화
					board[500+i][500+j] = Integer.parseInt(st.nextToken());
					if(board[500+i][500+j] != 0) aliveCell.add(new Cell(500+i, 500+j, board[500+i][500+j], 0));
				}
			}
			
			PriorityQueue<Cell> candiateCell;
			List<Cell> removeCell;
			for(int time = 1; time <= K; time++) {
				// 이번 시행에서 증식될 수 있는 세포들의 후보
				candiateCell = new PriorityQueue<>();
				removeCell = new ArrayList<>();
				
				for(int i = 0; i < aliveCell.size(); i++) {
					Cell nowCell = aliveCell.get(i);
					if(!nowCell.active) { // 아직 활성화 전이라면 시간을 ++
						nowCell.nowTime++;
						
						if(nowCell.nowTime == nowCell.life) { // 자신의 생명력과 동일해지면
							nowCell.active = true;
						}
					}else { //활성화 상태라면 시간을 --
						nowCell.nowTime--;
						
						if(nowCell.life - 1 == nowCell.nowTime) { // 활성화 후 1시간 지남 => 증식
							
							// 4 방향 검토
							for(int dir = 0; dir < 4; dir++) {
								int nx = nowCell.x + dx[dir];
								int ny = nowCell.y + dy[dir];
								
								if(board[nx][ny] != 0) continue; //이미 세포가 있는 상황이면 pass
								candiateCell.add(new Cell(nx, ny, nowCell.life, 0)); //아니라면 후보에 추가
							}
						}
						if(nowCell.nowTime == 0) { //시간이 끝나면 삭제될 cell에 추가
							removeCell.add(nowCell);
						}
					}
				}
				aliveCell.removeAll(removeCell); // 삭제될 애들을 지우기
				
				while(!candiateCell.isEmpty()) {
					Cell now = candiateCell.poll();
					if(board[now.x][now.y] == 0) { // 최대 cell이면 board에 반영하고 alive에 추가
						board[now.x][now.y] = now.life;
						aliveCell.add(now);
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(aliveCell.size()).append("\n");
		}
		System.out.print(sb);
	}
}
