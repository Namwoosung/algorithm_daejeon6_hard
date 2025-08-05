
import java.util.*;
import java.io.*;
/* 
 * 4 3 3 5
 *  7 6 8
 * 
 * 파일의 순서는 중요하지 않음
 * pq에 모두 넣고, 작은 수 두개 꺼내서 합치고, pq에 add
 * 
 * */
public class BOJ_13975 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			long [] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
			for(long j : arr) {
				pq.add(j);
			}
			sb.append(doFileSum(arr, pq)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static long doFileSum(long [] arr, PriorityQueue<Long> pq) {
		long result = 0;
		while(pq.size()>1) {
			long smallFile1 = pq.poll();
			long smallFile2 = pq.poll();
			long fileSum = smallFile1+smallFile2;
			result+=fileSum;
			pq.add(fileSum);
		}
		return result;
	}

}
