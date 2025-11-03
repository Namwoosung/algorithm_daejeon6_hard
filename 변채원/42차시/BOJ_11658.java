import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11658 {
    public static int n;

    public static int[][] arr;
    public static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][n + 1];
        tree = new int[4 * n][4 * n];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initx(1, 1, n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            if(w == 0) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int diff = c - arr[x][y];
                arr[x][y] = c;
                updatex(1, 1, n, x, y, diff);
            } else if(w == 1) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                sb.append(queryx(1, 1, n, x1, x2, y1, y2)).append("\n");
            }
        }

        System.out.println(sb);
    }

    // y축 세그트리
    public static int inity(int nodex, int sx, int ex, int nodey, int sy, int ey) {
        if(sy == ey) {
            int midx = (sx + ex) / 2;
            if(sx == ex) {
                return tree[nodex][nodey] = arr[sx][sy];
            } else {
                return tree[nodex][nodey]
                    = inity(nodex * 2, sx, midx, nodey, sy, ey) +
                      inity(nodex * 2 + 1, midx + 1, ex, nodey, sy, ey);
            }
        }

        int midy = (sy + ey) / 2;
        return tree[nodex][nodey] 
            = inity(nodex, sx, ex, nodey * 2, sy, midy) +
              inity(nodex, sx, ex, nodey * 2 + 1, midy + 1, ey);
    }

    // x축 세그트리
    public static void initx(int nodex, int sx, int ex) {
        if(sx != ex) {
            int midx = (sx + ex) / 2;
            initx(nodex * 2, sx, midx);
            initx(nodex * 2 + 1, midx + 1, ex);
        }
        inity(nodex, sx, ex, 1, 1, n);
    }

    public static void updatey(int nodex, int nodey, int sy, int ey, int y, int diff) {
        if(y < sy || y > ey) return;
        tree[nodex][nodey] += diff;
        if(sy != ey) {
            int mid = (sy + ey) / 2;
            updatey(nodex, nodey * 2, sy, mid, y, diff);
            updatey(nodex, nodey * 2 + 1, mid + 1, ey, y, diff);
        }
    }

    public static void updatex(int nodex, int sx, int ex, int x, int y, int diff) {
        if(x < sx || x > ex) return;
        updatey(nodex, 1, 1, n, y, diff);
        if(sx != ex) {
            int mid = (sx + ex) / 2;
            updatex(nodex * 2, sx, mid, x, y, diff);
            updatex(nodex * 2 + 1, mid + 1, ex, x, y, diff);
        }
    }

    public static int queryy(int nodex, int nodey, int sy, int ey, int y1, int y2) {
        if(y2 < sy || ey < y1) return 0;
        if(y1 <= sy && ey <= y2) return tree[nodex][nodey];
        int mid = (sy + ey) / 2;
        return queryy(nodex, nodey * 2, sy, mid, y1, y2) 
            + queryy(nodex, nodey * 2 + 1, mid + 1, ey, y1, y2);
    }

    public static int queryx(int nodex, int sx, int ex, int x1, int x2, int y1, int y2) {
        if(x2 < sx || ex < x1) return 0;
        if(x1 <= sx && ex <= x2) return queryy(nodex, 1, 1, n, y1, y2);
        int mid = (sx + ex) / 2;
        return queryx(nodex * 2, sx, mid, x1, x2, y1, y2) +
            queryx(nodex * 2 + 1, mid + 1, ex, x1, x2, y1, y2);
    }
}
