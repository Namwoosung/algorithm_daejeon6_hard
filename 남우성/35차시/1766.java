import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	PriorityQueue<Integer> candiate = new PriorityQueue<>();
    	Set<Integer>[] graph = new HashSet[N + 1];
    	int[] inOrder = new int[N+1];
    	for(int i = 1; i <= N; i++) {
    		graph[i] = new HashSet<>();
    	}
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		graph[a].add(b);
    		inOrder[b]++;
    	}
    	
    	for(int i = 1; i <= N; i++) {
    		if(inOrder[i] == 0) candiate.add(i);
    	}
    	
    	while(!candiate.isEmpty()) {
    		int now = candiate.poll();
    		sb.append(now).append(" ");
    		
    		for(int next : graph[now]) {
    			inOrder[next]--;
    			if(inOrder[next] == 0) candiate.add(next);
    		}
    	}
    	System.out.print(sb);
    }
}