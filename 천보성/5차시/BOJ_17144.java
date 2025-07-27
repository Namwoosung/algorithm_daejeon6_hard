import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {

    static int airCleaner;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int R,C;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] mapA = new int[R][C];
        int[][] mapB = new int[R][C];
        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<C;j++){
                mapB[i][j] = Integer.parseInt(st.nextToken());
                if(mapB[i][j] == -1){
                    airCleaner = i;
                }
            }
        }

        for(int t=0;t<T;t++){
            copyMap(mapA, mapB);
            spread(mapA, mapB);
            rotation(mapB);
        }

        System.out.println(getTotal(mapB));
    }

    public static void copyMap(int[][] mapA, int[][] mapB){
        for(int i=0;i<R;i++){
            mapA[i] = mapB[i].clone();
        }
    }
    public static void spread(int[][] mapA, int[][] mapB){

        for (int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if (mapA[i][j] > 0) {

                    int dust = mapA[i][j] / 5;
                    int count = 0;

                    for(int d=0;d<4;d++){
                        int nextX = i + dx[d];
                        int nextY = j + dy[d];
                        if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) continue;
                        if((nextX == airCleaner || nextX == airCleaner-1) && nextY == 0) continue;

                        mapB[nextX][nextY] += dust;
                        count++;
                    }
                    mapB[i][j] -= dust * count;
                }
            }
        }
    }

    public static int getTotal(int[][] map){
        int total = 2;

        for (int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                total += map[i][j];
            }
        }

        return total;
    }
    public static void rotation(int[][] map){
        for (int i=airCleaner-2;i>0;i--){
            map[i][0]= map[i-1][0];
        }
        for (int i=0;i<C-1;i++){
            map[0][i] = map[0][i+1];
        }
        for (int i=0;i<airCleaner-1;i++){
            map[i][C-1]= map[i+1][C-1];
        }
        for (int i=C-1;i>1;i--){
            map[airCleaner-1][i] = map[airCleaner-1][i-1];
        }

        map[airCleaner-1][1] = 0;

        for (int i=airCleaner+1;i<R-1;i++){
            map[i][0] = map[i+1][0];
        }

        for (int i=0;i<C-1;i++){
            map[R-1][i] = map[R-1][i+1];
        }

        for (int i=R-1;i>airCleaner;i--){
            map[i][C-1] = map[i-1][C-1];
        }

        for (int i=C-1;i>1;i--){
            map[airCleaner][i] = map[airCleaner][i-1];
        }

        map[airCleaner][1] = 0;

    }

}
