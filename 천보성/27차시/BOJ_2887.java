import java.util.*;
import java.io.*;

class Dot{
    int num;
    int x,y,z;

    Dot(int num, int x, int y, int z){
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Edge implements Comparable<Edge>{
    int num1, num2;
    int val;

    Edge(int num1, int num2, int val){
        this.num1 = num1;
        this.num2 = num2;
        this.val = val;
    }
    @Override
    public int compareTo(Edge o){
        return this.val - o.val;
    }
}
public class BOJ_2887
{
    static ArrayList<Edge> list = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Dot[] arr = new Dot[N];
        parent = new int[N];
        Long ans = 0L;

        StringTokenizer st;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            arr[i] = new Dot(i,x,y,z);
            parent[i] = i;
        }

        Arrays.sort(arr, (o1,o2) -> o1.x - o2.x);
        for(int i=0;i<N-1;i++){
            int temp = Math.abs(arr[i].x - arr[i+1].x);
            list.add(new Edge(arr[i].num, arr[i+1].num, temp));
        }

        Arrays.sort(arr, (o1,o2) -> o1.y - o2.y);
        for(int i=0;i<N-1;i++){
            int temp = Math.abs(arr[i].y - arr[i+1].y);
            list.add(new Edge(arr[i].num, arr[i+1].num, temp));
        }

        Arrays.sort(arr, (o1,o2) -> o1.z - o2.z);
        for(int i=0;i<N-1;i++){
            int temp = Math.abs(arr[i].z - arr[i+1].z);
            list.add(new Edge(arr[i].num, arr[i+1].num, temp));
        }

        Collections.sort(list);

        for(Edge i:list){
            if(find(i.num1) != find(i.num2)){
                union(i.num1, i.num2);
                ans += i.val;

            }
        }
        System.out.println(ans);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            if(x < y){
                parent[y] = x;
            }
            else{
                parent[x] = y;
            }
        }

    }

    public static int find(int x){
        if(x == parent[x]){
            return x;
        }
        else{
            return parent[x] = find(parent[x]);
        }
    }
}