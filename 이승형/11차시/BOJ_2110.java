
import java.io.*;
import java.util.*;

/*
 * 공유기 C개 설치
 * 가장 인접한 두 공유기 사이의 거리를 최대로
 * 일정 개수를 설치, 공유기 사이 거리의 최대치
 * -> 거리를 탐색 (이분 탐색으로) + 일정 개수 설치 가능 체크 
 * 
 * */
public class BOJ_2110 {

	static int N;
	static int [] houses;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		int C = Integer.parseInt(str.nextToken());
		
		houses = new int[N];
		for(int i=0;i<N;i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houses);
		
		int start = 1;
		int end = houses[N-1] - houses[0]+1;
		
		while(start<end) {
			int mid = (start+end)/2;
			
			if(canInstall(mid) < C) {
				end = mid;
			}else {
				start = mid + 1;
			}
		}
		System.out.println(start-1);
	}
	
	static int canInstall(int dist) {
		int count = 1;
		int idx = 0;
		for(int i=idx+1;i<N;i++) {
			if(houses[i] - houses[idx] >= dist) {
				idx = i;
				count++;
			}
		}
		return count;
	}
	
	

}
