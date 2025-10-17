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
        int total = 0;

        for (int i = 0; i < len; i++) {
            int tmp = 0;
            total += arr[i];
            for (int j = i; j < i + len; j++) {
                tmp += arr[j];

                if(tmp <= N)
                    count[tmp]++;
                else 
                    break;
            }
        }

        if(total <= N)
            count[total] -= (len - 1);
    }

    public static int calc(int[] arr, int len) {
        int result = count[N];
        count[0] = 1;
        int total = 0;
        for (int i = 0; i < len; i++) {
            int cnt = 0;
            total += arr[i];
            for (int j = i; j < i + len - 1; j++) {
                cnt += arr[j];

                if(cnt <= N)
                    result += count[N - cnt];
            }
        }

        if(total <= N)
            result += count[N - total];
            
        return result;
    }
}