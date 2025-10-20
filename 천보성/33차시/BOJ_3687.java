import java.io.*;
import java.util.*;

class BOJ_3687 {
    static long[] dp = new long[101];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Arrays.fill(dp, 999999999999999L);
        makeMin();
        for (int i=0;i<T;i++){
            int n = Integer.parseInt(br.readLine());

            sb.append(dp[n] +" ");
            makeMax(n);
            sb.append('\n');
        }

        System.out.println(sb);
    }
    public static void makeMin(){
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;
        int[] arr = {0,0,1,7,4,2,0,8};

        for (int i=9;i<dp.length;i++){

            for (int j=2;j<8;j++){
                dp[i] = Math.min(dp[i], dp[i-j] * 10 + arr[j]);
            }
        }
    }

    public static void makeMax(int n){
        if (n % 2 == 1){
            sb.append("7");
            n-=3;
        }
        int m = n/2;

        for (int i=0;i<m;i++){
            sb.append("1");
        }
    }
}