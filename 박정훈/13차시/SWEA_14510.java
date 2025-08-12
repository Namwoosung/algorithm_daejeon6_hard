import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class SWEA_14510 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
         
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] trees = new int[N];
            int[] diff = new int[N];
            int count = 0;
            int maxH = Integer.MIN_VALUE; // 가장 높은 나무 높이
            int day = 0;
            int odd = 0;
             
            // 나무 높이 입력
            for(int i = 0; i < N; i++) {
                int height = Integer.parseInt(st.nextToken());
                trees[i] = height;
                maxH = Math.max(maxH, height); // 가장 높은 나무 높이 저장
            }
             
            // 높이 차이 입력
            for(int i = 0; i < N; i++) {
                diff[i] = maxH - trees[i];
                if(diff[i] != 0) {
                    if(diff[i] % 2 == 1) {
                        odd++;
                    }
                    count++; // 물 줘야할 나무 개수
                }
            }
             
            // 시뮬레이션
            while(count > 0) { // 물줘야할 나무가 하나라도 있다면
                day++;
                // 홀수날엔 1 짝수 날엔 2
                int h = (day % 2 == 0? 2 : 1);
                 
                for(int i = 0; i < N; i++) {
                    if(diff[i] == 0) continue; // 다 자란 나무
                     
                    if(count > 1) { // 물 줄 나무 1개 이상
                        if(h == 1 && odd > 0 && diff[i] % 2 == 0) continue; // 홀수날엔 홀수 먼저 주기
                        if(diff[i] - h >= 0) { // 젤 큰 나무를 초과하지 않음
                            if(h == 1 && diff[i] % 2 == 0) { // 짝수 -> 홀수
                                odd++;
                            } else if(h == 1 && diff[i] % 2 == 1) { // 홀수 -> 짝수
                                odd--;
                            }
                            diff[i] -= h;
                            if(diff[i] == 0) count--; // 다 자람
                            break;
                        }
                    } else {
                        if(h == 1 && diff[i] == 2) continue; // 다음날 주면 끝
                        if(diff[i] - h >= 0) { // 젤 큰 나무를 초과하지 않음
                            diff[i] -= h;
                            if(diff[i] == 0) count--; // 다 자람
                            break;
                        }
                    }
                }
            }
 
            sb.append("#").append(test_case).append(" ").append(day).append("\n");
        }
         
        System.out.print(sb);
    }
}