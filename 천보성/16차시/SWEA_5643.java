import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5643 {
	static ArrayList<ArrayList<Integer>> graph;
	static int N;
	static ArrayList<ArrayList<Integer>> bigList;
	static ArrayList<ArrayList<Integer>> smallList;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int testcase=1;testcase<=T;testcase++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			graph  = new ArrayList<>();
			bigList = new ArrayList<>();
			smallList = new ArrayList<>();
			
			for(int i=0;i<=N;i++) {
				graph.add(new ArrayList<>());
				bigList.add(new ArrayList<>());
				smallList.add(new ArrayList<>());
			}
			
			for(int i=0;i<M;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph.get(a).add(b);
			}
			
			for(int i=1;i<=N;i++) {
				bfs(i);
			}
			int cnt = 0;
			for(int i=1;i<=N;i++) {
				if(N-1 == bigList.get(i).size() + smallList.get(i).size()) {
					cnt++;
				}
			}
			sb.append('#').append(testcase).append(' ');
			sb.append(cnt);
			sb.append('\n');
			
		}
		System.out.println(sb);
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int next : graph.get(now)) {
				if(visited[next]) continue;
				visited[next] = true;
				bigList.get(start).add(next);
				smallList.get(next).add(start);
				queue.add(next);
			}
		}

	}
}
