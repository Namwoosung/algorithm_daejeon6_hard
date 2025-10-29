import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.soap.Node;


public class BOJ_1135 {
	 

	static ArrayList<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int N = Integer.parseInt(br.readLine());
	    
	    tree = new ArrayList[N];
	    
	    for(int i=0;i<N;i++) {
	    	tree[i] = new ArrayList<>();
	    }
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    st.nextToken();
	    
	    for(int i=1;i<N;i++) {
	    	int num = Integer.parseInt(st.nextToken());
	    	tree[num].add(i);
	    }
	    
	    System.out.println(getMinTime(0));
	    
	}
	
	public static int getMinTime(int idx) {
		int size = tree[idx].size();
		
		if(size == 0) {
			return 0;
		}

		int[] times = new int[size];
		
		for(int i=0;i<size;i++) {
			times[i] = getMinTime(tree[idx].get(i));
		}
		

		Arrays.sort(times);
		
		int max = 0;
		
		for(int i=1;i<=size;i++) {
			max = Math.max(times[size - i] + i, max);
		}
		
		
		return max; 
	}
}
