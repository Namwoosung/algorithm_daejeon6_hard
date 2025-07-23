import java.util.*;
import java.io.*;

public class BOJ_1325 {

    static ArrayList<Integer>[] graph;
    static int N;
    static int[] depth;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];

        for(int i=0;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
        }

        depth = new int[N+1];

        for(int i=1;i<=N;i++){
            boolean[] visited = new boolean[N+1];
            bfs(i, visited);
        }

        int max = Arrays.stream(depth).max().getAsInt();
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=N;i++){
            if(max == depth[i]){
                sb.append(i).append(' ');
            }
        }
        System.out.println(sb);

    }

    public static void bfs(int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int next : graph[now]){
                if(!visited[next]){
                    visited[next] = true;
                    depth[next]++;
                    queue.add(next);
                }
            }
        }

    }
}