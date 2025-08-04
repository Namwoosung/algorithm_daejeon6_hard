import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] pos = new int[N];
        int answer = 0;
        //입력
        for(int i = 0; i< N; i++) {
        	pos[i] = Integer.parseInt(br.readLine());
        }
        
        //정렬
        Arrays.sort(pos);
        
        int left = 0;
        int right = pos[N-1];
        while(left <= right) {
        	int mid = (left + right) / 2;
        	int count = 1;// 설치한 개수, 0번 인덱스엔 설치
        	int index = 0; //설치한 인덱스
        	// mid 거리로 몇 개 설치할 수 있는 지 탐색
        	for(int i = 1; i < N; i++) {
        		if(pos[i] - pos[index] >= mid) {
        			index = i; // 마지막으로 설치한 인덱스
        			count++; //설치
        		}
        	}
        	
        	if(count >= C) { // 설치 개수가 충분하면 거리를 더 벌려서 탐색
        		left = mid + 1;
        		answer = mid;
        	} else { // 설치 개수가 미달이면 거리를 좁혀서 탐색
        		right = mid - 1;
        	}
        }
        
        System.out.print(answer);
    }

}