import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 8980{

static class Route implements Comparable<Route> {
    int start;
    int end;
    int cnt;

    public Route(int start, int end, int cnt) {
        this.start = start;
        this.end = end;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Route o) {
        return this.end - o.end;
    }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        PriorityQueue<Route> candiate = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            candiate.add(new Route(a, b, c));
        }

        int result = 0;
        int[] cnt = new int[N];

        while (!candiate.isEmpty()) {
            Route now = candiate.poll();

            int nowMaxCnt = 0;
            for (int i = now.start; i < now.end; i++) {
                nowMaxCnt = Math.max(nowMaxCnt, cnt[i]);
            }

            int diff = C - nowMaxCnt;
            if (diff <= 0)
                continue;

            int nowCnt = Math.min(diff, now.cnt);

            result += nowCnt;
            for (int i = now.start; i < now.end; i++) {
                cnt[i] += nowCnt;
            }
        }

        System.out.println(result);
    }
}