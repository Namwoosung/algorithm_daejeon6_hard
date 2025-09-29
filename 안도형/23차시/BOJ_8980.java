package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8980 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        int M = Integer.parseInt(br.readLine());
        int[][] arr = new int[M][3];
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	arr[i][0] = a;
        	arr[i][1] = b;
        	arr[i][2] = c;
        }
        
        Arrays.sort(arr, (a, b) -> {
        	if(a[1] != b[1]) return a[1] - b[1];
        	return a[0] - b[0];
        });
        
        int total = 0;
        int[] city = new int[N+1];
        
        for (int[] box : arr) {
        	
            int max = 0;
            for (int i = box[0]; i < box[1]; i++) 
            	max = Math.max(max, city[i]);
            

            int deliver = Math.min(box[2], C - max);
            total += deliver;

            for (int i = box[0]; i < box[1]; i++) {
                city[i] += deliver;
            }
        }

        
        System.out.println(total);
	}
}
