import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dist;
	static int N;
	static int X;
	static List<List<Node>> graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken()); X = Integer.parseInt(st.nextToken()) - 1;
		
		graph = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; int b = Integer.parseInt(st.nextToken()) - 1; int w = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, w));
		}
		
		dist = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// N에 대해 모두 다익스트라 적용
		for(int i = 0; i < N; i++) {
			dijkstra(i);
		}
		
		// 가장 긴 경우 탐색
		int result = 0;
		for(int i = 0; i < N; i++) {
			result = Math.max(result, dist[i][X] + dist[X][i]);
		}
		
		
		System.out.println(result);
	}
	
	
	static class Node{
		int pos;
		int weight;

		public Node(int pos, int weight) {
			super();
			this.pos = pos;
			this.weight = weight;
		}
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> node1.weight - node2.weight);
		boolean[] isVisited = new boolean[N];
		
		// 시작 노드 입력
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(isVisited[now.pos]) continue; //이미 경로 설정이 완료된 경우 pass
			
			// 방문처리 및 거리 저장
			isVisited[now.pos] = true;
			dist[start][now.pos] = now.weight;
			
			if(start != X && now.pos == X) return; // 만약 반환점이 아닌 경우, 반환점까지의 거리가 확정된 경우 바로 종료
			
			for(int i = 0; i < graph.get(now.pos).size(); i++) { // 현재 노드 기준 거리를 update
				Node next = graph.get(now.pos).get(i);
				if(isVisited[next.pos]) continue; // 다음 노드가 이미 확정된 노드면 pass
				
				if(dist[start][next.pos] > dist[start][now.pos] + next.weight) { // 기존값보다 update된 가중치가 더 최적일 경우 pq에 입력
 					pq.add(new Node(next.pos, dist[start][now.pos] + next.weight));
				}
			}
		}
	}
}
