import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int[] sortedArr;
	static long result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sortedArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		result = 0;
		
		mergeSort(0, N-1);
		
		
		
		System.out.println(result);
		
	}
	
	static int[] mergeSort(int start, int end) {
		if(start == end) {
			return new int[] {arr[start]};
		}
		
		int mid = (start + end) / 2;
		int[] arr1 = mergeSort(start, mid);
		int[] arr2 = mergeSort(mid + 1, end);
		int arrSize1 = mid - start + 1;
		int arrSize2 = end - (mid + 1) + 1;
		int p1 = 0;
		int p2 = 0;
		
		int[] now = new int[(end - start + 1)];
		int index = 0;
		while(p1 < arrSize1 && p2 < arrSize2) {
			if(arr1[p1] <= arr2[p2]) {
				now[index] = arr1[p1];
				p1++;
			}else {
				now[index] = arr2[p2];
				p2++;
				
				result += (arrSize1 - p1);
			}
			
			index++;
		}
		
		while(p1 < arrSize1) {
			now[index] = arr1[p1];
			p1++;
			index++;
		}
		while(p2 < arrSize2) {
			now[index] = arr2[p2];
			p2++;
			index++;
		}
		
		return now;
	}
}