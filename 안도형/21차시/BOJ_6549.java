package study_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6549 {
	public static class Histo{
		int height, count;
		long total;
		
		public Histo(int height, long total) {
			this.height = height;
			this.total = total;
		}
		
		public Histo() {
		}
	}
	
	
	public static int[] arr;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        
        while(true) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	if(N == 0) break;
        	
        	arr = new int[N];
        	for(int i = 0; i < N; i++)
        		arr[i] = Integer.parseInt(st.nextToken());
        	
        	long total = divide(0, N-1);
        	System.out.println(total);
        }
	}
	
	public static long divide(int start, int end) {
		
		if(start == end) 
			return arr[start];
		
		int mid = (start + end) / 2;
		long div1 = divide(start, mid);
		long div2 = divide(mid + 1, end);
		long div3 = murge(start, mid, end);
		
		return Math.max(Math.max(div1, div2), div3);
	}
	
	public static long murge(int start, int mid, int end) {
		int left = mid;
		int right = mid + 1;
		
		int height = Math.min(arr[left], arr[right]);
		long total = (long)height + height;
		
		while (start < left || right < end) {
			if(right < end && (left == start || arr[left - 1] < arr[right + 1])) {
				height = Math.min(height, arr[++right]);
			}else {
				height = Math.min(height, arr[--left]);
			}
			
			total = Math.max(total, (long) height * (right - left + 1));
		}
		
		return total;
	}
}
