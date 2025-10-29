import java.io.*;
import java.util.*;

public class BOJ_1019
{
    static long[] number_count = new long[10];
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int start = 1;
        int digit = 1;
        
        while(start <= N){
            
            while(N % 10 != 9 && start <= N){
                count(N, digit);
                N--;
            }
            
            if(start > N) break;
            
            while(start % 10 != 0 && start <= N){
                count(start, digit);
                start++;
            }
            
            for(int i=0;i<10;i++){
                number_count[i] += ((N/10) - (start/10) + 1) * digit;
            }
            
            start /= 10;
            N /= 10;
            digit *= 10;
            
            
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<10;i++){
            sb.append(number_count[i]).append(" ");    
        }
        
        System.out.println(sb);
	}
	
	public static void count(int num, int digit){
	    
	    while(num > 0){
	        number_count[num%10] += digit;
	        num /= 10;
	    }
	}
}
