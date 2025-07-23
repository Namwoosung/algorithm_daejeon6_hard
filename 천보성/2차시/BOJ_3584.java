import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3584 {

    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            int N = Integer.parseInt(br.readLine());

            parent = new int[N+1];

            for(int i=1;i<N;i++){
                st  = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
            }

            st = new StringTokenizer(br.readLine());
            sb.append(getLCA(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append('\n');
        }
        System.out.println(sb);
    }
    public static int getDepth(int node){
        int depth = 0;

        while (parent[node] != 0){
            depth++;
            node = parent[node];
        }

        return depth;
    }

    public static int getLCA(int node1, int node2){
        int depthNode1 = getDepth(node1);
        int depthNode2 = getDepth(node2);

        if(depthNode1 > depthNode2){
            node1 = updateDepth(node1, depthNode1 - depthNode2);
        }
        else if (depthNode1 < depthNode2){
            node2 = updateDepth(node2, depthNode2 - depthNode1);
        }

        while (node1 != node2){
            node1 = parent[node1];
            node2 = parent[node2];
        }
        return node1;
    }

    public static int updateDepth(int node, int diff){
        for(int i=0;i<diff;i++){
            node = parent[node];
        }
        return node;
    }
}


