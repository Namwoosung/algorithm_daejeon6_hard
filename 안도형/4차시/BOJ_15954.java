package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15954 {
    static int N,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        double min = Integer.MAX_VALUE;

        for(int i = K; i <= N; i++) {
            for(int j = 0; j <= N - i; j++)
                min = Math.min(min, std(arr, j, i));
        }

        System.out.printf("%.8f ", min);
    }

    public static double std(int[] arr, int start, int len){
        int k = len+start;
        int sum = 0;

        for(int i = start; i < k; i++){
            sum += arr[i];
        }

        double avg = (double)sum / len;
        double dis = 0;

        for(int i = start; i < k; i++){
            double temp = arr[i] - avg;
            dis += temp * temp;
        }
        dis /= len;

        return Math.sqrt(dis);
    }
}
