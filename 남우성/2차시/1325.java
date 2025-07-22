import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static List<List<Integer>> graph;
	private static int[] results;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		
		// graph에는 다음을 저장
		// graph[1]: 1번 인덱스를 선택하면 연쇄적으로 해킹되는 index들을 저장
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph.get(B).add(A);
		}
		
		
		int maxCount = 0;
		Deque<Integer> bfs = new ArrayDeque<>();
		results = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			
			boolean[] isVisited = new boolean[N+1]; // 방문처리를 위함
			
			bfs.offer(i);
			isVisited[i] = true;

			while(!bfs.isEmpty()) {
				int now = bfs.poll();
				results[i]++;
				
				for(int item: graph.get(now)) {
					if(!isVisited[item]) {
						bfs.offer(item);
						isVisited[item] = true;
					}
				}
			}
			maxCount = Math.max(maxCount, results[i]);
		}
		
		for(int i = 1; i <= N; i++) {
			if (results[i] == maxCount) {
				sb.append(i).append(" ");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}