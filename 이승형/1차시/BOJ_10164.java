package seunghyeong.week01.d_20250720;

import java.io.*;
import java.util.*;

public class BOJ_10164 {
    static int N, M, K;
    static int [][] arr;
    static int [] dx = {0, -1};
    static int [] dy = {-1, 0};
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());

        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        K = Integer.parseInt(str.nextToken());
        arr = new int[N+1][M+1];
        int result = 0;

        if (K == 0) {
            result = getResult(1, 1, N, M);
        }else{
            int nx = K/M+1;
            int ny = K%M;
            if(K%M==0){
                nx = K/M;
                ny = M;
            }

            getResult(1, 1, nx, ny);
            result = getResult(nx, ny, N,M);

        }
        System.out.println(result);
    }

    static int getResult(int startX, int startY, int endX, int endY){
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx>0 && ny>0 && nx<=endX && ny<=endY){
                        arr[i][j]=arr[i][j-1]+arr[i-1][j];
                    }else{
                        arr[i][j]=1;
                    }
                }
            }
        }
        return arr[endX][endY];
    }
}