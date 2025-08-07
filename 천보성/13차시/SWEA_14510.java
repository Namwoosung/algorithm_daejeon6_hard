import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_14510 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int testcase=1;testcase<=T;testcase++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] tree = new int[N];
			int max = 0;
			for(int i=0;i<N;i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, tree[i]);
			}
			
			int[] diff = new int[N];
			for(int i=0;i<N;i++) {
				diff[i] = max - tree[i];
			}
			
			int oddCnt = 0;
			int evenCnt = 0;
			
			for(int i=0;i<N;i++) {
				evenCnt += diff[i] / 2;
				oddCnt += diff[i] % 2;
			}
			
			int day = 0;
			if(oddCnt > evenCnt) {
				day = oddCnt * 2 - 1;
			}
			else if(oddCnt == evenCnt) {
				day = evenCnt * 2;
			}
			else {
				int n = (evenCnt - oddCnt + 1) / 3;
				evenCnt -= n;
				oddCnt += n * 2;
				
				if(oddCnt > evenCnt) {
					day = oddCnt * 2 - 1;
				}
				else {
					day = evenCnt * 2;
				}

			}
			
			sb.append('#').append(testcase).append(' ');
			sb.append(day).append('\n');
		}
		System.out.println(sb);
	}

}


