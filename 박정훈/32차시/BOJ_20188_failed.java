import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20188_failed {
	static List<Integer>[] graph;
	static int[] oneToNDist;
	static int N, sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		// 1 -> n 가는 간선 수
		oneToNDist = new int[N + 1];
		graph = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			oneToNDist[i] = Integer.MAX_VALUE;
		}
		
		// 간선 입력
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		// 1 -> n 가는 최소 경로
		findDist();
		
		sum = 0;
		for(int i = 2; i < N; i++) {
			sum += oneToNDist[i];
			for(int j = i + 1; j <= N; j++) {
				sum += lca(i, j);
			}
		}
		
		// 1 -> N으로 가는 경로 합산
		sum += oneToNDist[N];
		
		System.out.print(sum);
	}
	
	public static int lca(int a, int b) {
		int count = 0;
		// 깊이 맞춰주기
		if(oneToNDist[a] != oneToNDist[b]) {
			// 더 깊이가 깊은 쪽을 a로
			if(oneToNDist[a] < oneToNDist[b]) {
				int temp = a;
				a = b;
				b = temp;
			}
			// 깊이가 동일할 때까지
			while(oneToNDist[a] != oneToNDist[b]) {
				for(int node : graph[a]) {
					if(oneToNDist[a] > oneToNDist[node]) {
						count++;
						a = node;
						break;
					}
				}
			}
		}
		
		// 공통 조상 찾을 때까지
		while(a != b) {
			// a 부모 노드
			for(int node : graph[a]) {
				if(oneToNDist[a] > oneToNDist[node]) {
					a = node;
					break;
				}
			}
			
			// b 부모 노드
			for(int node : graph[b]) {
				if(oneToNDist[b] > oneToNDist[node]) {
					b = node;
					break;
				}
			}
			
			count += 2;
		}
		
		// 1 -> 공통 조상까지의 거리
		count += oneToNDist[a];
		
		return count;
	}

	public static void findDist() {
		Deque<int[]> deque = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		oneToNDist[1] = 0;
		deque.offer(new int[] {1, 0});
		
		while(!deque.isEmpty()) {
			int[] info = deque.poll();
			int curNode = info[0];
			int curDist = info[1];

			for(int nextNode : graph[curNode]) {
				if(oneToNDist[nextNode] > curDist + 1) {
					oneToNDist[nextNode] = curDist + 1;
					deque.offer(new int[] {nextNode, curDist + 1});
				}
			}
		}
	}
}