package com.ssafy.d1015;

import java.io.*;
import java.util.*;

public class BOJ_2632 {
	
	static int size, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input 입력 받음
		size = Integer.parseInt(br.readLine());
		
		StringTokenizer str = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		
		int [] pizzaA, pizzaB, prefixSumA, prefixSumB;
		pizzaA = new int[n];
		pizzaB = new int[m];
		
		for(int i=0;i<n;i++) { pizzaA[i]=Integer.parseInt(br.readLine());}
		for(int i=0;i<m;i++) { pizzaB[i]=Integer.parseInt(br.readLine());}
		
		// 각 피자의 누적합 생성 
		prefixSumA = new int[n+1];
		prefixSumB = new int[m+1];
		buildPrefixSum(pizzaA, prefixSumA);
		buildPrefixSum(pizzaB, prefixSumB);

		// 연속하는 피자조각의 합 중 정답이 될 수 있는 후보를 찾음
		HashMap<Integer, Integer> sumMapA, sumMapB;
		sumMapA = new HashMap<>();
		sumMapB = new HashMap<>();
		makePizzaSum(sumMapA, pizzaA, prefixSumA);
		makePizzaSum(sumMapB, pizzaB, prefixSumB);

		// 후보군 중, 가능한 경우의 수를 모두 찾음
		result = 0;
		countPizzaSum(sumMapA, sumMapB);
		
		System.out.println(result);
	}
	
	static void buildPrefixSum(int [] pizza, int [] prefixSum) {
		for(int i=0;i<pizza.length;i++) {
			prefixSum[i+1]=prefixSum[i]+pizza[i];
		}
	}
	
	static void makePizzaSum(HashMap<Integer,Integer> sumMap, int [] pizza, int [] prefixSum) {
		int len = pizza.length;
		
		// 아무조각 선택 하지 않은 경우와 전체를 선택한 경우
		sumMap.put(0,1);
		int last = prefixSum[len];
		if(last<=size) sumMap.put(last, sumMap.getOrDefault(last, 0)+1);
		
		for(int gap=0;gap<len-1;gap++) {
			for(int start=1;start<=len;start++) {
				int end = start+gap;
				int sumValue = 0;
				
				if(end<=len) {
					sumValue = prefixSum[end]-prefixSum[start-1];
				}else {
					sumValue = prefixSum[len]-prefixSum[start-1]+prefixSum[end-len];
				}
				
				if(sumValue<=size) sumMap.put(sumValue, sumMap.getOrDefault(sumValue, 0)+1);	
			}
		}
	}
	
	static void countPizzaSum(HashMap<Integer, Integer> sumMapA, HashMap<Integer, Integer> sumMapB) {
		for(int i=0;i<=size;i++) {
			int countA = sumMapA.getOrDefault(i, 0);
			int countB = sumMapB.getOrDefault(size-i, 0);	
			
			result+=countA*countB;
		}
	}
	
	
	
//	처음에 List에 저장해 풀이했던 코드 <-- 시간초과 
//  ------------------------------------------- main
//	ArrayList<Integer> sumListA, sumListB;
//	sumListA = new ArrayList<>();
//	sumListB = new ArrayList<>();
//	makePizzaSum(sumListA, pizzaA, prefixSumA);
//	makePizzaSum(sumListB, pizzaB, prefixSumB);
//	countPizzaSumList();
	
	/*  --------------------------------------- methods
	 *  처음에 counting을 List로 시도한 풀이 
	 */
	static void makePizzaSum(ArrayList<Integer> sumList, int [] pizza, int [] prefixSum) {
		int len = pizza.length;
		
		// 아무조각 선택 하지 않은 경우와 전체를 선택한 경우
		sumList.add(0);
		int last = prefixSum[len];
		if(last<=size) sumList.add(last);
		for(int gap=0;gap<len-1;gap++) {
			for(int start=1;start<=len;start++) {
				int end = start+gap;
				int sumValue = 0;
				
				if(end<=len) {
					sumValue = prefixSum[end]-prefixSum[start-1];
				}else {
					sumValue = prefixSum[len]-prefixSum[start-1]+prefixSum[end-len];
				}
				
				if(sumValue<=size) sumList.add(sumValue);
			}
		}
	}
	
	static void countPizzaSum(ArrayList<Integer> sumListA, ArrayList<Integer> sumListB) {
		for(int i=0;i<sumListA.size();i++) {
			int nowSize = sumListA.get(i);
			int otherSize = size - nowSize;
			
			if(otherSize==0) {
				result++;
			}else {
				for(int j=0;j<sumListB.size();j++) {
					if(sumListB.get(j)==otherSize) result++;
				}
			}
		}
	}
}
