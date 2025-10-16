import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0: 왼쪽에서 온 거, 1: 위에서 온거: 2: 오른쪽에서 온 거
        int[][][] dp = new int[N][M][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        dp[0][0][0] = board[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j][0] = dp[0][j - 1][0] + board[0][j];
        }

        for (int i = 1; i < N; i++) {
            // 아래 이동 처리(가장 최적만 처리)
            for (int j = 0; j < M; j++) {
                dp[i][j][1] = Math.max(Math.max(dp[i - 1][j][0], dp[i - 1][j][1]), dp[i - 1][j][2]) + board[i][j];
            }

            // 오른쪽 이동 처리(가장 왼쪽은 위에서 내려온 걸 왼쪽에서 왔다고 처리)
            dp[i][0][0] = dp[i][0][1];
            for (int j = 1; j < M; j++) {
                dp[i][j][0] = Math.max(dp[i][j - 1][0], dp[i][j - 1][1]) + board[i][j];
            }

            // 왼쪽 이동 처리(가장 오른쪽은 위에서 내려온 걸 오른쪽에서 왔다고 처리)
            dp[i][M - 1][2] = dp[i][M - 1][1];
            for (int j = M - 2; j >= 0; j--) {
                dp[i][j][2] = Math.max(dp[i][j + 1][2], dp[i][j + 1][1]) + board[i][j];
            }
        }

        System.out.println(Math.max(Math.max(dp[N - 1][M - 1][0], dp[N - 1][M - 1][1]), dp[N - 1][M - 1][2]));
    }
}