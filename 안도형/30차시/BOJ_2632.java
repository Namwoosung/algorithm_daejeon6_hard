package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2632 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int size = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[] A = new int[M + M];
        int[] B = new int[N + N];
        int[] dpA = new int[M + M + 1];
        int[] dpB = new int[N + N + 1];
        
        int[] sizeA = new int[size+1];
        int[] sizeB = new int[size+1];
        
        for(int i = 0; i < M; i++) 
        	A[i] = A[i + M] = Integer.parseInt(br.readLine());
        
        for(int i = 1; i < M + M; i++)  
        	dpA[i] = dpA[i - 1] + A[i - 1];
            
        for(int i = 0; i < N; i++)  
        	B[i] = B[i + N] = Integer.parseInt(br.readLine());
 
        for(int i = 1; i < N + N; i++) 
        	dpB[i] = dpB[i - 1] + B[i - 1];

        for(int i = 0; i < M; i++) {
        	for(int j = i + 1; j <= i + M; j++) {
        		if (j - i == M && i > 0) continue;
        		
        		int sum = dpA[j] - dpA[i];
        		if(sum <= size)
        			sizeA[sum]++;
        	}
        }
        
        for(int i = 0; i < N; i++) {
        	for(int j = i + 1; j <= i + N; j++) {
        		if (j - i == N && i > 0) continue;
        		
        		int sum = dpB[j] - dpB[i];
        		if(sum <= size)
        			sizeB[sum]++;
        	}
        }

		//개수가 넘을 수도
        long count = 0;
        sizeA[0] = sizeB[0] = 1;
        for(int i = 0; i <= size; i++) 
        	count += (long)(sizeA[i] * sizeB[size - i]);
        
        System.out.println(count);
	}
}
