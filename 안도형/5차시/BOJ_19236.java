package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19236 {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 위부터 반시계
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];
        int[][] fish = new int[17][3];
        boolean[] isDeath = new boolean[17];

        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = num;
                fish[num] = new int[]{i, j, dir};
            }
        }

        int dir = fish[map[0][0]][2];
        isDeath[map[0][0]] = true;
        map[0][0] = -1;

        dfs(map, fish, isDeath, 0, 0, dir, map[0][0]);
        System.out.println(max);
    }

    static void dfs(int[][] map, int[][] fish, boolean[] isDeath, int sharkR, int sharkC, int sdir, int score) {
        max = Math.max(max, score);

        int[][] newMap = copyMap(map);
        int[][] newFish = copyFish(fish);
        boolean[] newDeath = isDeath.clone();

        for (int i = 1; i <= 16; i++) {
            if (newDeath[i]) continue;

            int r = newFish[i][0];
            int c = newFish[i][1];
            int dir = newFish[i][2];

            for (int d = 0; d < 8; d++) {
                int nd = (dir + d) % 8;
                int nr = r + dr[nd];
                int nc = c + dc[nd];

                if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || newMap[nr][nc] == -1) continue;

                int target = newMap[nr][nc];

                // 이동
                newMap[r][c] = target;
                newMap[nr][nc] = i;

                newFish[i][0] = nr; newFish[i][1] = nc; newFish[i][2] = nd;

                if (target != 0) {
                    newFish[target][0] = r;
                    newFish[target][1] = c;
                }

                break;
            }
        }

        for (int i = 1; i <= 3; i++) {
            int nr = sharkR + dr[sdir] * i;
            int nc = sharkC + dc[sdir] * i;

            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) break;
            if (newMap[nr][nc] == 0) continue;

            int[][] mapCopy = copyMap(newMap);
            int[][] fishCopy = copyFish(newFish);
            boolean[] deathCopy = newDeath.clone();

            int target = mapCopy[nr][nc];
            int nextDir = fishCopy[target][2];
            mapCopy[sharkR][sharkC] = 0;
            mapCopy[nr][nc] = -1;
            deathCopy[target] = true;

            dfs(mapCopy, fishCopy, deathCopy, nr, nc, nextDir, score + target);
        }
    }

    static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[4][4];
        for (int i = 0; i < 4; i++) newMap[i] = map[i].clone();
        return newMap;
    }

    static int[][] copyFish(int[][] fish) {
        int[][] newFish = new int[17][3];
        for (int i = 1; i <= 16; i++) {
            newFish[i][0] = fish[i][0];
            newFish[i][1] = fish[i][1];
            newFish[i][2] = fish[i][2];
        }
        return newFish;
    }
}
