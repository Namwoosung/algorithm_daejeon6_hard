
import java.io.*;
import java.util.*;

public class BOJ_1238 {
	
	static int N,M,X;
	static ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
	static boolean [] visited;
	static int [][] dist;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());
		X = Integer.parseInt(str.nextToken());
		
		// 초기화
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<>());
		}
		dist = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		for(int i=0;i<M;i++) {
			str = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(str.nextToken());
			int node = Integer.parseInt(str.nextToken());
			int edge = Integer.parseInt(str.nextToken());
			graph.get(start).add(new Pair(node, edge));
		}
		
		// i번 학생의 집에서 출발하는 최단거리
		int result = Integer.MIN_VALUE;
		for(int i=1;i<=N;i++) {
			visited = new boolean[N+1];
			dijkstra(i);	
		}
		
		// 최대값 = i번 집에서 목적지 X로의 최단 거리 + 목적지 X에서 i번 집으로의 최단거리
		for(int i=1;i<=N;i++) {	
			result = Math.max(result,dist[i][X]+dist[X][i]);
		}
		
		System.out.println(result);
		
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		dist[start][start] = 0;
		pq.add(new Pair(start, 0));
		
		while(!pq.isEmpty()) {
			Pair now = pq.poll();
			
			if(visited[now.node]) continue;			
			
			visited[now.node] = true;
			for(int i=0;i<graph.get(now.node).size(); i++) {
				int nextNode = graph.get(now.node).get(i).node;
				int nextEdge = graph.get(now.node).get(i).edge;
				
				if(dist[start][nextNode] > nextEdge + dist[start][now.node]) {
					dist[start][nextNode] = nextEdge + dist[start][now.node];
					pq.add(new Pair(nextNode, dist[start][nextNode]));
				}
			}
		}
	}

	
	static class Pair implements Comparable<Pair>{
		int node, edge;
		public Pair(int node, int edge) {
			this.node = node;
			this.edge = edge;
		}
		
		@Override
		public int compareTo(Pair p1) {
			return this.edge - p1.edge;
		}
	}
}
