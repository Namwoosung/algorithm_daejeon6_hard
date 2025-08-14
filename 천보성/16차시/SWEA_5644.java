import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5644 {

    static class Battery{
        int x;
        int y;
        int c;
        int p;
        Battery(int x, int y, int c, int p){
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }
    static int[] dx = {0,0,1,0,-1};
    static int[] dy = {0,-1,0,1,0};
    static ArrayList<Battery> batteryList;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int testcase=1;testcase<=T;testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            batteryList = new ArrayList<>();
            int[] peopleA = new int[M+1];
            int[] peopleB = new int[M+1];
            st = new StringTokenizer(br.readLine());
            for (int i=1;i<=M;i++){
                peopleA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i=1;i<=M;i++){
                peopleB[i] = Integer.parseInt(st.nextToken());
            }

            for (int i=0;i<A;i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                batteryList.add(new Battery(x, y, C, P));
            }

            sb.append('#').append(testcase).append(' ');
            sb.append(simul(peopleA, peopleB));
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static int simul(int[] peopleA, int[] peopleB){
        int aX = 1;
        int aY = 1;
        int bX = 10;
        int bY = 10;
        int total = 0;

        for (int t=0;t<=M;t++){

            ArrayList<Integer> listA = getCanChargeList(aX, aY);
            ArrayList<Integer> listB = getCanChargeList(bX, bY);

            int max = 0;

            if (listA.isEmpty()){
                for (int idx : listB){
                    max = Math.max(max, batteryList.get(idx).p);
                }
            }
            else if (listB.isEmpty()){
                for (int idx : listA){
                    max = Math.max(max, batteryList.get(idx).p);
                }
            }

            else{
                for (int idxA : listA){
                    for (int idxB : listB){
                        if (idxA == idxB){
                            max = Math.max(max,batteryList.get(idxA).p);
                        }
                        else{
                            max = Math.max(max, batteryList.get(idxA).p + batteryList.get(idxB).p);
                        }
                    }
                }
            }

            total += max;

            if (t == M) break;

            int dir1 = peopleA[t+1];
            int dir2 = peopleB[t+1];

            aX += dx[dir1];
            aY += dy[dir1];
            bX += dx[dir2];
            bY += dy[dir2];
        }
        return total;
    }
    public static ArrayList<Integer> getCanChargeList(int x, int y){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<batteryList.size();i++){
            Battery b = batteryList.get(i);

            if (Math.abs(b.x - x) + Math.abs(b.y - y) <= b.c){
                list.add(i);
            }
        }
        return list;
    }
}
