import java.io.*;
import java.util.*;

public class BOJ_3687 {
	static int[] needed = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        String[] maxDp = new String[101]; // 최대 성냥개비
        maxDp[2] = "1";
        maxDp[3] = "7";
        // i 개의 성냥개비로 만들 수 있는 가장 큰 수 1 또는 7에서 1을 이어붙이기
        for(int i = 4; i <= 100; i++) {
        	maxDp[i] = maxDp[i - 2] + 1;
        }
        
        String[] minDp = new String[101]; // 최소 성냥개비
        Arrays.fill(minDp, "99999999999999999999");
        minDp[2] = "1";
        minDp[3] = "7";
        minDp[4] = "4";
        minDp[5] = "2";
        minDp[6] = "6";
        minDp[7] = "8";  
        
        // 최소 dp
        for(int i = 8; i <= 100; i++) {
        	for(int j = 0; j <= 9; j++) { // 0~9까지
        		if(i - needed[j] < 2) continue;
        		String prev = minDp[i - needed[j]];
        		minDp[i] = minString(minDp[i], prev + j);
        	}
        }
        
        for(int tc = 0; tc < T; tc++) {
        	int num = Integer.parseInt(br.readLine()); // 성냥개비 개수
        	answer.append(minDp[num]).append(" ").append(maxDp[num]).append("\n");
        }
        
        System.out.print(answer);
    }
    
    public static String minString(String s1, String s2) {
    	if(s1.length() < s2.length()) return s1; // s1이 더 짧음
    	else if(s1.length() > s2.length()) return s2; // s2 가 더 짧음
    	else return s1.compareTo(s2) < 0 ? s1 : s2; // s1과 s2 길이가 같음
    }
}