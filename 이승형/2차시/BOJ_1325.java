
import java.io.*;
import java.util.*;

// 1 3
// 2 3
// 3 4,5
// 4
// 5

// depth 구해서 []에 저장
// 저장된 depth 중 가장 큰 값 찾기
// for문으로 해당 depth인 경우의 index+1 출력
public class BOJ_1325 {

    static int N, M;
    static ArrayList<Integer> [] graph;
    static int [] depth;
    static boolean [] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());
        N = Integer.parseInt(str.nextToken());
        M = Integer.parseInt(str.nextToken());
        graph = new ArrayList[N+1];
        depth = new int [N+1];
        visited = new boolean[N+1];

        for(int i=0;i<N+1;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            str = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(str.nextToken());
            int end = Integer.parseInt(str.nextToken());
            graph[end].add(start);
        }

        int maxValue = Integer.MIN_VALUE;
        for(int i=1;i<N+1;i++) {
            visited = new boolean[N+1];
            depth[i] = bfs(i);
            maxValue = Math.max(depth[i], maxValue);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<N+1;i++) {
            if(depth[i]==maxValue) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    static int bfs(int start) {
        int cnt = 0;
        Queue<Integer> que = new LinkedList<>();
        visited[start] = true;
        cnt++;
        que.offer(start);
        while(!que.isEmpty()) {
            int nowNode = que.poll();
            for(int i=0;i<graph[nowNode].size();i++) {
                int nextNode = graph[nowNode].get(i);
                if(!visited[nextNode]) {
                    visited[nextNode] = true;
                    cnt++;
                    que.offer(nextNode);
                }
            }
        }
        return cnt;
    }
}

