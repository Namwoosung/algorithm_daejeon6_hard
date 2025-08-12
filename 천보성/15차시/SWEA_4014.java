import java.io.*;
import java.util.*;

public class SWEA_4014 {

    static int N, X;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case=1;test_case<=T;test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<N;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            for (int i=0;i<N;i++) {
                int[] line = new int[N];
                System.arraycopy(map[i], 0, line, 0, N);
                if (canBuild(line)){
                    answer++;
                }
            }

            for (int j=0;j<N;j++) {
                int[] line = new int[N];
                for (int i=0;i<N;i++){
                    line[i] = map[i][j];
                }
                if (canBuild(line)){
                    answer++;
                }
            }

            sb.append('#').append(test_case).append(' ');
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static boolean canBuild(int[] line) {
        boolean[] used = new boolean[N];

        for (int i=0;i<N-1;i++) {
            int diff = line[i+1] - line[i];
            
            if (diff == 0) continue;
            if (diff >= 2 || diff <= -2) return false;
            if (diff == 1) {
                int h = line[i];

                for (int k=0;k<X;k++) {
                    int idx = i - k;
                    if (idx < 0 || idx >= N) return false;
                    if (line[idx] != h || used[idx]) return false;
                }
                
                for (int k=0;k<X;k++) {
                    used[i-k] = true;
                }
            }

            else if (diff == -1) {
                int h = line[i + 1];

                for (int k=1;k<=X;k++) {
                    int idx = i + k;
                    if (idx < 0 || idx >= N) return false;
                    if (line[idx] != h || used[idx]) return false;
                }
                
                for (int k=1;k<=X;k++) {
                    used[i+k] = true;
                }
            }
        }
        return true;
    }

}
