import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10836 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[M][M];
		int[] offset = new int[2 * M - 1];
				
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int value0 = Integer.parseInt(st.nextToken());
			int value1 = Integer.parseInt(st.nextToken());
			int value2 = Integer.parseInt(st.nextToken());
			
			int index = 0;
			index += value0;
			
			if(value1 != 0) {
				offset[index] += 1;
			}
			
			index += value1;
			
			if(value1 != 0 && index < 2*M - 1) {
				offset[index] -= 1;
			}
			
			if(value2 != 0) {
				offset[index] += 2;
			}
			
		}
		
		map[M-1][0] = offset[0];
		
		for(int i=M-2;i>=0;i--) {
			map[i][0] = map[i+1][0] + offset[M - i - 1];
		}
		
		for(int i=1;i<M;i++) {
			map[0][i] = map[0][i-1] + offset[M + i - 1]; 
		}
		
		
		for(int i=1;i<M;i++) {
			for(int j=1;j<M;j++) {
				map[i][j] = Math.max(map[i-1][j], Math.max(map[i-1][j-1], map[i][j-1]));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<M;j++) {
				sb.append(map[i][j] + 1).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		
	}
}
