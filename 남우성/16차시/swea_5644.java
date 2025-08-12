import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	
	// 배열로 하려다가 그냥 시간도 널널하고, 가독성 좋은 게 더 좋을 것 같아서 class로 관리
	static class Charger implements Comparable<Charger>{
		int x;
		int y;
		int range;
		int performace;
		public Charger(int y, int x, int range, int performace) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.performace = performace;
		}
		@Override
		public int compareTo(Charger other) {
			return Integer.compare(other.performace, this.performace);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken()); int A = Integer.parseInt(st.nextToken());
			int[] mA = new int[M+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < M+1; i++) {
				mA[i] = Integer.parseInt(st.nextToken());
			}
			int[] mB = new int[M+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < M+1; i++) {
				mB[i] = Integer.parseInt(st.nextToken());
			}
			
			Charger[] chargers = new Charger[A];
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				chargers[i] = new Charger(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 각 시행을 시뮬레이션
			int[] nA = {1,1};
			int[] nB = {10,10};
			int result = 0;
			PriorityQueue<Charger> cA = new PriorityQueue<>();
			PriorityQueue<Charger> cB = new PriorityQueue<>();
			for(int T = 0; T < M+1; T++) {
				nA[0] += dx[mA[T]]; nA[1] += dy[mA[T]];
				nB[0] += dx[mB[T]]; nB[1] += dy[mB[T]];
				
				cA.clear();
				cB.clear();

				for(int i = 0; i < A; i++) {
					if(Math.abs(nA[0] - chargers[i].x) + Math.abs(nA[1] - chargers[i].y) <= chargers[i].range) cA.add(chargers[i]);
					if(Math.abs(nB[0] - chargers[i].x) + Math.abs(nB[1] - chargers[i].y) <= chargers[i].range) cB.add(chargers[i]);
				}
				
				// 최대값 추출
				Charger ncA = cA.poll();
				Charger ncB = cB.poll();
				
				if(ncA != null && ncB != null) {
					if(ncA.equals(ncB)) { // 최대값이 같은 경우
						int tempScore = ncA.performace; // 하나를 나눠가지는 경우
						Charger nncA = cA.poll();
						Charger nncB = cB.poll();
						
						if(nncA != null) {
							tempScore = Math.max(tempScore, nncA.performace + ncB.performace); //a가 양보
						}
						if(nncB != null) {
							tempScore = Math.max(tempScore, nncB.performace + ncA.performace); //b가 양보
						}
						result += tempScore;
					}else { //최대값이 다른 경우
						result += ncA.performace;
						result += ncB.performace;

					}
				}else if(ncA != null) {
					result += ncA.performace;
				}else if(ncB != null) {
					result += ncB.performace;
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
