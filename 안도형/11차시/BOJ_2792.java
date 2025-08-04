import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2792 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[M];
		int end = 0;

		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			end = Math.max(arr[i], end);
		}

		int start = 1;
		int ans = 0;
		int mid, child;

		while(start <= end) {
			mid = (start + end) / 2;
			child = 0;

			for(int i = 0; i < M; i++) {
				child += (arr[i] + mid - 1) / mid;
			}

			if(child <= N) {
				end = mid - 1;
				ans = mid;
			}else {
				start = mid + 1;
			}
		}
		System.out.println(ans);
	}
}
