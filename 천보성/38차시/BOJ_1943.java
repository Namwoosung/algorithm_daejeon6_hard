import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1943 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i=0;i<3;i++){
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][2];
            int total = 0;

            for (int j=0;j<N;j++){
                st = new StringTokenizer(br.readLine());
                arr[j][0] = Integer.parseInt(st.nextToken());
                arr[j][1] = Integer.parseInt(st.nextToken());
                total += arr[j][0] * arr[j][1];
            }
            if (check(arr,total)) sb.append(1);
            else sb.append(0);
            sb.append('\n');
        }
        System.out.println(sb);
    }
	
	
    public static boolean check(int[][] arr, int total){
        if (total%2 == 1) return false;

        total /= 2;
        boolean[] dp = new boolean[total+1];
        
        dp[0] = true;
        
        for (int i=0;i<arr.length;i++){

            int[] count = new int[total + 1];
            
            for (int j=arr[i][0];j<=total;j++){
                
                if (!dp[j-arr[i][0]]) continue;
                
                
                if (!dp[j] && count[j-arr[i][0]] < arr[i][1]) {                	
                	dp[j] = true; 
                	count[j] = count[j-arr[i][0]] + 1;
                }
            }
        }
        return dp[total];
    }
}
