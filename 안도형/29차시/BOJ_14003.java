package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_14003 {
	public static int[] arr;
	public static int[][] lis;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        lis = new int[N][2];
        int lisIdx = 0;
        lis[0][0] = arr[0];
        lis[0][1] = 0;
        
        for(int i = 1; i < N; i++) {
        	if(lis[lisIdx][0] < arr[i]) {
        		lis[++lisIdx][0] = arr[i];
        		lis[i][1] = lisIdx;
        	}else{
        		int idx = binary(0, lisIdx, arr[i]);
        		lis[idx][0] = arr[i];
        		lis[i][1] = idx;
        	}
        }
        
        System.out.println(1+lisIdx);
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for(int i = N - 1; i >= 0; i--) {
        	if(lis[i][1] == lisIdx) {
        		lisIdx--;
        		dq.add(arr[i]);
        	}
        	
        	if(lisIdx == -1) break;
        }
        
        while(!dq.isEmpty()) {
        	System.out.print(dq.pollLast() + " ");
        }
	}
	
	public static int binary(int start, int end, int value) {
		while(start < end) {
			int mid = (start + end) / 2;
			
			if(lis[mid][0] < value) {
				start = mid + 1;
			}else {
				end = mid;
			}
		}
		
		return end;
	}
}
