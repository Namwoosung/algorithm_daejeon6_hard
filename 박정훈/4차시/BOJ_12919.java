import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {
	static boolean isFind;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder S = new StringBuilder(br.readLine());
		StringBuilder T = new StringBuilder(br.readLine());
		StringBuilder temp = new StringBuilder(T);
		
		// T의 첫 글자가 A, 마지막 글자가 A인 경우
		if(T.charAt(0) == 'A' && T.charAt(T.length() - 1) == 'A') {
			minusA(S, temp);
		} else if(T.charAt(0) == 'B' && T.charAt(T.length() - 1) == 'B') { //T의 첫 글자가 B, 마지막 글자가 B인 경우
			minusB(S, temp);
		} else if(T.charAt(0) == 'B' && T.charAt(T.length() - 1) == 'A') { //T의 첫 글자가 B, 마지막 글자가 A인 경우
			minusA(S, temp);
			minusB(S, T);
		} // 첫 글자가 A, 마지막 글자가 B인 경우는 만들 수 없음
		
		System.out.print(isFind ? 1 : 0);
	}
	
	public static void minusA(StringBuilder S, StringBuilder T) {
		if(!isFind && S.length() < T.length()) { // 아직 발견 못 한 경우에만
			T.deleteCharAt(T.length() - 1); // 마지막 글자 지우기
			if(S.length() == T.length() && S.toString().equals(T.toString())) { // 비교
				isFind = true;
				return;
			} else {
				StringBuilder temp = new StringBuilder(T);
				// T의 첫 글자가 A, 마지막 글자가 A인 경우
				if(T.charAt(0) == 'A' && T.charAt(T.length() - 1) == 'A') {
					minusA(S, temp);
				} else if(T.charAt(0) == 'B' && T.charAt(T.length() - 1) == 'B') { //T의 첫 글자가 B, 마지막 글자가 B인 경우
					minusB(S, temp);
				} else if(T.charAt(0) == 'B' && T.charAt(T.length() - 1) == 'A') { //T의 첫 글자가 B, 마지막 글자가 A인 경우
					minusA(S, temp);
					minusB(S, T);
				} else { // 첫 글자가 A, 마지막 글자가 B인 경우는 만들 수 없음
					return;
				}
			}
		}
	}
	
	public static void minusB(StringBuilder S, StringBuilder T) {
		if(!isFind && S.length() < T.length()) { // 아직 발견 못 한 경우에만
			T.reverse(); // 문자열 뒤집기
			T.deleteCharAt(T.length() - 1); // 마지막 글자 지우기
			if(S.length() == T.length() && S.toString().equals(T.toString())) { // 비교
				isFind = true;
				return;
			} else {
				StringBuilder temp = new StringBuilder(T);
				// T의 첫 글자가 A, 마지막 글자가 A인 경우
				if(T.charAt(0) == 'A' && T.charAt(T.length() - 1) == 'A') {
					minusA(S, temp);
				} else if(T.charAt(0) == 'B' && T.charAt(T.length() - 1) == 'B') { //T의 첫 글자가 B, 마지막 글자가 B인 경우
					minusB(S, temp);
				} else if(T.charAt(0) == 'B' && T.charAt(T.length() - 1) == 'A') { //T의 첫 글자가 B, 마지막 글자가 A인 경우
					minusA(S, temp);
					minusB(S, T);
				} else { // 첫 글자가 A, 마지막 글자가 B인 경우는 만들 수 없음
					return;
				}
			}
		}
	}
}