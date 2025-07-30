import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_11559 {
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {-1, 0, 1, 0};
	static List<int[]> posList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    char[][] field = new char[12][6];
	    for(int i = 0; i < 12; i++) {
	    	field[i] = br.readLine().toCharArray();
	    }
	    int chain = 0; // 연쇄 횟수
	    boolean isBomb = false; // 이번 턴에 터졌는 지
	
	    while(true) {
	    	for(int y = 11; y >= 0; y--) { // 아래로 내려오기 때문에 아래부터
	    		for(int x = 0; x < 6; x++) {
	    			if(field[y][x] != '.') { // 뿌요라면
	    				posList.clear(); // 리스트 초기화
	    				boolean[][] visited = new boolean[12][6]; // 방문 초기화
	    				visited[y][x] = true;
	    				posList.add(new int[] {x, y});
	    				// 같은 색 찾기
	    				dfs(x, y, field[y][x], field, visited);
	    				
	    				// 인접한 같은 색이 4개 이상
	    				if(posList.size() >= 4) {
	    					isBomb = true;
	    					for(int i = 0; i < posList.size(); i++) {
	    						int[] pos = posList.get(i);
	    						field[pos[1]][pos[0]] = '.';
	    					}
	    				}
	    			}
	    		}
	    	}
	    	// 터진 게 있다면
	    	if(isBomb) {
	    		down(field); // 밑으로 내리기
	    		chain++; // 연쇄 +1
	    		isBomb = false;
	    	} else { // 터진 게 없다면 종료
	    		break;
	    	}
	    }
	    System.out.print(chain);
	}
	
	public static void dfs(int x, int y, char c, char[][] field, boolean[][] visited) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//범위 밖이거나 방문한 곳
			if(nx < 0 || nx > 5 || ny < 0 || ny > 11 || visited[ny][nx]) continue;
			
			//탐색하는 색과 동일
			if(field[ny][nx] == c) {
				visited[ny][nx] = true;
				posList.add(new int[] {nx, ny});
				dfs(nx, ny, c, field, visited);
			}
		}
	}
	
	public static void down(char[][] field) {
		for(int x = 0; x < 6; x++) {
			for(int y = 11; y >= 0; y--) { //아래 칸부터 탐색
				if(field[y][x] == '.') { // 빈 칸이라면
					for(int p = y - 1; p >= 0; p--) { // 내릴 뿌요 찾기
						if(field[p][x] != '.') { // 뿌요라면
							field[y][x] = field[p][x]; //내리기
							field[p][x] = '.';
							break;
						}
					}
				}
			}
		}
	}
}