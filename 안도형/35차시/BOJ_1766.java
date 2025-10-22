package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2150 {
	public static ArrayList<Integer>[] node;
	public static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
	public static ArrayDeque<Integer> dq = new ArrayDeque<>();
	
	public static boolean[] visited;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        node = new ArrayList[V+1];
        visited = new boolean[V+1];
        
        for(int i = 1; i <= V; i++) {
        	node[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	node[a].add(b);
        }
        
        for(int i = 1; i <= V; i++) {
        	if(!visited[i]) {
        		dq.push(i);
        		solution(i);
        	}
        	
        	while(!dq.isEmpty()) {
            	ArrayList<Integer> temp = new ArrayList<>();
            	temp.add(dq.pop());
            	ans.add(temp);
            }
        }

        for(int i = 0; i < ans.size(); i++) {
        	System.out.println(ans.get(i).toString());
        }
	}
	
	public static void solution(int curr) {
		if(visited[curr]) {
			return;
		}
		
		for(int next : node[curr]) {
			if(!visited[next]) {
				if(dq.equals(next)) {
					ArrayList<Integer> temp = new ArrayList<>();
					while(!dq.isEmpty()) {
						int num = dq.pop();
						temp.add(num);
						visited[num] = true;
						
						if(num == next)
							break;
					}
					ans.add(temp);
				}else {
					dq.push(next);
					solution(next);
				}
			}
		}
	}
}
