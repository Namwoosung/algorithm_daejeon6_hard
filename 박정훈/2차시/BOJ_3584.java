import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_3584 {
	static boolean[] visited;
	static List<Integer>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트 케이스
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			visited = new boolean[N + 1];
			graph = new ArrayList[N + 1];
			
			// 그래프 초기화
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			// 간선 입력
			for(int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()); // 부모
				int B = Integer.parseInt(st.nextToken()); // 자식
				
				// B -> A로 올라가기만 하므로 단방향
				graph[B].add(A);
			}
			
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			int root = find(node1); // 첫 탐색은 루트노드까지 올라감
			sb.append(find(node2)).append("\n");
		}
		
		//출력
		System.out.print(sb);
	}

	public static int find(int node) {
		int p = 0;
		if(!graph[node].isEmpty()) { // 현재 노드가 루트 노드가 아니라면
			if(!visited[node]) { // 방문하지 않은 노드라면
				visited[node] = true;
				p = find(graph[node].get(0));
			} else { // 공통 부모 찾음
				p = node;
			}
		} else { // 루트 노드면 방문처리하고 루트 반환
			visited[node] = true;
			p = node;
		}
		
		return p;
	}
}