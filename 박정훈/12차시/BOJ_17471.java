import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17471 {
    static int N, sum;
    static int[] population;
    static int[] union;
    static List<Integer>[] graph;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sum = 0;
        
        population = new int[N + 1];
        graph = new ArrayList[N + 1];
        union = new int[N + 1];
        
        //인구수 입력, 그래프 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	int p = Integer.parseInt(st.nextToken());
            population[i] = p;
            sum += p; //총 인구수
            graph[i] = new ArrayList<>();
        }
        
        // 그래프 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                graph[i].add(neighbor);
            }
        }
        
        // 1번 지역 1번 집합
        union[1] = 1;
        dfs(1);
        
        // 1번 지역 2번 집합
        union[1] = 2;
        dfs(1);

        //max_value면 두 집합으로 나뉘는 경우가 없음
        System.out.print(min == Integer.MAX_VALUE? -1 : min);
    }
    
    public static void dfs(int node) {
    	if(node == N) { // 모든 지역 할당 완료
    		boolean[] visited = new boolean[N+1];
    		
    		// 집합 개수 세기
    		int unionCount = 0;
    		for(int i = 1; i <= N; i++) {
    			if(!visited[i]) {
    				unionCount++; // 집합이 몇 개로 나뉘는 지 카운트
    				// 같은 집합은 모두 방문 처리
    				bfs(i, union, visited);
    			}
    		}
    		
    		// 집합이 2개면
    		if(unionCount == 2) {
    			int uSum = 0;
    			for(int i = 1; i <= N; i++) {
    				if(union[i] == 1) { // 1번 집합
    					uSum += population[i];
    				}
    			}
    			min = Math.min(min, Math.abs(uSum - (sum - uSum)));
    		}
    		return;
    	}
    	
    	// 다음 지역 1번 집합으로 할당
    	union[node + 1] = 1;
    	dfs(node + 1);
    	
    	// 다음 지역 2번 집합으로 할당
    	union[node + 1] = 2;
    	dfs(node + 1);
    }
    
    public static void bfs(int node, int[] union, boolean[] visited) {
    	Deque<Integer> deque = new ArrayDeque<>();
    	deque.offer(node);
    	visited[node] = true;
    	
    	while(!deque.isEmpty()) {
    		int n = deque.poll();
    		
    		for(int i = 0; i < graph[n].size(); i++) {
    			int next = graph[n].get(i);
    			// 같은 집합이고 방문하지 않았다면
    			if(union[next] == union[node] && !visited[next]) {
    				deque.offer(next);
    				visited[next] = true;
    			}
    		}
    	}
    }
}