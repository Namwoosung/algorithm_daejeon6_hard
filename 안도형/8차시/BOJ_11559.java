package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;

public class BOJ_11559 {
    static char[][] map = new char[12][6];
    static boolean[][] visited = new boolean[12][6];

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static boolean checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        for(int i = 0; i < 12; i++)
            map[i] = br.readLine().toCharArray();

        do{
            checked = false;

            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 6; j++){
                    if(!visited[i][j] && map[i][j] != '.') puyopuyo(map[i][j], i, j);
                    else visited[i][j] = true;
                }
            }

            if(checked) {
                answer++;   setMap();
            }

        }while(checked);

        System.out.println(answer);
    }
    
    public static void puyopuyo(char color, int r, int c){
        if(visited[r][c]) return;
        visited[r][c] = true;

        Stack<int[]> s = new Stack<>(); //4개 이상되는지 체크용
        ArrayDeque<int[]> dq = new ArrayDeque<>();

        s.push(new int[]{r,c});
        dq.offer(new int[]{r,c});

        while(!dq.isEmpty()){
            int[] pos = dq.poll();

            for(int i = 0; i < 4; i++){
                int nr = pos[0] + dr[i];
                int nc = pos[1] + dc[i];

                if(nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && !visited[nr][nc]){
                    if(color == map[nr][nc]) {
                        dq.offer(new int[]{nr, nc});
                        s.push(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        if(s.size() >= 4) {
            while(!s.isEmpty()){
                int[] pos = s.pop();
                map[pos[0]][pos[1]] = '.';
            }
            checked = true;
        }
    }

    public static void setMap() {
        for (int j = 0; j < 6; j++) {
            int temp = 11;
            while(temp > 0){
                if(map[temp][j] != '.') { temp--; continue; }

                int i;
                for (i = temp - 1; i >= 0; i--) {
                    if (map[i][j] != '.') {
                        map[temp][j] = map[i][j];
                        map[i][j] = '.';
                        temp--;
                        break;
                    }
                }
                if (i < 0) break;
            }
        }
        visited = new boolean[12][6];
    }
}
