package com.ssafy.d1023;

import java.io.*;
import java.util.*;

public class BOJ_1766 {

	static int N,M;
	static int [] indegree;
	static ArrayList<ArrayList<Integer>> graph;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb  = new StringBuilder();
		
		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		M = Integer.parseInt(str.nextToken());

		indegree = new int[N+1];
		
		graph = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0;i<M;i++) {
			str = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(str.nextToken());
			int nxt = Integer.parseInt(str.nextToken());
			indegree[nxt]+=1;
			graph.get(pre).add(nxt);
		}
		
		
		for(int i=1;i<=N;i++) {
			if(indegree[i]==0) {
				pq.offer(i);
			}
		}

		while(!pq.isEmpty()) {
			int k = pq.poll();
			for(int i=0;i<graph.get(k).size();i++) {
				int t = graph.get(k).get(i);
				indegree[t]--;
				if(indegree[t]==0) {
					pq.offer(t);
				}				
			}
			sb.append(k).append(" ");
		}
		System.out.println(sb.toString());
	}
	
}
