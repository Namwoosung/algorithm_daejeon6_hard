import java.io.*;
import java.util.*;

public class BOJ_5213 {
    static class Tile {
        int num, tileNum;
        
        Tile(int num, int tileNum) {
            this.num = num;
            this.tileNum = tileNum;
        }
    }
    static class Pos {
        int r, c;
        
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static int N, lastTile;
    static Tile[][] tiles;
    static int [] fromTileNum;
    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 

        N = Integer.parseInt(br.readLine());
        tiles = new Tile[N][N * 2];
        lastTile = N * N - N / 2; // 마지막 타일 번호
        fromTileNum = new int[lastTile + 1];
        
        // 타일 입력
        int tileNum = 1;
        for (int r = 0; r < N; r++) {
            int count = N;
            int col = 0;
            if (r % 2 == 1) {
                count = N - 1;
                col = 1;
            }
            for (int c = 0; c < count; c++) {
                st = new StringTokenizer(br.readLine());
                tiles[r][col++] = new Tile(Integer.parseInt(st.nextToken()), tileNum);
                tiles[r][col++] = new Tile(Integer.parseInt(st.nextToken()), tileNum++);
            }
        }
        
        bfs();
        
        // 도착한 가장 큰 타일 찾기
        int searchTile = 0;
        for(int i = lastTile; i >= 1; i--) {
        	if(fromTileNum[i] != 0) {
        		searchTile = i;
        		break;
        	}
        }
        
        // 경로 역추적
        Deque<Integer> stack = new ArrayDeque<>();
        // 1번 타일에 올 때까지 스택에 삽입
        while(searchTile != -1) {
        	stack.offer(searchTile);
        	searchTile = fromTileNum[searchTile];
        }
        
        // 스택에서 빼기
        StringBuilder sb = new StringBuilder();
        sb.append(stack.size()).append("\n");
        int repeat = stack.size();
        for(int i = 0; i < repeat; i++) {
        	sb.append(stack.pollLast()).append(" ");
        }
        
        System.out.println(sb);
    }
    
    public static void bfs() {
        Deque<Pos> deque = new ArrayDeque<>();
        int[][] cost = new int[N][N * 2]; // 각 칸 이동 비용
        for (int i = 0; i < N; i++) {
        	Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        int[] bestCostOfTile = new int[lastTile + 1]; // 각 타일의 최소 도달 비용
        Arrays.fill(bestCostOfTile, Integer.MAX_VALUE);

        // 1번 타일의 두 칸 모두 방문 처리 (0, 0)은 이동할 칸이 (0, 1) 밖에 없음
        deque.offer(new Pos(0, 1));
        cost[0][0] = 1;
        cost[0][1] = 1;
        bestCostOfTile[1] = 1;
        fromTileNum[1] = -1; // 1번 타일은 시작점

        while (!deque.isEmpty()) {
            Pos pos = deque.poll();
            Tile curTile = tiles[pos.r][pos.c];

//            if(curTile.tileNum == lastTile) break;

            for (int i = 0; i < 4; i++) {
                int nr = pos.r + dr[i];
                int nc = pos.c + dc[i];

                // 범위 밖 또는 null이면 continue
                if (nr < 0 || nr >= N || nc < 0 || nc >= N * 2 || tiles[nr][nc] == null) 
                	continue;

                Tile nextTile = tiles[nr][nc];
                // 같은 타일의 이동은 먼저 탐색하게끔
                if (curTile.tileNum == nextTile.tileNum) {
                	if(cost[pos.r][pos.c] < cost[nr][nc]) {
	                	cost[nr][nc] = cost[pos.r][pos.c];
	                	deque.offerFirst(new Pos(nr, nc));
                	}
                } else if (curTile.num == nextTile.num) { // 다른 타일로의 이동
                	if(cost[pos.r][pos.c] + 1 < cost[nr][nc]) {
	                	cost[nr][nc] = cost[pos.r][pos.c] + 1;
	                	deque.offerLast(new Pos(nr, nc));
	                	// 어느 타일에서 왔는지 기록
	                	if(bestCostOfTile[nextTile.tileNum] > cost[nr][nc]) {
	                		bestCostOfTile[nextTile.tileNum] = cost[nr][nc];
	                		fromTileNum[nextTile.tileNum] = curTile.tileNum;
	                	}
                	}
                }
            }
        }
    }
}