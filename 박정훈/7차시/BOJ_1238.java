import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238 {
	static List<int[]>[] graph;
	static int[] go;
	static int[] back;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		go = new int[N + 1];
		back = new int[N + 1];
		int[] answer = new int[N + 1];
		
		//초기화
		graph = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		//입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			//그래프는 단방향
			graph[start].add(new int[] {end, value});
		}
		
		//탐색
		int max = 0;
		for(int i = 1; i <= N; i++) {
			Arrays.fill(go, Integer.MAX_VALUE);
			Arrays.fill(back, Integer.MAX_VALUE);
			go[i] = 0; //시작점은 0
			answer[i] = dijkstra(i, X, go, false);
			max = Math.max(max, answer[i]);
		}
		
		System.out.print(max);
	}
	
	public static int dijkstra(int start, int end, int[] time, boolean isBack) {
		PriorityQueue<int[]> pque = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pque.offer(new int[] {start, time[start]});
		
		while(!pque.isEmpty()) {
			int[] info = pque.poll();
			int s = info[0]; // 시작점
		
			if(s == end) { //도착
				if(isBack) {
					return time[end];
				} else {
					back[s] = go[s]; //도착점까지 온 시간 저장
					return dijkstra(s, start, back, true); //되돌아가기
				}
			}
			
			//다음 마을 이동하기
			for(int i = 0; i < graph[s].size(); i++) {
				int[] temp = graph[s].get(i);
				if(time[s] + temp[1] < time[temp[0]]) { //다음 이동 시간이 덜 걸린다면
					time[temp[0]] = time[s] + temp[1];
					pque.offer(new int[] {temp[0], time[temp[0]]});
				}
			}
		}
		
		return -1;
	}
}