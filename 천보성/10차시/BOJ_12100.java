import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12100 {
    static int N;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, map);
        System.out.println(ans);
    }

    public static void dfs(int depth, int[][] map){
        if(depth == 5){
            ans = Math.max(ans, getMax(map));
            return;
        }

        for (int dir=0;dir<4;dir++){
            int[][] mapClone = cloneMap(map);
            switch (dir) {
                case 0:
                    moveUp(mapClone);
                    break;
                case 1:
                    moveDown(mapClone);
                    break;
                case 2:
                    moveLeft(mapClone);
                    break;
                case 3:
                    moveRight(mapClone);
                    break;
            }
            dfs(depth + 1, mapClone);
        }
    }
    // 맵 복사본
    public static int[][] cloneMap(int[][] map){
        int[][] temp = new int[N][N];
        for (int i=0;i<N;i++){
            temp[i] = map[i].clone();
        }
        return temp;
    }
    // 가장 큰 수 반환
    public static int getMax(int[][] map){
        int max = 0;
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    public static void moveLeft(int[][] map) {
        for (int i=0;i<N;i++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N]; // 한번 합쳐졌는지 확인
            int idx = 0;

            for (int j=0;j<N;j++) {
                if (map[i][j] == 0) continue;

                if (idx > 0 && temp[idx-1] == map[i][j] && !merged[idx-1]) {
                    temp[idx-1] *= 2;
                    merged[idx-1] = true;
                } else {
                    temp[idx++] = map[i][j];
                }
            }

            System.arraycopy(temp, 0, map[i], 0, N);
        }
    }

    public static void moveRight(int[][] map) {
        for (int i=0;i<N;i++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N];
            int idx = 0;

            for (int j=N-1;j>=0;j--) {
                if (map[i][j] == 0) continue;

                if (idx > 0 && temp[idx-1] == map[i][j] && !merged[idx-1]) {
                    temp[idx-1] *= 2;
                    merged[idx-1] = true;
                } else {
                    temp[idx++] = map[i][j];
                }
            }

            for (int j=0;j<N;j++) {
                map[i][N-1-j] = temp[j];
            }
        }
    }

    public static void moveUp(int[][] map) {
        for (int i=0;i<N;i++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N];
            int idx = 0;

            for (int j=0;j<N;j++) {
                if (map[j][i] == 0) continue;

                if (idx > 0 && temp[idx-1] == map[j][i] && !merged[idx-1]) {
                    temp[idx-1] *= 2;
                    merged[idx-1] = true;
                } else {
                    temp[idx++] = map[j][i];
                }
            }

            for (int j=0;j<N;j++) {
                map[j][i] = temp[j];
            }
        }
    }

    public static void moveDown(int[][] map) {
        for (int i=0;i<N;i++) {
            int[] temp = new int[N];
            boolean[] merged = new boolean[N];
            int idx = 0;

            for (int j=N-1;j>=0;j--) {
                if (map[j][i] == 0) continue;

                if (idx > 0 && temp[idx-1] == map[j][i] && !merged[idx-1]) {
                    temp[idx-1] *= 2;
                    merged[idx-1] = true;
                } else {
                    temp[idx++] = map[j][i];
                }
            }
            for (int j=0;j<N;j++) {
                map[N-1-j][i] = temp[j];
            }
        }

    }
}
