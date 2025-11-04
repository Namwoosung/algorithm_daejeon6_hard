import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1708 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        
        List<int[]> map = new ArrayList<>();
        
        int minIdx = 0;
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        
        for(int i=0;i<N;i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	if(minY > y) {
        		minY = y;
        		minX = x;
        		minIdx = i;
        	}
        	else if(minY == y && minX > x) {
        		minX = x;
        		minIdx = i;
        	}
        	
        	map.add(new int[] {x, y});
        }
        
        int[] p0 = map.get(minIdx);
        map.set(minIdx, map.get(0));
        map.set(0, p0);
        
        Collections.sort(map.subList(1, N), new Comparator<int[]>() {
        	@Override
        	public int compare(int[] p1, int[] p2) {
        		long v = ccw(p0, p1, p2);
        		
        		if(v > 0) {
        			return -1;
        		}
        		else if(v < 0) {
        			return 1;
        		}
        		else {
        			return Long.compare(dist(p0, p1), dist(p0, p2));
        		}
        	}
		});
        
        
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(map.get(0));
        stack.push(map.get(1));
        
        for(int i=2;i<N;i++) {
        	int[] next = map.get(i);
        	
        	while(stack.size() >= 2) {
        		int[] p1 = stack.poll();
        		int[] p2 = stack.peek();
        		
        		long v = ccw(p2, p1, next);
        		
        		if(v > 0) {
        			stack.push(p1);
        			break;
        		}
        	}
        	stack.push(next);
        }
        
        System.out.println(stack.size());
	}
	
	public static long ccw(int[] p0, int[] p1, int[] p2) {
		
		return (long)(p1[0] - p0[0]) * (p2[1] - p0[1]) 
				- (long)(p1[1] - p0[1]) * (p2[0] - p0[0]);
		
		
	}
	
	public static long dist(int[] p0, int[] p1) {
	    return (long)(p1[0] - p0[0]) * (p1[0] - p0[0]) + 
	           (long)(p1[1] - p0[1]) * (p1[1] - p0[1]);
	}
}
