import java.io.*;
import java.util.*;

public class BOJ_1238 {

	static class Edge{
		int end;
		int time;
		
		Edge(int end, int time){
			this.end = end;
			this.time = time;
		}
	}
	
	static class Node implements Comparable<Node>{
		int num;
		int t;
		
		Node(int num, int t){
			this.num = num;
			this.t = t;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.t - n.t;
		}
	}
	
	static final int INF = 100000000;
	static int N;
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 ArrayList<ArrayList<Edge>> graph1 = new ArrayList<>();
		 ArrayList<ArrayList<Edge>> graph2 = new ArrayList<>();
		 
		 N = Integer.parseInt(st.nextToken());
		 int M = Integer.parseInt(st.nextToken());
		 int X = Integer.parseInt(st.nextToken());
		 
		 for(int i=0;i<=N;i++) {
			 graph1.add(new ArrayList<>());
			 graph2.add(new ArrayList<>());
		 }
		 
		 for(int i=0;i<M;i++) {
			 st = new StringTokenizer(br.readLine());
			 int start = Integer.parseInt(st.nextToken());
			 int end = Integer.parseInt(st.nextToken());
			 int time = Integer.parseInt(st.nextToken());
			 graph1.get(end).add(new Edge(start, time));
			 graph2.get(start).add(new Edge(end, time));
		 }

		 
		 int[] dist1 = new int[N+1];
		 int[] dist2 = new int[N+1];
		
		 dijk(X, graph1, dist1);
		 dijk(X, graph2, dist2);
		 
		 int max = 0;
		 for(int i=1;i<=N;i++) {
			 max = Math.max(max, dist1[i] + dist2[i]);
		 }
		 
		 System.out.println(max);
	}
	
	public static void dijk(int start, ArrayList<ArrayList<Edge>> graph, int[] dist) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visited[now.num]) continue;
			
			visited[now.num] = true;
			
			for(Edge e : graph.get(now.num)) {
				int time = now.t + e.time;
				
				if(dist[e.end] > time) {
					dist[e.end] = time;
					pq.add(new Node(e.end, time));
				}
			}
		}
		
	}
}
