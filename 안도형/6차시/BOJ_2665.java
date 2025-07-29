package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2665 {
    static int N;
    static int[][] map, dist;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Pair {
        int r, c;
        Pair(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dist[N-1][N-1]);
    }

    static void bfs() {
        Deque<Pair> dq = new ArrayDeque<>();
        dq.add(new Pair(0, 0));
        dist[0][0] = 0;

        while (!dq.isEmpty()) {
            Pair p = dq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                int cost = dist[p.r][p.c] + (map[nr][nc] == 0 ? 1 : 0);

                if (cost < dist[nr][nc]) {
                    dist[nr][nc] = cost;
                    if (map[nr][nc] == 1) {
                        dq.addFirst(new Pair(nr, nc));
                    } else {
                        dq.addLast(new Pair(nr, nc));
                    }
                }
            }
        }
    }
}
