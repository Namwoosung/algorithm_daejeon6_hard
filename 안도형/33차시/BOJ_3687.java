package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_3687 {
    static String[] arrMax = {"0","0","1","7","4","5","9","8"};
    static String[] arrMin = {"0","0","1","7","4","2","0","8"};
    static String[] dpMin = new String[101];
    static String[] dpMax = new String[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        dpMax[2] = "1";
        dpMax[3] = "7";
        for (int i = 4; i <= 100; i++) 
            dpMax[i] = dpMax[i - 2] + "1";
        

        for (int i = 2; i <= 7; i++)
        	dpMin[i] = arrMin[i];
        dpMin[6] = "6";
        dpMin[8] = "10";

        for (int i = 9; i <= 100; i++) {
            dpMin[i] = null;
            for (int j = 2; j <= 7; j++) {
                String candidate = dpMin[i - j] + arrMin[j];
                
                if (dpMin[i] == null || compareNumericStrings(candidate, dpMin[i])) {
                    dpMin[i] = candidate;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            sb.append(dpMin[a]).append(" ").append(dpMax[a]).append("\n");
        }
        System.out.print(sb);
    }

    static boolean compareNumericStrings(String a, String b) {
        if (a.length() != b.length()) return a.length() < b.length();
        return a.compareTo(b) < 0;
    }
}
