package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1517 {
	static long[] node;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        
        node = new long[N + 1];
        Integer[] arr = new Integer[N];
        int[] originArr = new int[N];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	originArr[i] = arr[i];
        }
        
        Arrays.sort(arr, (a, b) -> a - b);
        
        for(int i = 0; i < N; i++) {
        	map.put(arr[i], i + 1);
        }
        
        long result = 0;
        for(int i = N - 1; i >= 0; i--) {
        	result += sum(map.get(originArr[i]) - 1);
        	add(map.get(originArr[i]), 1);
        }
        
        System.out.println(result);
	}
	
	public static void add(int idx, int num) {
		
		while(idx < node.length) {
			node[idx] += num;
			idx += (idx & -idx);
		}
	}
	
	public static long sum(int idx) {
		long result = 0;
		while(idx > 0) {
			result += node[idx];
			idx -= (idx & -idx);
		}
		
		return result;
	}

	
}
