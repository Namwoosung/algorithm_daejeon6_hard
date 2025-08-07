import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471 {

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int N;
    static int[] people;
    static int ans = Integer.MAX_VALUE;
    static int size1;
    static int size2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        people = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }



        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for(int j=0;j<cnt;j++) {
                int node = Integer.parseInt(st.nextToken());
                graph.get(i).add(node);
            }
        }

        visited = new boolean[N+1];
        visited[1] = true;
        for(int i=1;i<=N/2;i++) {
            size1 = i;
            size2 = N-i;
            dfs(i, 1);
        }
        if(ans == Integer.MAX_VALUE) ans = -1;

        System.out.println(ans);

    }


    public static void dfs(int depth, int node) {
        if(depth == 0) {
            boolean flag1 = false; // 1번 그룹 확인 변수
            boolean flag2 = false; // 2번 그룹 확인 변수

            for(int i=1;i<=N;i++) {
                if(visited[i]) {
                    flag1 = checkLink(i, false);
                }
                else {
                    flag2 = checkLink(i, true);
                }
                if(flag1 && flag2) { // 모두 연결돼있다면
                    ans = Math.min(ans, getDiffPeople());
                    break;
                }
            }

            return;
        }

        for (int i=node;i<=N;i++){
            visited[i] = true;
            dfs(depth-1, i+1);
            visited[i] = false;
        }
    }

    // 연결 됐는지 확인하는 메서드 bfs
    public static boolean checkLink(int node, boolean type) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        boolean[] temp = visited.clone();
        temp[node] = type;
        int cnt = 0;

        while(!queue.isEmpty()) {
            int now = queue.poll();
            cnt++;

            for(int next : graph.get(now)) {
                if(temp[next] != type) {
                    temp[next] = type;
                    queue.add(next);
                }
            }
        }
        
        if(!type && cnt == size1) {
            return true;
        }
        else return type && cnt == size2;
    }
    
    // 인구 수 차이 얻는 메서드
    public static int getDiffPeople() {
        int cnt = 0;

        for(int i=1;i<=N;i++) {
            if(visited[i]) {
                cnt += people[i];
            }
            else {
                cnt -= people[i];
            }
        }
        return Math.abs(cnt);
    }
}