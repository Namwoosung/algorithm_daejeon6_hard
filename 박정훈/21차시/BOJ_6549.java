import java.io.*;
import java.util.*;

public class BOJ_6549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            
            // 0이면 입력 종료
            if (N == 0) break;
            
            // 막대 높이 입력
            long[] heights = new long[N + 2]; // 양쪽에 0을 추가
            for (int i = 1; i <= N; i++) {
                heights[i] = Long.parseLong(st.nextToken());
            }
            
            // 스택으로 활용
            Deque<Integer> stack = new ArrayDeque<>();
            long maxArea = 0;
            
            for (int i = 0; i < N + 2; i++) {
            	// 스택이 비어있지 않고 막대의 높이가 작아져서 이상으로 확장이 불가
                while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                	// 마지막 막대의 인덱스
                    int index = stack.pop();
                    // 오른쪽으로 확장할 너비 (index ~ i까지는 같거나 높은 막대만 있어서 현재 높이로 확장이 가능
                    long width = i - stack.peek() - 1;
                    maxArea = Math.max(maxArea, heights[index] * width);
                }
                stack.push(i);
            }
            
            sb.append(maxArea).append("\n");
        }
        
        System.out.print(sb);
    }
}