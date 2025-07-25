import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] honey = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefix = new int[N];
        prefix[0] = honey[0];
        for (int i = 1; i < N; i++) {
            prefix[i] = prefix[i - 1] + honey[i];
        }

        int max = 0;

        for (int i = 1; i < N - 1; i++) {
            int bee1 = prefix[N - 1] - honey[0] - honey[i];
            int bee2 = prefix[N - 1] - prefix[i];
            max = Math.max(max, bee1 + bee2);
        }

        for (int i = 1; i < N - 1; i++) {
            int bee1 = prefix[N - 2] - honey[i];
            int bee2 = prefix[i - 1];
            max = Math.max(max, bee1 + bee2);
        }

        for (int i = 1; i < N - 1; i++) {
            int bee1 = prefix[i] - honey[0];
            int bee2 = prefix[N - 1] - prefix[i - 1] - honey[N - 1];
            max = Math.max(max, bee1 + bee2);
        }

        System.out.println(max);
    }
}
