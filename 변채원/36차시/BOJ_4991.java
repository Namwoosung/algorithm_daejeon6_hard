import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4991 {
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static char[][] map;
    public static int[][] dust;
    public static int[][] result;

    public static int w, h;
    public static int size;
    public static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;

            int id = 1;
                
            ArrayList<int[]> qList = new ArrayList<>();
            map = new char[h][w];
            dust = new int[h][w];
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);

                    if(map[i][j] == 'o') {
                        qList.add(new int[] {i, j, 1});
                        dust[i][j] = 1;
                    }
                    if(map[i][j] == '*') {
                        qList.add(new int[] {i, j, ++id});
                        dust[i][j] = id;
                    }
                }
            }
            size = qList.size() + 1;
            result = new int[size + 1][size + 1];

            for (int i = 0; i < qList.size(); i++) {
                int[] node = qList.get(i);
                bfs(node[0], node[1], node[2]);
            }
            
            min = Integer.MAX_VALUE;
            boolean[] visited = new boolean[size + 1];
            perm(1, 0, visited, 1);
            if(min == Integer.MAX_VALUE)
                sb.append(-1).append("\n");
            else
                sb.append(min).append("\n");
            }
            System.out.println(sb);
    }

    public static void bfs(int x, int y, int id) {
        int[][] cnt = new int[h][w];
        boolean[][] visited = new boolean[h][w];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            x = node[0];
            y = node[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= h || ny < 0 || ny >= w)
                    continue;
                
                if(!visited[nx][ny] && map[nx][ny] != 'x') {
                    visited[nx][ny] = true;
                    cnt[nx][ny] = cnt[x][y] + 1;
                    if(dust[nx][ny] != 0)
                        result[id][dust[nx][ny]] = cnt[nx][ny];
                    q.add(new int[] {nx, ny});
                }
            }
        }
    }



    public static void perm(int start, int ret, boolean[] visited, int cnt) {
        if(cnt == size - 1) {
            min = Math.min(min, ret);
            return;
        }
        
        for (int i = 2; i < size; i++) {
            if(!visited[i] && result[start][i] != 0) {
                visited[i] = true;
                perm(i, ret + result[start][i], visited, cnt + 1);
                visited[i] = false;
            }
        }
    }
}
