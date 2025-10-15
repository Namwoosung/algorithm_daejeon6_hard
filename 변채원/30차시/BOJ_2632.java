import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2632 {
    public static int N;
    public static int[] count;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        count = new int[N + 1];

        String[] str = br.readLine().split(" ");
        int A = Integer.parseInt(str[0]);
        int B = Integer.parseInt(str[1]);

        int[] arrA = new int[A * 2];
        int[] arrB = new int[B * 2];

        for (int i = 0; i < A; i++) {
            int num = Integer.parseInt(br.readLine());
            arrA[i] = num;
            arrA[i + A] = num;
        }

        for (int i = 0; i < B; i++) {
            int num = Integer.parseInt(br.readLine());
            arrB[i] = num;
            arrB[i + B] = num;
        }

        if (A > B) {
            counter(arrA, A);
            System.out.println(calc(arrB, B));
        } else {
            counter(arrB, B);
            System.out.println(calc(arrA, A));
        }

    }

    public static void counter(int[] arr, int len) {
        int start = 0, end = 0;
        int size = arr.length - 1;
        int cnt = 0;
        while ((start < len) && (end < size)) {
            if (start > end)
                return;

            count[cnt]++;

            if ((end - start) >= len)
                start++;

            if ((cnt + arr[end]) <= N)
                cnt += arr[end++];
            else {
                if (arr[start] <= N)
                    cnt -= arr[start];
                start++;
            }
        }
    }

    public static int calc(int[] arr, int len) {
        int start = 0, end = 0;
        int size = arr.length - 1;

        count[0] = 1;
        int cnt = 0, result = 0;
        while ((start < len) && (end < size)) {
            if (start > end)
                return result;

            if (!visited[N - cnt]) {
                result += count[N - cnt];
                visited[N - cnt] = true;
            }

            if ((end - start) >= len) {
                if (arr[start] <= N)
                    cnt -= arr[start];
                start++;
                continue;
            }

            if ((cnt + arr[end]) <= N)
                cnt += arr[end++];
            else {
                if (arr[start] <= N)
                    cnt -= arr[start];
                start++;
            }
        }

        return result;
    }
}