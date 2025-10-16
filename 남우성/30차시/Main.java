import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int target = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arrA = new int[m];
        int allA = 0;
        int[] arrB = new int[n];
        int allB = 0;
        for (int i = 0; i < m; i++) {
            arrA[i] = Integer.parseInt(br.readLine());
            allA += arrA[i];
        }
        for (int i = 0; i < n; i++) {
            arrB[i] = Integer.parseInt(br.readLine());
            allB += arrB[i];
        }

        int[] sumA = new int[target + 1];
        sumA[0] = 1;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            int now = i;
            do {
                sum += arrA[now];
                if (sum > target)
                    break;
                sumA[sum]++;
                now = (now + 1) % m;
            } while (now != i);
        }

        // 전체 도는 거 여러 번 더해진 거 보정
        if (allA <= target)
            sumA[allA] -= (m - 1);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int now = i;

            do {
                sum += arrB[now];
                if (sum > target)
                    break;
                answer += sumA[target - sum];
                now = (now + 1) % n;
            } while (now != i);
        }

        // 전체 도는 거 여러 번 더해진 거 보정
        if (allB <= target)
            answer -= (sumA[target - allB] * (n - 1));

        // B 피자를 포함한 경우와 A만 있는 경우의 합
        System.out.println(answer + sumA[target]);
    }
}