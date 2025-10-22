package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2150 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] node = new ArrayList[V+1];
        ArrayList<Integer>[] reverseNode = new ArrayList[V+1];
        for(int i = 1; i <= V; i++) {
        	node[i] = new ArrayList<>();
        	reverseNode[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	node[a].add(b);
        	reverseNode[a].add(b);
        }
        
        
        int[] visited = new int[V+1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        
        while(!dq.isEmpty()) {
        	int pos = dq.poll();
        	
        	for(int next : node[pos]) {
        		if(visited[next] <= node[next].size()) {
        			visited[next]++;
        			dq.add(next);
        		}else {
        			//싸이클이 돈다.
        		}
        	}
        }
	}
}
