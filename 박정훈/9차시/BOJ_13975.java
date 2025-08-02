import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13975 {
	
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    int T = Integer.parseInt(br.readLine());
	    
	    for(int tc = 1; tc <= T; tc++) {
	    	PriorityQueue<Long> pque = new PriorityQueue<>();
	    	int K = Integer.parseInt(br.readLine());
	    	long sum = 0;
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	
	    	for(int i = 0; i < K; i++) {
	    		pque.add((long) Integer.parseInt(st.nextToken()));
	    	}
	    	
	    	while(pque.size() > 1) {
	    		long num1 = pque.poll();
	    		long num2 = pque.poll();
	    		
	    		sum += num1 + num2;
	    		pque.offer(num1 + num2);
	    	}
	    	
	    	sb.append(sum).append("\n");
	    }
	    
	    System.out.print(sb);
	}
}