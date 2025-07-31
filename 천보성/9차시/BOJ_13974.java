import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13974 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t=0;t<T;t++) {
            int K = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<K;i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long ans = 0;
            while(pq.size() > 1) {
                long sum = pq.poll() + pq.poll();

                pq.add(sum);
                ans += sum;
            }

            sb.append(ans).append('\n');

        }
        System.out.println(sb);
    }

}
