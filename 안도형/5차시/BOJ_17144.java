package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {
    static int[][] map;
    static int[][] subMap = new int [50][50];
    static int R;
    static int C;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] ac = new int[2][2];

        map = new int[R][C];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){ ac[1][0] = i; ac[1][1] = j;}
            }
        }
        ac[0][0] = ac[1][0] - 1;
        ac[0][1] = ac[1][1];

        for(int t = 0; t < T; t++){
            diffusion();
            airClean1(ac[0][0], ac[0][1]);
            airClean2(ac[1][0], ac[1][1]);
        }

        int ans = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                ans += map[i][j];
            }
        }
        System.out.println(ans + 2);
    }

    public static void diffusion(){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] <= 0) continue;

                int diff = map[i][j] / 5;
                for(int k = 0; k < 4; k++){
                    int nr = dr[k] + i;
                    int nc = dc[k] + j;

                    if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1){
                        subMap[nr][nc] += diff;
                        subMap[i][j] -= diff;
                    }
                }
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++) {
                map[i][j] += subMap[i][j];
                subMap[i][j] = 0;
            }
        }
    }

    public static void airClean1(int r, int c) {
        for (int i = r - 1; i > 0; i--) {
            map[i][c] = map[i - 1][c];
        }
        map[0][c] = 0;

        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }

        for (int i = 0; i < r; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }

        for (int i = C - 1; i > 1; i--) {
            map[r][i] = map[r][i - 1];
        }

        map[r][1] = 0;
        map[r][c] = -1;
    }

    public static void airClean2(int r, int c) {
        for (int i = r + 1; i < R - 1; i++) {
            map[i][c] = map[i + 1][c];
        }
        map[R - 1][c] = 0;

        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }

        for (int i = R - 1; i > r; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        for (int i = C - 1; i > 1; i--) {
            map[r][i] = map[r][i - 1];
        }

        map[r][1] = 0;
        map[r][c] = -1;
    }

}
