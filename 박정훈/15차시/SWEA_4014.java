import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
   
public class SWEA_4014 {
    static int count;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
          
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int[][] table = new int[N][N];
            count = 0;
             
            //입력
            for(int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < N; x++) {
                    table[y][x] = Integer.parseInt(st.nextToken());
                }
            }
             
            //가로 검사
            for(int y = 0; y < N; y++) {
                int numStartIndex = 0;
                int lastNum = table[y][0];
                boolean flag = true;
                for(int x = 1; x < N; x++) {
                    int num = table[y][x];
                    if(num != lastNum) { //높이가 달라지면
                        if(Math.abs(num - lastNum) == 1) {
                            if(num > lastNum) { // 이전 X개가 같아야함
                                int len = x - numStartIndex;
                                if(len < X) { // 설치 불가능
                                    flag = false;
                                    x = N;
                                }
                                lastNum = num;
                                numStartIndex = x;
                            } else { // 이후 X개가 같아야함
                                lastNum = num;
                                // X개가 같은 지 검사
                                for(int i = 0; i < X; i++) {
                                    if(x+i >= N) { //범위 벗어남
                                        flag = false;
                                        break;
                                    }
                                    if(table[y][x+i] != lastNum) {
                                        flag = false;
                                        break;
                                    }
                                }
                                if(flag) { // X개가 같다면 그 이후부터 길이 계산
                                    numStartIndex = x + X;
                                    lastNum = num;
                                } else { // 다르면 그 줄은 안 됨
                                    x = N;
                                }
                            }
                        } else { // 높이 차가 2이상이면 그 줄은 안 됨
                            flag = false;
                            x = N;
                        }
                    }
                }
                if(flag) {
                    count++;
                }
            }
             
            //세로 검사
            for(int x = 0; x < N; x++) {
                int numStartIndex = 0;
                int lastNum = table[0][x];
                boolean flag = true;
                for(int y = 1; y < N; y++) {
                    int num = table[y][x];
                    if(num != lastNum) { //높이가 달라지면
                        if(Math.abs(num - lastNum) == 1) {
                            if(num > lastNum) { // 이전 X개가 같아야함
                                int len = y - numStartIndex;
                                if(len < X) { // 설치 불가능
                                    flag = false;
                                    y = N;
                                }
                                lastNum = num;
                                numStartIndex = y;
                            } else { // 이후 X개가 같아야함
                                lastNum = num;
                                // X개가 같은 지 검사
                                for(int i = 0; i < X; i++) {
                                    if(y+i >= N) { //범위 벗어남
                                        flag = false;
                                        break;
                                    }
                                    if(table[y+i][x] != lastNum) {
                                        flag = false;
                                        break;
                                    }
                                }
                                if(flag) { // X개가 같다면 그 이후부터 길이 계산
                                    numStartIndex = y + X;
                                    lastNum = num;
                                } else { // 다르면 그 줄은 안 됨
                                    y = N;
                                }
                            }
                        } else { // 높이 차가 2이상이면 그 줄은 안 됨
                            flag = false;
                            y = N;
                        }
                    }
                }
                if(flag) {
                    count++;
                }
            }
             
            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}