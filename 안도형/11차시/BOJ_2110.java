import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int start = 1;
        int end = arr[N - 1];
        int ans = 0;
        //최대 거리 구한다.
        while(start <= end) {
            int mid = (start + end) / 2;
            int count = 1;
            int startIdx = 0;

            for(int i = 1; i < N; i++) {
                if(arr[i] - arr[startIdx] >= mid) {
                    startIdx = i;
                    count++;
                }
            }

            if(count >= C) {
                start = mid + 1;
                ans = mid;
            }else {
                end = mid - 1;
            }
        }
        System.out.println(ans);
    }
}
