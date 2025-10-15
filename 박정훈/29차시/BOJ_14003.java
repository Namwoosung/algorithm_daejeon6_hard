import java.io.*;
import java.util.*;

public class BOJ_14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> lis = new ArrayList<>();
        int[] index = new int[N]; // 각 원소가 lis의 어느 위치에 들어갔는지
        
        for(int i = 0; i < N; i++) {
            int num = arr[i];
            
            // 이분 탐색으로 들어갈 위치 찾기
            int pos = binarySearch(lis, num);
            
            if(pos == lis.size()) {
                lis.add(num);
            } else {
                lis.set(pos, num);
            }
            
            index[i] = pos;
        }
        
        // 역추적
        int length = lis.size();
        int[] result = new int[length];
        int idx = length - 1;
        
        for(int i = N - 1; i >= 0; i--) {
            if(index[i] == idx) {
                result[idx] = arr[i];
                idx--;
            }
        }
        
        StringBuilder answer = new StringBuilder();
        answer.append(length).append("\n");
        for(int num : result) {
            answer.append(num).append(" ");
        }
        System.out.print(answer);
    }
    
    public static int binarySearch(List<Integer> list, int num) {
        int left = 0;
        int right = list.size() - 1;
        int result = list.size(); // 못 찾으면 맨 뒤
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(list.get(mid) >= num) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
}