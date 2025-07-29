package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1238 {
    static class Pair{
        int nextP;
        int cost;

        public Pair(int nextP, int cost){
            this.nextP = nextP;
            this.cost = cost;
        }
    }

    static int N, M, X;
    static ArrayList<ArrayList<Pair>> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ans = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++)
            arr.add(new ArrayList<>());

        for(int i = 0; i < M; i++){
            int[] temp = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            arr.get(temp[0]).add(new Pair(temp[1], temp[2]));
        }

        int[] distFromX = bfs(X);
        for(int i = 1; i <= N; i++){
            if(i != X)
                ans = Math.max(bfs(i)[X] + distFromX[i], ans);
        }
        System.out.println(ans);
    }

    public static int[] bfs(int pos){
        int[] dist = new int[1001];

        for(int i = 1; i <= N; i++)
            dist[i] = Integer.MAX_VALUE;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(pos);
        dist[pos] = 0;

        while(!dq.isEmpty()){
            int currP = dq.poll();

            //if(currP == pos) continue;

            for(Pair p : arr.get(currP)){
                int cost = dist[currP] + p.cost;

                if (cost < dist[p.nextP]) {
                    dist[p.nextP] = cost;
                    dq.add(p.nextP);
                }
            }
        }

        return Arrays.copyOf(dist, N+1);
    }
}
