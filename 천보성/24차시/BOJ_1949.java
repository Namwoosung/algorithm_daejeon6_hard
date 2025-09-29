import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class BOJ_1949 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] people = new int[N+1];
        dp = new int[N+1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            people[i] = Integer.parseInt(st.nextToken());
            dp[i][1] = people[i];
        }

        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i=0;i<N+1;i++){
            tree.add(new ArrayList<>());
        }

        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }
        dfs(tree, people, 1, 0);

        System.out.println(Math.max(dp[1][0], dp[1][1]));

    }
    private static void dfs(ArrayList<ArrayList<Integer>> tree, int[] people, int n, int p){

        for (int i:tree.get(n)){
            if (p != i){
                dfs(tree, people, i, n);
                dp[n][0] += Math.max(dp[i][0], dp[i][1]);
                dp[n][1] += dp[i][0];
            }
        }

    }
}