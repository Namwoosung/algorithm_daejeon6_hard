import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); int w = Integer.parseInt(st.nextToken()); int L = Integer.parseInt(st.nextToken());
		
		int[] trucks = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}
		
		// 다리 위에 올라갈 수 있는 트럭을 투포인터로 팔로잉
		int start = 0;
		int end = 0;
		int[] dist = new int[n];
		
		int sum = trucks[0];
		int totalTime = 0;
		
		while(start < n) {
			
			for(int i = start; i <= end; i++) { // 올라가있는 트럭은 한 칸 씩 이동
				dist[i]++;
			}
			
			if(dist[start] > w) { // 가장 앞 트럭이 다리를 넘어가면 보내기
				sum -= trucks[start];
				start++;
			}
			
			if(end < n-1 && sum + trucks[end+1] <= L) { // 트럭이 올라올 수 있으면 올라오기
				end++;
				sum += trucks[end];
				dist[end]++;
			}
			
			totalTime++;
		}
		System.out.print(totalTime);
	}
}
