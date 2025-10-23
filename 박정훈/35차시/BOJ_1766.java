import java.io.*;
import java.util.*;

public class BOJ_1766 {
	static int[] count;
	static List<Integer>[] graph;
	static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        count = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        // 간선 입력
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	graph[a].add(b);
        	count[b] += 1; // b보다 먼저 와야하는 개수
        }
        
        // 바로 풀어도 되는 문제들 큐에 삽입 (count가 0인 문제들)
    	for(int i = 1; i <= N; i++) {
    		if(count[i] == 0) { // 풀 준비가 됐다면
    			pque.offer(i);
    		}
    	}
        
        while(!pque.isEmpty()) {
        	int num = pque.poll();
        	for(int i = 0; i < graph[num].size(); i++) {
        		int n = graph[num].get(i);
        		count[n]--; // 후에 풀어야 하는 문제들
        		if(count[n] == 0) pque.offer(n); // 0이 되면 큐에 삽입
        	}
        	answer.append(num).append(" ");
        }
        System.out.print(answer);
    }
}