package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_2117 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {-1, 1, 0, 0};
	static int[] K = {0, 1, 5, 13, 25, 41, 61, 85, 113, 145, 181, 221, 265, 313, 365, 421, 481, 545, 613, 685, 761, 841};

	static int N, M;
	
	static boolean[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					char c = st.nextToken().charAt(0);
					
					if(c == '1') {
						map[i][j] = true;
					}else
						map[i][j] = false;
				}
			}
			
			System.out.println("#" + (t+1) + " " + homeService());
		}
	}
	
	public static int homeService() {
		int maxK = 0;
	    int mainCount = 0;
	    int houseCount = 0;

	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            if (map[i][j]) houseCount++;
	        }
	    }

	    for (int i = 1; i < K.length; i++) {
	        if (houseCount * M < K[i]) break;
	        maxK = i;
	    }
		
		for (int r = 0; r < N; r++) {
	        for (int c = 0; c < N; c++) {
	            for (int k = 1; k <= maxK; k++) {
	                int subCount = setting(r, c, k - 1);
	                int total = (subCount * M) - K[k];
	                if (total >= 0 && subCount > mainCount) {
	                    mainCount = subCount;
	                }
	            }
	        }
	    }
	
		return mainCount;
	}
	
	public static int setting(int r, int c, int m) {
	    int count = 0;
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            if (map[i][j] && Math.abs(r - i) + Math.abs(c - j) <= m) {
	                count++;
	            }
	        }
	    }
	    return count;
	}
}
