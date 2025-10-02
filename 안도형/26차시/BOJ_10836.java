package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10836 {
	public static int[] dr = {-1, 0, -1};
	public static int[] dc = {-1, -1, 0};
	public static int[][] map;
	
	public static int M,N;
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[M][M];
        for(int i = 0; i < M; i++) {
        	for(int j = 0; j < M; j++) {
        		map[i][j] = 1;
        	}
        }
        
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int zero = Integer.parseInt(st.nextToken());
        	int one = Integer.parseInt(st.nextToken());
        	int two = Integer.parseInt(st.nextToken());
        	int[] sumCount = {zero, one, two};
        	
        	int currCountIdx = 0;


        	int limit = M * 2 - 1;
        	int j = sumCount[currCountIdx];
        	sumCount[currCountIdx] = 0;
        	for(; j < limit; j++) {
        		while(sumCount[currCountIdx] == 0) {
        			currCountIdx++;
        		}
        		
        		if(j < M) {
        			map[M - 1 - j][0] += currCountIdx;
        		}else {
        			map[0][j - M + 1] += currCountIdx;
        		}
    			sumCount[currCountIdx]--;
    			
        	}
        }
        
        solution();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
        	for(int j = 0; j < M; j++) {
        		if(i > 1 && j > 0)
        			map[i][j] = map[i - 1][j];
        		
        		sb.append(map[i][j]).append(" ");
            }
        	sb.append("\n");
        }
        System.out.print(sb);
	}
	
	public static void solution() {
		int max = 0;
		for(int i = 0; i < 3; i++) {
			int nr = dr[i] + 1;
			int nc = dc[i] + 1;
			
			if(max < map[nr][nc]) 
				max = map[nr][nc];
		}
		map[1][1] = max;
		
		for(int i = 2; i < M; i++) {
			max = 0;
			for(int j = 1; j < 3; j++) {
				int nr = dr[j] + 1;
				int nc = dc[j] + i;
				
				if(max < map[nr][nc]) 
					max = map[nr][nc];
			}
			
			map[1][i] = max;
		}
	}
}
