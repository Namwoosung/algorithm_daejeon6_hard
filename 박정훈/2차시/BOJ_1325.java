import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1325 {
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] count = new int[N];
		
		//그래프 초기화
		List<Integer>[] graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		//그래프 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			//n1이 n2를 신뢰한다
			graph[n2 - 1].add(n1 - 1);
		}
		
		//탐색
		for(int i = 0; i < N; i++) {
			visited = new boolean[N]; //방문 초기화
			visited[i] = true;
			count[i]++;
			dfs(i, i, count, graph, N);
		}
		
		//가장 많은 해킹 찾기
		int max = 1;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, count[i]);
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(count[i] == max) {
				sb.append(i+1).append(" ");
			}
		}
		
		System.out.print(sb);
	}
	
	public static void dfs(int start, int current, int[] count, List<Integer>[] graph, int N) {
		for(int i = 0; i < graph[current].size(); i++) {
			if(count[start] == N) return; //모든 컴퓨터 해킹하면 탐색 필요 X
			
			int nc = graph[current].get(i);
			if(!visited[nc]) { //방문하지 않았다면
				visited[nc] = true;
				count[start]++;
				dfs(start, nc, count, graph, N);
			}
		}
	}
}