package study_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1202 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // M = 무게, V = 가격
        int[][] arr = new int[N][2];
        int[] back = new int[K];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr, (a, b) ->  a[0] - b[0] );
        
        for(int i = 0; i < K; i++) 
        	back[i] = Integer.parseInt(br.readLine());
        Arrays.sort(back);
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        long total = 0;
        int j = 0;
        for(int i = 0; i < K; i++) {
        	while (j < N && arr[j][0] <= back[i]) {
        	    pq.add(arr[j][1]);
        	    j++;
	        }
	        if(!pq.isEmpty())
	        	total += pq.poll();

        }
        
        System.out.println(total);
	}
}
