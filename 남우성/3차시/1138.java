import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] person = new int[N];
		for(int i = 0; i < N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[N];
		for(int i = 0; i < N; i++) {
			int cnt = 0; // 나보다 큰 사람이 들어가야 되는 자리(남겨놔야 되는 자리)
			
			for(int j = 0; j < N; j++) {
				if(result[j] != 0) continue; //이미 해당 자리에 사람이 들어가 있는 경우
				
				if(cnt >= person[i]) { // 앞 사람이 들어갈 자리를 확보했다면, 해당 자리에 현재 사람을 배치
					result[j] = i + 1;
					break;
				}
				
				cnt++;
			}
		}
		
		for(int item : result) {
			sb.append(item).append(" ");
		}
		System.out.print(sb);
		
		
	}
}