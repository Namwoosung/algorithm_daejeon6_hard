package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1135 {
	public static int N;
	public static ArrayList<Integer>[] arr;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = new ArrayList<>();
        }
        
        st.nextToken();
        for(int i = 1; i < N; i++) {
        	int a = Integer.parseInt(st.nextToken());
        	arr[a].add(i);
        }
        
        int ans = dfs(0, 0);
        System.out.println(ans);
	}
	
	
	static int dfs(int depth, int node) {
		if(arr[node].size() == 0) return depth;
		
		int[] temp = new int[arr[node].size()];
		int max = 0;
		for(int i = 0; i < arr[node].size(); i++) 
			temp[i] = dfs(depth + 1, arr[node].get(i));
		
		Arrays.sort(temp);
		
		for(int i = 0; i < arr[node].size(); i++) {
			temp[arr[node].size() - 1 - i] += i;
			max = Math.max(temp[arr[node].size() - 1 - i], max);
		}
		return max;
	}
}
