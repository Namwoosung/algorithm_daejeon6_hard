package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1708 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
         
        int N = Integer.parseInt(br.readLine());
        long[][] node = new long[N][2];
        long[] first = new long[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        int firstIdx = -1;
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	node[i][0] = a;
        	node[i][1] = b;
        	
        	if (first[1] > b || (first[1] == b && first[0] > a)) {
                first[0] = a;
                first[1] = b;
                firstIdx = i;
            }
        }
        
        long[] temp = node[0];
        node[0] = node[firstIdx];
        node[firstIdx] = temp;
        
        first[0] = node[0][0];
        first[1] = node[0][1];
        
        Arrays.sort(node, 1, N, new Comparator<long[]>() {
			@Override
			public int compare(long[] a, long[] b) {
				int result = CCW(first, a, b);
				if(result > 0) return -1; 		//반시계
				else if(result < 0) return 1;	//시계
				
				long dist1 = (a[0] - first[0]) * (a[0] - first[0]) + (a[1] - first[1]) * (a[1] - first[1]);
				long dist2 = (b[0] - first[0]) * (b[0] - first[0]) + (b[1] - first[1]) * (b[1] - first[1]);
				
				if(dist1 > dist2) return 1;
				return -1;
			}
        	
        });
        
        
        Stack<long[]> stack = new Stack<>();
        stack.add(node[0]);
        stack.add(node[1]);
        
        for(int i = 2; i < N; i++) {
        	while(stack.size() > 1) {
        		int result = CCW(stack.get(stack.size() - 2), stack.get(stack.size() - 1), node[i]);
        		
        		if(result <= 0)
        			stack.pop();
        		else
        			break;
        	}
        	
        	stack.add(node[i]);
        }
        
        System.out.println(stack.size());
	}
	
	//벡터의 외적 방법
	// x1 x2 x3 x1 X로 교차해서 곱해주되 (왼쪽에서 오른쪽 아래 대각선의 곱의 합) - (오른쪽에서 왼쪽아래 대각선의 곱의 합) 이다. 
	//   X  X  X
	// y1 y2 y3 y1
	public static int CCW(long[] a, long[] b, long[] c) {
		long result = (a[0] * b[1] + b[0] * c[1] + c[0] * a[1]) - (b[0] * a[1] + c[0] * b[1] + a[0] * c[1]);
		
		if(result > 0) return 1;
		else if(result < 0) return -1;
		return 0;
	}
}
