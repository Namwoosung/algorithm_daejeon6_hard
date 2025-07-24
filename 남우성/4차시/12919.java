import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// 역순으로 T -> S 가 가능한지를 검사
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		
		List<String> stores = new LinkedList<>(); // 검사할 문자열들을 저장해 놓을 저장소, add와 remove를 많이 할거라 LinkedList를 사용
		stores.add(T);
		
		boolean found = false;
		while(!stores.isEmpty() && !found) {
			//각 시행마다 전체 String을 검사후 set에 넣어두고 한번에 stores에 update <- 동일한 문자열을 중복검사 하지 않기 위해 중간에 set을 한 번 거치기 위함
			Set<String> temp = new TreeSet<>();
			
			for(int i = 0; i < stores.size(); i++) {
				String item = stores.remove(0);
				if(S.equals(item)) {
					found = true;
					break;
				}
				
				char[] now = item.toCharArray();
				
				if(now[now.length - 1] == 'A') { //마지막에 A인 경우 A를 제외하고 set에 추가
					if(now.length > S.length()) {// 길이가 더 길 경우만 추가
						char[] tempCharArr = new char[now.length - 1];
						for(int j = 0; j < now.length - 1; j++) {
							tempCharArr[j] = now[j];
						}
						
						temp.add(String.valueOf(tempCharArr)); 
					}
				}
				
				if(now[0] == 'B'){ // 처음이 B인 경우, 뒤집고 마지막 B를 제외하고 추가
					if(now.length > S.length()) {
						char[] tempCharArr = new char[now.length - 1];
						for(int j = 0; j < now.length - 1; j++) {
							tempCharArr[j] = now[now.length - j - 1];
						}
						temp.add(String.valueOf(tempCharArr)); 
					}
				}
			}
			
			stores.addAll(temp);
		}
		
		if(found) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
}


/**
찾아본 더 깔끔한 코드
시간 복잡도는 동일, 전체적인 로직이 더 깔끔

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();

		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();

		queue.offer(T);
		visited.add(T);

		while (!queue.isEmpty()) {
			String current = queue.poll();

			if (current.equals(S)) {
				System.out.println(1);
				return;
			}

			// 길이가 S보다 길 때만 줄일 수 있음
			if (current.length() <= S.length()) continue;

			// case 1: 뒤 A 제거
			if (current.endsWith("A")) {
				String next = current.substring(0, current.length() - 1);
				if (visited.add(next)) queue.offer(next);
			}

			// case 2: 앞 B 제거 후 뒤집기
			if (current.startsWith("B")) {
				StringBuilder sb = new StringBuilder(current.substring(1));
				String next = sb.reverse().toString();
				if (visited.add(next)) queue.offer(next);
			}
		}

		System.out.println(0);
	}
}
 */