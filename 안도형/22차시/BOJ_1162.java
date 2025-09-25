package study_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1162 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        ArrayList<int[]>[] node = new ArrayList[N+1]; 
        for(int i = 1; i <= N; i++) {
        	node[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	node[a].add(new int[] {b, c});
        	node[b].add(new int[] {a, c});
        }
        
        long[][] dist = new long[N+1][K+1];
        for(int i = 1; i <= N; i++)
        	Arrays.fill(dist[i], Long.MAX_VALUE);
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> { return Long.compare(a[1], b[1]);});
        pq.add(new long[] {1, 0, 0});
        Arrays.fill(dist[1], 0);
        
        while(!pq.isEmpty()) {
        	long[] curr = pq.poll();
        	
        	if(curr[1] > dist[(int)curr[0]][(int)curr[2]]) {
        		continue;
        	}
        	
        	for(int[] next : node[(int)curr[0]]) {
        		long cost = next[1] + curr[1];
        		if(cost < dist[next[0]][(int)curr[2]]) {
        			dist[next[0]][(int)curr[2]] = cost;
        			pq.add(new long[] {next[0], cost, curr[2]});
        		}
        		
        		if(curr[2] + 1 <= K && curr[1] < dist[next[0]][(int)curr[2] + 1]) {
        			dist[next[0]][(int)curr[2] + 1] = curr[1];
        			pq.add(new long[] {next[0], curr[1], curr[2] + 1});
        		}
        	}
        }
        
        long maxTotal = Long.MAX_VALUE;
        for(int i = 1; i <= K; i++) {
        	maxTotal = Math.min(maxTotal, dist[N][i]);
        }
        System.out.println(maxTotal);
	}
}
