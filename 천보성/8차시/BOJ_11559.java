import java.io.*;
import java.util.*;


public class BOJ_11559 {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static boolean[] checkExplode;  // 해당 열에서 폭발이 있었는지 확인 하는 배열
    static boolean checkEnd;        // 게임이 끝나는 지 확인

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        for(int i=0;i<12;i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = -1;

        while(!checkEnd) {
            answer++;
            checkEnd = true;
            checkExplode = new boolean[6];

            for(int i=11;i>=0;i--) {
                for(int j=0;j<6;j++) {
                    if(map[i][j] != '.') {
                        explode(map[i][j], i, j);

                    }
                }
            }

            for(int i=0;i<6;i++) {
                if(checkExplode[i]) {
                    fallDown(i);
                }
            }
        }
        System.out.println(answer);

    }

    // bfs로 연결된 같은 문자 세기
    public static void explode(char color, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[12][6];
        ArrayList<int[]> list = new ArrayList<>();     // 같은 문자의 위치를 저장하는 리스트

        queue.add(new int[] {x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            list.add(now);

            for(int i=0;i<4;i++) {
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= 12 || nextY >= 6 || visited[nextX][nextY]) continue;
                if(map[nextX][nextY] == color) {
                    visited[nextX][nextY] = true;
                    queue.add(new int[] {nextX, nextY});
                }
            }
        }

        //리스트 사이즈가 4 이상이면 폭발
        if(list.size() >= 4) {
            checkEnd = false;                     // 한번이라도 폭발이 있다면 게임은 안끝났음
            for(int[] point : list) {
                map[point[0]][point[1]] = '.';    // 해당 위치를 . 으로 초기화
                checkExplode[point[1]] = true;    // 해당 열에서 폭발 있었다고 체크
            }
        }
    }

    // 폭발로 생긴 빈공간을 채우는 메서드
    public static void fallDown(int y) {
        int index = 11;

        for(int i=11;i>=0;i--) {
            if(map[i][y] != '.') {
                map[index--][y] = map[i][y];
            }
        }
        while(index >= 0) {
            map[index--][y] = '.';
        }

    }
}
