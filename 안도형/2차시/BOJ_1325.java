import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        result = new int[N + 1];

        for (int i = 0; i <= N; i++) arr[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            arr[A].add(B);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            queue.offer(i);

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (int next : arr[curr]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        result[next]++;
                        queue.offer(next);
                    }
                }
            }

            max = result[i] > max ? result[i] : max;
        }



        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (result[i] == max) sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
