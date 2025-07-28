import java.io.*;
import java.util.*;

public class BOJ_2665 {

    static class Point{
        int x;
        int y;
        int cnt;

        Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static char[][] map;

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i=0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.print(bfs());

    }

    public static int bfs() {
        ArrayDeque<Point> pq = new ArrayDeque<>();
        pq.add(new Point(0, 0, 0));
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            Point now = pq.pollFirst();

            if(now.x == N-1 && now.y == N-1) {
                return now.cnt;
            }

            for(int i=0;i<4;i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || visited[nextX][nextY]) continue;

                visited[nextX][nextY] = true;

                if(map[nextX][nextY] == '0') {
                    pq.addLast(new Point(nextX, nextY, now.cnt+1));
                }
                else {
                    pq.addFirst(new Point(nextX, nextY, now.cnt));
                }
            }
        }
        return -1;
    }
}