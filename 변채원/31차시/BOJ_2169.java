import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2169 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[n][m];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + map[0][i];
        } // 0행은 오른쪽으로만 갈 수 있음

        for (int i = 1; i < n; i++) {
            int[][] tmp = new int[2][m];
            // 위와 왼쪽 비교
            tmp[0][0] = dp[i - 1][0] + map[i][0];
            for (int j = 1; j < m; j++) {
                tmp[0][j] = Math.max(tmp[0][j - 1], dp[i - 1][j]) + map[i][j];
            }

            // 위와 오른쪽 비교
            tmp[1][m - 1] = dp[i - 1][m - 1] + map[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                tmp[1][j] = Math.max(tmp[1][j + 1], dp[i - 1][j]) + map[i][j];
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(tmp[0][j], tmp[1][j]);
            }
        }

        System.out.println(dp[n-1][m-1]);
    }
}
