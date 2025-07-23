
import java.util.*;
import java.io.*;


public class BOJ_1138 {

	static int N;
	static int [] weight;
	static int [] arr;
	static int idx;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		StringTokenizer str = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			weight[i] = Integer.parseInt(str.nextToken());
		}
		
		arr = new int[N];
		arr[weight[0]]=1;
		
		for(int i=1;i<N;i++) {
			idx = 0;
			moveIndexUntilBlank(weight[i]);
			moveIndexUntilNextBlanck();
			arr[idx] = i+1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	
	static void moveIndexUntilBlank(int blankTotal) {
		int blankCount = 0;
		for(int i=0;i<N;i++) {
			if(blankCount==blankTotal) return;
			if(arr[i]==0) blankCount++;
			idx++;
		}
	}
	
	static void moveIndexUntilNextBlanck() {
		for(int i=idx; i<N;i++) {
			if(arr[i]==0) return;
			idx++;
		}
	}

}
