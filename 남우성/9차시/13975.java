import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			int n = Integer.parseInt(br.readLine());
			
			PriorityQueue<Long> pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			
			Long result = 0L; Long num = 0L;
			while(pq.size() > 1) {
				num = pq.poll() + pq.poll();
				result += num;
				pq.add(num);
			}
			
			
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}