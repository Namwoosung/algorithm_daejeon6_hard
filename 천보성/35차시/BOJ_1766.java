import java.util.*;
import java.io.*;

public class BOJ_1766 {

    static int N;
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<=N;i++) {
            list.add(new ArrayList<>());
        }

        indegree = new int[N+1];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list.get(s).add(e);
            indegree[e]++;
        }
        topoSort();

        System.out.println(sb);
    }

    public static void topoSort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=1;i<=N;i++) {
            if(indegree[i] == 0) {
                pq.add(i);
            }
        }

        while(!pq.isEmpty()) {
            int temp = pq.poll();
            sb.append(temp).append(" ");

            for(int i:list.get(temp)) {
                indegree[i]--;
                if(indegree[i] == 0) {
                    pq.add(i);
                }
            }
        }
    }
}             