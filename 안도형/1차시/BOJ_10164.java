package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_10164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                arr[i][j] = i * M + j + 1;
            }
        }

        if(K == 0) {
            System.out.println(combination(N + M - 2, N - 1));
        }
        else{
            int r = (K - 1) / M;
            int c = (K - 1) % M;

            System.out.println(combination(r + c, r) * combination(N + M - r - c - 2, N - 1 - r));
        }

    }

    public static long combination(int n, int k){
        if(k == 0 || k == n) return 1;

        long comb = 1;
        for(int i = 1; i <= k; i++){
            comb *= n - i + 1;
            comb /= i;
        }

        return comb;
    }
}
