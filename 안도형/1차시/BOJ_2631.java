import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] kids = new int[N];

        for (int i = 0; i < N; i++) {
            kids[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (kids[j] < kids[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLIS = 0;
        for (int num : dp) {
            maxLIS = Math.max(maxLIS, num);
        }

        System.out.println(N - maxLIS);
    }
}
