import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Planet{
		int id;
		int x;
		int y;
		int z;
		
		public Planet(int id, int x, int y, int z) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Dist implements Comparable<Dist>{
		int start;
		int end;
		int dist;
		
		public Dist(int start, int end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Dist other) {
			return this.dist - other.dist;
		}
	}
	
	static int[] root;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		Planet[] arr = new Planet[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Planet(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 가능한 모든 경우를 pq에 저장. 3 * N^2에 대해 모든 가능한 경우를 다 저장하면 시간초과이므로 정렬해서 3(N-1)개만 저장
		PriorityQueue<Dist> pq = new PriorityQueue<>();
		
		// x기준 정렬 후 N-1 개의 가능한 연결 정보를 pq에 저장
		Arrays.sort(arr, (a, b) -> {return a.x - b.x;} );
		for(int i = 0; i < N - 1; i++) {
			Planet left = arr[i];
			Planet right = arr[i+1];
			
			pq.add(new Dist(left.id, right.id, right.x - left.x));
		}
		
		// y기준
		Arrays.sort(arr, (a, b) -> {return a.y - b.y;} );
		for(int i = 0; i < N - 1; i++) {
			Planet left = arr[i];
			Planet right = arr[i+1];
			
			pq.add(new Dist(left.id, right.id, right.y - left.y));
		}
		
		// z기준
		Arrays.sort(arr, (a, b) -> {return a.z - b.z;} );
		for(int i = 0; i < N - 1; i++) {
			Planet left = arr[i];
			Planet right = arr[i+1];
			
			pq.add(new Dist(left.id, right.id, right.z - left.z));
		}
		
		// 연결관계 확인을 위해 유니온 파인드를 사용
		root = new int[N+1];
		for(int i = 1; i <= N; i++) {
			root[i] = i;
		}
		
		long result = 0;
		while(!pq.isEmpty()) {
			Dist now = pq.poll();
			
			if(union(now.start, now.end)) { // union으 실제로 수행했다면 해당 통로를 이은 걸로 판단
				result += now.dist;
			}
		}
		
		System.out.println(result);
	}
	
	static boolean union(int n1, int n2) {
		int r1 = find(n1); int r2 = find(n2);
		if(r1 == r2) return false;
		
		root[r1] = r2;
		return true;
	}
	
	static int find(int n) {
		if(root[n] == n) return n;
		
		return root[n] = find(root[n]);
	}
}
