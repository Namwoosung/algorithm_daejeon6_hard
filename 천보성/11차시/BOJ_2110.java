import java.io.*;
import java.util.*;

public class BOJ_2110 {

    static int[] arr;
    static int N;
    static int C;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int start = 1;
        int end = arr[N-1] - arr[0];

        while(start <= end) {
            int mid = (start + end)/2;

            if(check(mid) < C) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1);
    }

    public static int check(int d) {
        int temp = 0;
        int cnt = 1;

        for(int i=1;i<N;i++) {
            if(arr[i] - arr[temp] >= d) {
                temp = i;
                cnt++;
            }
        }

        return cnt;
    }

}