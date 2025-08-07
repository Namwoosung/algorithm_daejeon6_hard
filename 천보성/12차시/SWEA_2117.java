import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2117{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testcase=1;testcase<=T;testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];
            
            // home 위치를 저장
            List<int[]> homeList = new ArrayList<>();

            for (int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1){
                        homeList.add(new int[] {i,j});
                    }
                }
            }

            int ans = 0;
            int maxEarn= homeList.size() * M;
            
            for (int i=0;i<N;i++){
                for (int j=0;j<N;j++){
                    for (int k=1;k<=N*2;k++){
                        int cost = k * k + (k-1)*(k-1); // 기본 운영 비용
                        if (cost > maxEarn) break;
                        int count = 0;
                        
                        // 거리 안에 있는 지 확인
                        for (int[] home : homeList){
                            if (Math.abs(home[0]-i) + Math.abs(home[1]-j) < k){
                                count++;
                            }
                        }

                        if (0 <= count * M - cost){
                            ans =  Math.max(ans, count);
                        }
                    }

                }
            }

            sb.append('#').append(testcase).append(' ');
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}
