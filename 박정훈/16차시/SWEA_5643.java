import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5643 {
	static List<Integer>[] graph;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); 
			int M = Integer.parseInt(br.readLine());
			graph = new ArrayList[N + 1];
			
			//그래프 초기화
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			//간선 입력
			for(int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
			}
			
			//하위+상위 노드가 몇 개인지 세기
			int[] count = new int[N+1];
			for(int i = 1; i <= N; i++) {
				bfs(i, count);
			}
			
			//위치 확정인 사람 세기
			int answer = 0;
			for(int i = 1; i <= N; i++) {
				if(count[i] == N - 1) { // 하위, 상위 노드 합이 본인 제외 전체임
					answer++;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void bfs(int start, int[] count) {
		Deque<Integer> deque = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		deque.offer(start);
		
		while(!deque.isEmpty()) {
			int node = deque.poll();
			for(int i = 0; i < graph[node].size(); i++) {
				int next = graph[node].get(i);
				if(!visited[next]) {
					visited[next] = true;
					count[start]++; // node의 하위 노드 개수 세기
					count[next]++; // next의 상위 노드 세기
					deque.offer(next);
				}
			}
		}
	}
}