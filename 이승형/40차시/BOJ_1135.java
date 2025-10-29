package com.ssafy.d1029;

import java.io.*;
import java.util.*;

/* 18:51
 * 
 */
public class BOJ_1135 {

	static int N;
	static ArrayList<Integer> [] nodes;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		nodes = new ArrayList[N];
		for(int i=0;i<N;i++) nodes[i] = new ArrayList<>();
		
		String [] line = br.readLine().split(" ");
		for(int i=1;i<line.length;i++) {
			nodes[Integer.parseInt(line[i])].add(i);
		}
		
		int result = getMin(0);
		
		System.out.println(result);
	}

	static int getMin(int idx) {
		int nowSize = nodes[idx].size();
		if(nowSize==0) return 0;
		
		int [] times = new int[nowSize];
		for(int i=0;i<nowSize;i++) {
			times[i] = getMin(nodes[idx].get(i));
		}
		
		Arrays.sort(times);
		
		int maxCount = Integer.MIN_VALUE;
		for(int i=1;i<=nowSize;i++) {
			maxCount = Math.max(maxCount, times[nowSize-i]+i);
		}
		return maxCount;
	}
}
