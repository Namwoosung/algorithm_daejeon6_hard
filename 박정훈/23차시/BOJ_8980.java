import java.io.*;
import java.util.*;

public class BOJ_8980 {
    static class Box {
        int start, end, boxes;
        
        Box(int start, int end, int boxes) {
            this.start = start;
            this.end = end;
            this.boxes = boxes;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭 용량
        int M = Integer.parseInt(br.readLine()); // 박스 정보 개수
        
        Box[] boxArr = new Box[M];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int boxes = Integer.parseInt(st.nextToken());
            boxArr[i] = new Box(start, end, boxes);
        }
        
        // 도착 마을 순으로 정렬
        Arrays.sort(boxArr, (a, b) -> a.end - b.end);
        
        // 각 구간별 현재 실린 박스 수
        int[] boxCount = new int[N + 1];
        int delivered = 0;
        
        for (Box b : boxArr) {
            // 시작 -> 도착 구간에서 현재 최대 실린 박스 수 찾기
            int maxBox = 0;
            for (int i = b.start; i < b.end; i++) {
                maxBox = Math.max(maxBox, boxCount[i]);
            }
            
            // 실을 수 있는 최대 박스 수 계산
            int canLoad = Math.min(b.boxes, C - maxBox);
            
            // 해당 구간에 박스 추가
            for (int i = b.start; i < b.end; i++) {
                boxCount[i] += canLoad;
            }
            
            delivered += canLoad;
        }
        
        System.out.println(delivered);
    }
}