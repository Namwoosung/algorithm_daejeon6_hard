import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15954 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        long[] preSum = new long[N+1];
        long[] preSqrSum = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            preSum[i+1] = preSum[i] + arr[i];
            preSqrSum[i+1] = preSqrSum[i] + (long) arr[i] * arr[i];
        }


        double answer = Double.MAX_VALUE;

        for (int i = K; i <= N; i++) {

            for (int j = 0; j <= N - i; j++) {
                long sum = preSum[j + i] - preSum[j];
                long sqrSum = preSqrSum[j + i] - preSqrSum[j];

                double v = (double) (i * sqrSum - sum * sum) / (i * i); // 분산

                double d = Math.sqrt(v); // 표준 편차

                answer = Math.min(answer, d);
            }
        }

        System.out.printf("%.11f", answer);
    }

}
