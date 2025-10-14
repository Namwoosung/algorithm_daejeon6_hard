package com.ssafy.d1015;

import java.io.*;
import java.util.*;

public class BOOJ_14003 {

	static int N;
	static int [] nums, arr, preIdx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		arr = new int[N];
		preIdx = new int[N];
		
		String [] tmp = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(tmp[i]);
		}
		
		// nums의 index 마다 arr에서의 위치를 찾음
		// 못 찾은 경우 -> java binarySearch는 lowerbound
		// -1 * (idx+1) 위치에 해당 값 저장
		// 만약 idx가 현재 배열 크기와 같다면 -> 맨 뒤에 추가되었다면 size++
		int size = 0;
		for(int i=0;i<N;i++) {
			int k = nums[i];
			int idx = Arrays.binarySearch(arr, 0, size, k);
			if(idx<0) idx = -1*(idx+1);
			arr[idx] = k;
			preIdx[i]=idx;
			if(idx==size) size++;
		}
		sb.append(size).append("\n");
		
		// 역추적 로직
		// 왜 뒤에서 부터 탐색할까?
		//  0  6  6  5  6  처럼 
		// [0, 1, 1, 1, 2]
		// 동일 preIdx에 num 값이 작은 경우가 뒤에 나오게 된 경우를 고려한다.
		int i = N-1;
		int idx = size-1;
		Stack<Integer> stack = new Stack<>();
		
		while(idx>=0 && i>=0) {
			if(preIdx[i]==idx) {
				stack.push(nums[i]);
				idx--;
			}
			i--;
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb.toString());
	}

}
