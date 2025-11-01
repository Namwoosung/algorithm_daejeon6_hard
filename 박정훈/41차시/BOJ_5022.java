import java.io.*;
import java.util.*;

public class BOJ_5022 {
	
	static class Pos {
		int r, c;
		List<Pos> path = new ArrayList<>();
		
		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		Pos(int r, int c, List<Pos> path){
			this.r = r;
			this.c = c;
			for(Pos p : path) {
				this.path.add(p);
			}
		}
	}
	
	static int N, M, min = Integer.MAX_VALUE, sum;
	static Pos A1, A2, B1, B2;
	static boolean[][] isUsed;
	static List<Pos> path;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        A1 = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        A2 = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        B1 = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        B2 = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        
        // A 연결 후 B 탐색
        path = new ArrayList<>();
        sum = 0;
        isUsed = new boolean[N + 1][M + 1];
        isUsed[B1.r][B1.c] = true;
        isUsed[B2.r][B2.c] = true;
        boolean A = bfs(A1, A2);
        if(A) {
        	isUsed = new boolean[N + 1][M + 1];
        	for(Pos pos : path) {
        		isUsed[pos.r][pos.c] = true;
        	}
        	path = new ArrayList<>();
    		boolean B = bfs(B1, B2);
    		if(B) {
    			min = Math.min(min, sum);
    		}
        }
        
        // B 연결 후 A 탐색
        path = new ArrayList<>();
        sum = 0;
        isUsed = new boolean[N + 1][M + 1];
        A1.path = new ArrayList<>();
        B1.path = new ArrayList<>();
        isUsed[A1.r][A1.c] = true;
        isUsed[A2.r][A2.c] = true;
        boolean B = bfs(B1, B2);
        if(B) {
        	isUsed = new boolean[N + 1][M + 1];
        	for(Pos pos : path) {
        		isUsed[pos.r][pos.c] = true;
        	}
        	path = new ArrayList<>();
    		A = bfs(A1, A2);
    		if(A) {
    			min = Math.min(min, sum);
    		}
        }
        
        System.out.println(min == Integer.MAX_VALUE ? "IMPOSSIBLE" : min);
    }

	private static boolean bfs(Pos start, Pos end) {
		Deque<Pos> deque = new ArrayDeque<>();
		isUsed[start.r][start.c] = true;
		start.path.add(new Pos(start.r, start.c));
		deque.offer(start);
		
		while(!deque.isEmpty()) {
			Pos pos = deque.poll();
			
			for(int i = 0 ; i < 4; i++) {
				int nr = pos.r + dr[i];
				int nc = pos.c + dc[i];
				
				// 도착
				if(nr == end.r && nc == end.c) {
					pos.path.add(new Pos(nr, nc));
					// 시작점 빼주기
					sum += pos.path.size() - 1;
					// 사용 경로 저장
					path = pos.path;
					return true;
				}
				
				if(nr < 0 || nr > N || nc < 0 || nc > M) continue;
				if(!isUsed[nr][nc]) {
					isUsed[nr][nc] = true;
					Pos next = new Pos(nr, nc, pos.path);
					next.path.add(new Pos(nr, nc));
					deque.offer(next);
				}
			}
		}
			
		return false;
	}
}