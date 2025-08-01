import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		for(int i = 0; i < m; i++) {
			Long sum = pq.poll() + pq.poll();
			pq.add(sum); pq.add(sum);
		}
		
		Long result = 0L;
		for(Long num : pq) {
			result += num;
		}
		System.out.println(result);
	}
}