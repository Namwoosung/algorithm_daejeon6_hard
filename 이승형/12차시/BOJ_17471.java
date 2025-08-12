
import java.util.*;
import java.io.*;

/*
 * 백트래킹
 * 	그룹 구성
 * 		Set에 선거구 저장하여 넘김
 * 연결 확인 
 * 	bfs
 * 	계산
 */
public class BOJ_17471 {
	static int N, result, p;
	static int [] population;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		graph = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		str = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {	
			population[i] = Integer.parseInt(str.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			str = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(str.nextToken());
			for(int j=0;j<M;j++) {
				int end = Integer.parseInt(str.nextToken());
				graph.get(i).add(end);
				graph.get(end).add(i);
			}
		}
	
		//////////////////////////////////////// 입력 끝
		
		result = Integer.MAX_VALUE;
		Set<Integer> setA = new HashSet<>();
		for(int i=1;i<=N/2;i++) {
			backTracking(1,0,i,setA);
		}
		
		if(result == Integer.MAX_VALUE) result = -1;
		
		System.out.println(result);
	}
	
	
	static void backTracking(int start, int depth, int n, Set<Integer> setA) {
		if(depth==n) {
			gerrymandering(setA);
			return;
		}
		
		for(int i=start;i<=N;i++) {
			setA.add(i);
			backTracking(i+1, depth+1, n, setA);
			setA.remove(i);
		}
	}
	
	static void gerrymandering(Set<Integer> setA) {
		if(!isConnected(setA)) return;
		int populationA = p;
		Set<Integer> setB = new HashSet<Integer>();
		for(int i=1; i<=N;i++) {
			if(setA.contains(i)) continue;
			setB.add(i);
		}
		
		if(!isConnected(setB)) return;
		int populationB = p;
		
		result = Math.min(result, Math.abs(populationA-populationB));
	}
	
	
	static boolean isConnected(Set<Integer> set) {
		boolean [] visited = new boolean[N+1];
		Queue<Integer> que = new LinkedList<>();
		int start = set.iterator().next();
		visited[start] = true;
		p = population[start];
		que.offer(start);
		int cnt = 1;
		
		while(!que.isEmpty()) {
			int now = que.poll();
			for(int i=0;i<graph.get(now).size();i++) {
				int nxt = graph.get(now).get(i);
				
				if(visited[nxt] || !set.contains(nxt)) continue;
				
				cnt++;
				visited[nxt] = true;
				p+=population[nxt];
				que.offer(nxt);
			}
		}
		
		return (cnt==set.size()) ? true : false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
