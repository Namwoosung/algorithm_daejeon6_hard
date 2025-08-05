import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); int C = Integer.parseInt(st.nextToken());
		
		int[] homes = new int[N];
		for(int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(homes);
		
		int start = 0; int end = homes[N-1] - homes[0];
		int mid = 0;
		int result = 0;
		while(start <= end) {
			boolean flag = false;
			mid = (start + end) / 2;
			
			// 0번 집부터 최소 mid 만큼 띄워가면서 공유기를 설치
			int now = homes[0];
			int cnt = 1;
			int bound = 0;
			for(int i = 1; i < N; i++) {
				bound = now + mid;
				
				if(homes[i] < bound) continue;
				else { // 설치 가능하면 설치
					cnt++;
					now = homes[i];
				}
				
				if(cnt >= C) {
					flag = true;
					result = mid;
				}
			}
			
			if(flag) { // 가능하면 공유기 설치 거리를 더 늘리기
				start = mid + 1;
			}else { // 불가능하면 거리를 줄이기
				end = mid - 1;
			}
			
		}
		
		System.out.println(result);
	}
}