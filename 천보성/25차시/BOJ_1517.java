import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1517 {
	
	static class Number implements Comparable<Number>{
		int index;
		int value;
		
		Number(int index, int value){
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Number o) {
			if(o.value == this.value) {
				return o.index - this.index;
			}
			return o.value - this.value;
		}
	}
	
	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<Number> pq = new PriorityQueue<>();
		
		for(int i=1;i<=N;i++) {
			pq.add(new Number(i, Integer.parseInt(st.nextToken())));
		}
		
		long ans = 0;
		tree = new long[N*4];
		
		while(!pq.isEmpty()) {
			Number num = pq.poll();
			
			long scale = sum(1, 1, num.index-1, 1, N);
			ans += pq.size() - (num.index + scale) + 1;
			update(1, 1, N, num.index, -1);
		}
		
		System.out.println(ans);
		
	}
	
	
	public static long sum(int node, int targetL, int targetR, int start, int end){
		if(end < targetL || start > targetR) {
			return 0;
		}
		if(targetL <= start && targetR >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		
		long left = sum(node * 2, targetL, targetR, start, mid);
		long right = sum(node * 2 + 1, targetL, targetR, mid + 1, end);
		
		return left + right;
	}
	
	public static void update(int node, int start, int end, int index, int value) {
		if(index < start || end < index) {
			return;
		}
		tree[node] += value;
		
		if(start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, index, value);
			update(node * 2 + 1, mid + 1, end, index, value);
		}
	}
}
