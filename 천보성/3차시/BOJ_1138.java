
import java.util.*;
import java.io.*;

public class BOJ_1138 {
	
	/*
	 * 키는 큰 친구들부터 역순으로 위치 설정 
	 * 4 -> 3 (1) 리스트 사이즈 비교 : 사이즈랑 people[i] 같다면 오른쪽 삽입
	 * 2 (1)   사이즈보다 작다면 index = people[i]  ->  1
	 * 1 (2)   index -> 2
	 */
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] people = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(N);
		
		for(int i=N-1;i>0;i--) {
			if(people[i] < list.size()) {
				list.add(people[i], i);
			}
			else if(people[i] == list.size()) {
				list.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int h : list) {
			sb.append(h).append(' ');
		}
		
		System.out.println(sb);
	}
}
