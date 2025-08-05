
import java.io.*;
import java.util.*;

public class BOJ_15903 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// inputs
		StringTokenizer str = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(str.nextToken());
		int M = Integer.parseInt(str.nextToken());
		str = new StringTokenizer(br.readLine());

		// solve
		PriorityQueue<Long> pq = new PriorityQueue<>((o1, o2) -> (o1.compareTo(o2)));
		for(int i=0;i<N;i++) {
			pq.add(Long.parseLong(str.nextToken()));
		}
		
		for(int i=0;i<M;i++) {
			long o1 = pq.poll();
			long o2 = pq.poll();
			pq.add(o1+o2);
			pq.add(o1+o2);	
		}

		Iterator<Long> it = pq.iterator();
		long result = 0;
		while(it.hasNext()) {
			result+= it.next();
		}
		
		System.out.println(result);
	}
}
