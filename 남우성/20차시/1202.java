import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); int K = Integer.parseInt(st.nextToken());
		
		int[][] item = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken());
			item[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] pack = new int[K];
		for(int i = 0; i < K; i++) {
			pack[i] = Integer.parseInt(br.readLine());
		}
		
		// 오름차순 정렬(무게 순 정렬)
		Arrays.sort(item, (a, b) -> {return a[0] - b[0];});
		Arrays.sort(pack);
		
		long result = 0;
		int idx = 0;
		PriorityQueue<Integer> candiate = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < K; i++) {
			int nowMax = pack[i];
			
			while(idx < N && item[idx][0] <= nowMax) {
				candiate.add(item[idx][1]);
				idx++;
			}
			
			if(!candiate.isEmpty()) result += candiate.poll();
		}
		
		System.out.println(result);
	}

}
