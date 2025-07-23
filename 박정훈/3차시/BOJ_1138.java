import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1138 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] count = new int[N];
		int[] line = new int[N];
		
		for(int i = 0; i < N; i++) {
			count[i] = Integer.parseInt(st.nextToken());
		}
		
		// 키 젤 작은 사람은 모두가 키가 크므로 사람 수가 줄의 인덱스
		line[count[0]] = 1;
		
		// 2번 사람부터 count[i]번 째 빈 칸이 본인 자리
		for(int i = 1; i < N; i++) {
			int cnt = 0;
			
			for(int j = 0; j < N; j++) {
				if(line[j] == 0) { // 빈 칸
					if(cnt == count[i]) { //본인 자리 찾음
						line[j] = i + 1;
						break;
					} else {
						cnt++;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(line[i]).append(" ");
		}
		
		System.out.print(sb);
	}

}