import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1562 {
    public static int n;
    public static int[][][] dp;

    public static int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // dp[length][끝자릿수][visited]
        dp = new int[n + 1][10][1 << 10];

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        // 현재 자릿수 i
        for (int i = 2; i <= n; i++) {
            // 끝자릿수
            for (int j = 0; j < 10; j++) {
                for (int mask = 0; mask < (1 << 10); mask++) {
                    int newMask = mask | (1 << j);
                    if (j > 0)
                        dp[i][j][newMask] = (dp[i][j][newMask] + dp[i - 1][j - 1][mask]) % MOD;
                    if (j < 9)
                        dp[i][j][newMask] = (dp[i][j][newMask] + dp[i - 1][j + 1][mask]) % MOD;
                }
            }
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[n][i][(1 << 10) - 1];
            result %= MOD;
        }
        System.out.println(result);
    }
}
