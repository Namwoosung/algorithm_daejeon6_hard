import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 0; testCase < T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] tree = new int[N+1]; //각 index 마다의 부모를 저장
			
			StringTokenizer st;
			for(int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				tree[b] = a;
			}
			
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2= Integer.parseInt(st.nextToken());
			
			Set<Integer> parents = new HashSet<>();
			
			//먼저 node1 자신 및 자신의 부모들을 parents에 저장
			int now = node1;
			
			while(now != 0) {
				parents.add(now);
				now = tree[now];
			}
			
			// node2의 부모들을 parents에 저장해 나가는데 처음 동일한 item이 들어가면 해당 index가 공통조상
			now = node2;
			int result = 0;
			
			while(now != 0) {
				if(!parents.add(now)) {
					result = now;
					break;
				}
				now = tree[now];
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}