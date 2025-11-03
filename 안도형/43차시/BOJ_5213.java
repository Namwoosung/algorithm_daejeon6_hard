package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5213 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(br.readLine());
        int[][][] arr = new int[N][N*2][4];
        boolean[][] visited = new boolean[N][N*2];
        
        int idxCount = 1;
        for(int i = 0; i < N; i++) {
        	int j, limit;
        	
        	if((i & 1) == 0) {
        		j = 0;
        		limit = N * 2;
        	}else {
        		j = 1;
        		limit = (N - 1) * 2;
        		visited[i][0] = true;
        		visited[i][N * 2 - 1] = true;
        	}
        	
        	for(; j < limit; j += 2) {
    			st = new StringTokenizer(br.readLine());
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			
    			arr[i][j][0] = a;
    			arr[i][j + 1][0] = b;
    			arr[i][j][3] = idxCount++;
    		}
        }
        
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        
        int[] findPos = {0,0,0};
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] {0, 0});
        
        visited[0][0] = true;
        visited[0][1] = true;
        arr[0][0][1] = arr[0][0][2] = arr[0][1][1] = arr[0][1][2] = -1;
        
        while(!dq.isEmpty()) {
        	int[] curr = dq.poll();
        	
        	if(arr[curr[0]][curr[1]][3] == (idxCount - 1)) {
        		findPos[0] = curr[0];
        		findPos[1] = curr[1];
        		break;
        	}
        	
        	for(int i = 0; i < 4; i++) {
        		int nr = dr[i] + curr[0];
        		int nc = dc[i] + curr[1];
        		
        		if(nr >= 0 && nr < N && nc >= 0 && nc < N * 2 && !visited[nr][nc]) {
	        		if(arr[nr][nc][0] == arr[curr[0]][curr[1]][0]) {
	        			if((nr & 1) == 0) {
	                		if((nc & 1) == 1)
	                			nc--;
	                	}else {
	                		if((nc & 1) == 0)
	                			nc--;
	                	}
	        			
	        			visited[nr][nc] = true;
	        	        visited[nr][nc + 1] = true;
	        	        arr[nr][nc][1] = curr[0];
	        			arr[nr][nc][2] = curr[1];
	        			
	        			if(findPos[2] < arr[nr][nc][3]) {
	        				findPos[0] = nr;
	        				findPos[1] = nc;
	        				findPos[2] = arr[nr][nc][3];
	        			}
	        			
	        			dq.add(new int[] {nr, nc});
	        		}
        		}
        		
        		nr = dr[i] + curr[0];
        		nc = dc[i] + curr[1] + 1;
        		
        		if(nr >= 0 && nr < N && nc >= 0 && nc < N * 2 && !visited[nr][nc]) {
	        		if(arr[nr][nc][0] == arr[curr[0]][curr[1] + 1][0]) {
	        			if((nr & 1) == 0) {
	                		if((nc & 1) == 1)
	                			nc--;
	                	}else {
	                		if((nc & 1) == 0)
	                			nc--;
	                	}
	        			
	        			visited[nr][nc] = true;
	        	        visited[nr][nc + 1] = true;
	        	        arr[nr][nc][1] = curr[0];
	        			arr[nr][nc][2] = curr[1];
	        			
	        			if(findPos[2] < arr[nr][nc][3]) {
	        				findPos[0] = nr;
	        				findPos[1] = nc;
	        				findPos[2] = arr[nr][nc][3];
	        			}
	        			
	        			dq.add(new int[] {nr, nc});
	        		}
        		}
        	}
        }

        print(arr, findPos);
	}
	
	public static void print(int[][][] arr, int[] start) {
		int count = 1;
		int currR = start[0];
		int currC = start[1];
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(arr[currR][currC][3]);
		while(arr[currR][currC][1] != -1) {
			int prevR = currR;
			int prevC = currC;
			currR = arr[prevR][prevC][1];
			currC = arr[prevR][prevC][2];
			
			list.add(arr[currR][currC][3]);
			count++;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		for(int i = list.size() - 1; i >= 0; i--) 
			sb.append(list.get(i)).append(" ");
		
		System.out.println(sb.toString());
	}
}
