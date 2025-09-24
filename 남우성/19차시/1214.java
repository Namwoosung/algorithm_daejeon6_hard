import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		if(P < Q) {
			int temp = P;
			P = Q;
			Q = temp;
		}

		// 만약 검사하다가 중복된 결과가 한 번이라도 나오면, 이후에는 중복된 동작이 계속될 것
		Set<Integer> candiates = new HashSet<>();
		
		int value = (int)Math.ceil((double)D / P);
		int result = P * value;
		candiates.add(result);
		value--;
		while(value >= 0) {
			int remain = D - (P * value);
			int nValue = (int)Math.ceil((double)remain / Q);
			
			int now = P * value + Q * nValue;
			if(candiates.contains(now)) break;
			
			result = Math.min(result, now);
			candiates.add(now);
			
			if(result == D) break;
			
			value--;
		}
		
		System.out.print(result);
	}
}
