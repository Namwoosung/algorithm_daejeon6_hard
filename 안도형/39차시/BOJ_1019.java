package algo_hard;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1019 {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] numCount = new int[10];
        int digit = 1;

        while (N / digit > 0) {
        	int nextDigitNum = N / (digit * 10);
        	int currDigitNum = (N / digit) % 10;
        	int low = N % digit;

            for(int i = 0; i < 10; i++)
        		numCount[i] += nextDigitNum * digit;
            
            for(int i = 0; i < currDigitNum; i++)
            	numCount[i] += digit;

        	numCount[currDigitNum] += (low + 1);
        	numCount[0] -= digit;
        	
            digit *= 10; 
        }
     
        for(int i = 0; i < 10; i++) {
    		System.out.print(numCount[i] + " ");
    	}
	}
}
