import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3687 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] dp = new long[101]; // 0: 최소, 1: 최대
        int[] arr = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };
        int[] arr2 = { 0, 0, 1, 7, 4, 2, 0, 8 };
        for (int i = 0; i < 101; i++) {
            dp[i] = 999999999999999L;
        }

        for (int i = 0; i < arr.length; i++) {
            dp[arr[i]] = Math.min(dp[arr[i]], i);
        }

        dp[6] = 6;
        for (int i = 8; i < 101; i++) {
            for (int j = 2; j < 8; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] * 10 + arr2[j]);
            }
        }

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            sb.append(dp[m]).append(" ");
            max(m);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void max(int m) {
        if (m % 2 == 1) {
            sb.append("7");
            m -= 3;
        }

        for (int i = 0; i < m / 2; i++) {
            sb.append("1");
        }
    }
}
