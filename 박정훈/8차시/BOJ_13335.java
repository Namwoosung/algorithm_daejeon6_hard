import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13335 {

	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int w = Integer.parseInt(st.nextToken());
	    int L = Integer.parseInt(st.nextToken());
	    int[] weights = new int[n];
	    int time = 0;
	    List<int[]> onBridgeC = new ArrayList<>();
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i = 0; i < n; i++) {
	        weights[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    int onBridgeW = 0; // 다리 위 차들 무게 합
	    int completeCarIndex = -1; // 다를 건넌 차 인덱스
	    int nextCarIndex = 0; // 다음 대기 중인 차 인덱스
	    
	    while(completeCarIndex < n - 1) {
	        time++;
	        //다리 위 차들 한 칸 앞으로
	        for(int i = 0; i < onBridgeC.size(); i++) {
	            onBridgeC.get(i)[1]++;    
	            // 다리를 다 건넘
	            if(onBridgeC.get(i)[1] > w) {
	            	onBridgeW -= weights[onBridgeC.get(i)[0]];
	                onBridgeC.remove(i);
	                completeCarIndex++;
	                i--;
	            }
	        }

	        // 차 올릴 수 있는 지 검사
	        if(nextCarIndex < n && onBridgeW + weights[nextCarIndex] <= L && onBridgeC.size() <= w) { //다리에 올릴 수 있는 무게이고 올릴 다리 공간이 있다면
            	//차 올리기
                onBridgeC.add(new int[] {nextCarIndex, 1});
                onBridgeW += weights[nextCarIndex];
                nextCarIndex++;
	        }
	    }
	    
	    System.out.print(time);
	}
}