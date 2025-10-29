import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ_1135 {
	static int[] time;
	static List<Integer>[] tree;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N]; // 현재 노드의 자식 노드가 모두 전파 되는 최소 시간
		tree = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}

		// 트리 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num == -1) continue;
			tree[num].add(i);
		}
		
		call(0);
		
		System.out.print(time[0]);
	}

	// 전파 시간 구하기
	public static int call(int node) {
		// 말단 직원은 전파 시간이 0
		if (tree[node].size() < 1) return 0;
		
		// 각 노드 별 전파에 걸리는 시간
		Integer[] times = new Integer[tree[node].size()];
		for (int i = 0; i < tree[node].size(); i++) {
			times[i] = call(tree[node].get(i));
		}
	
		// 내림차순
		Arrays.sort(times, Collections.reverseOrder());
		
		for (int i = 0; i < tree[node].size(); i++) {
			time[node] = Math.max(time[node], times[i] + i + 1);
		}
		
		return time[node];
	}
}