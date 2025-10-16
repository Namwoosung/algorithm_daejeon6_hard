import java.io.*;
import java.util.*;

public class BOJ_2632 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int target = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // A 피자 조각 수
        int n = Integer.parseInt(st.nextToken()); // B 피자 조각 수
        
        int[] A = new int[m];
        int[] B = new int[n];
        
        for (int i = 0; i < m; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(br.readLine());
        }
        
        // 각 피자에서 만들 수 있는 모든 합의 경우의 수 계산
        Map<Integer, Integer> mapA = getPizzaSums(A, target);
        Map<Integer, Integer> mapB = getPizzaSums(B, target);
        
        int result = 0;
        
        // A만 사용
        result += mapA.getOrDefault(target, 0);
        
        // B만 사용
        result += mapB.getOrDefault(target, 0);
        
        // A와 B 혼합
        for (int sumA : mapA.keySet()) {
            int sumB = target - sumA;
            result += mapA.get(sumA) * mapB.getOrDefault(sumB, 0);
        }
        
        System.out.println(result);
    }
    
    static Map<Integer, Integer> getPizzaSums(int[] pizza, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = pizza.length;
        
        // 전체 합 계산
        int totalSum = 0;
        for (int val : pizza) {
            totalSum += val;
        }
        
        // 전체 피자 사용 (1가지만 존재)
        if (totalSum <= target) {
            map.put(totalSum, 1);
        }
        
        // 부분 사용
        for (int len = 1; len < n; len++) {
            for (int start = 0; start < n; start++) {
                int sum = 0;
                for (int i = 0; i < len; i++) {
                    sum += pizza[(start + i) % n];
                }
                if (sum <= target) {
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        
        return map;
    }
}