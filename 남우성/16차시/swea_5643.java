import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        for(int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
             
            List<List<Integer>> graph = new ArrayList<>(); // 그래프
            List<List<Integer>> reGraph = new ArrayList<>(); // 역방향 그래프
 
            for(int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
                reGraph.add(new ArrayList<>());
            }
             
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1; int b = Integer.parseInt(st.nextToken()) - 1;
                graph.get(a).add(b);
                reGraph.get(b).add(a);
            }
             
            int result = 0;
             
             
            for(int i = 0; i < N; i++) {
                boolean[] isVisited = new boolean[N];
                int cnt = 0;
                 
                Deque<Integer> queue = new ArrayDeque<>();
                queue.add(i);
                isVisited[i] = true;
                while(!queue.isEmpty()) { //bfs 탐색(그래프 방향)
                    int now = queue.poll();
                    cnt++;
                     
                    for(int temp = 0; temp < graph.get(now).size(); temp++) {
                        int next = graph.get(now).get(temp);
                        if(!isVisited[next]) {
                            queue.add(next);
                            isVisited[next] = true;
                        }
                    }
                }
                 
                queue = new ArrayDeque<>();
                isVisited = new boolean[N];
                queue.add(i);
                isVisited[i] = true;
                while(!queue.isEmpty()) { //bfs 탐색(그래프 반대 방향)
                    int now = queue.poll();
                    cnt++;
                     
                    for(int temp = 0; temp < reGraph.get(now).size(); temp++) {
                        int next = reGraph.get(now).get(temp);
                        if(!isVisited[next]) {
                            queue.add(next);
                            isVisited[next] = true;
                        }
                    }
                }
                 
                cnt--;
                if(cnt == N) {
                    result++;
                }
            }
             
             
            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
}