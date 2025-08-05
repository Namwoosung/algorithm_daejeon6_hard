import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int result = Integer.MAX_VALUE;
	static int N;
	static int[] nodes;
	static List<List<Integer>> graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		nodes = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nodes[i] = Integer.parseInt(st.nextToken());
		}
		
		Set<Integer> section = new HashSet<>();
		Set<Integer> remain = new HashSet<>();
		graph = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> temp = new ArrayList<>();

			int m = Integer.parseInt(st.nextToken());
			for(int j = 0; j < m; j++) {
				temp.add(Integer.parseInt(st.nextToken()) - 1);
			}
			
			graph.add(temp);
			remain.add(i);
		}
		
		// 완탐으로 풀이, 0번이 기준
		section.add(0);
		remain.remove(0);
		
		bfs(section, remain, 1);
		
		if(result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}
	
	static public void bfs(Set<Integer> section1, Set<Integer> section2, int index) {
		if(section1.size() == N) { // 종료조건: 모든 노드를 포함한 경우
			return;
		}else {
			if(checkUnion(section1) && checkUnion(section2)) { // 둘 다 구역이 형성되었다면
				int sum1 = 0;
				int sum2 = 0;
				
				for(int i = 0; i < N; i++) {
					if(section1.contains(i)) sum1 += nodes[i];
					else sum2 += nodes[i];
				}
				
				result = Math.min(result, Math.abs(sum1 - sum2));
			}
			
			if(index < N) {
				bfs(section1, section2, index+1); //현재 index를 포함하지 않고 호출
				section1.add(index);
				section2.remove(index);
				bfs(section1, section2, index+1); //현재 index를 포함하고 호출
				section1.remove(index);
				section2.add(index);
			}
		}
	}
	
	static boolean checkUnion(Set<Integer> remain) {
		boolean[] isVisited = new boolean[N];
		
		// 검사 시작 지점 설정
		int startNode = -1;
		for(int i = 0; i < N; i++) {
			if(remain.contains(i)) {
				startNode = i;
				break;
			}
		}
		if(startNode == -1) return false;
		
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(startNode);
		isVisited[startNode] = true;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int i = 0; i < graph.get(now).size(); i++) {
				int next = graph.get(now).get(i);
				if(remain.contains(next) && !isVisited[next]) { // 검사 구역이고, 방문하지 않은 경우만 추가
					queue.add(next);
					isVisited[next] = true;
				}
			}
		}
		
		//모든 remain 중에 하나라도 못 갔으면 false
		for(int i = 0; i < N; i++) {
			if(remain.contains(i) && !isVisited[i]) {
				return false;
			}
		}
		return true;
	}
}