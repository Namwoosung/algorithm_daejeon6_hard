import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ_1202 {
	
	static class Gemstone {
		int weight, value;
		
		public Gemstone(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    
	    // 보석 정보
	    Gemstone[] gemstones = new Gemstone[N];
	    for(int i = 0; i < N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int w = Integer.parseInt(st.nextToken());
	    	int v = Integer.parseInt(st.nextToken());
	    	
	    	gemstones[i] = new Gemstone(w, v);
	    }
	    
	    // 무게 오름차순
	    Arrays.sort(gemstones, (a, b) -> a.weight - b.weight);
	    
	    // 가방 정보
	    int[] bag = new int[K];
	    for(int i = 0; i < K; i++) {
	    	bag[i] = Integer.parseInt(br.readLine());
	    }
	    
	    // 무게 오름차순
	    Arrays.sort(bag);
	    
	    // 가치 내림차순
	    PriorityQueue<Integer> pque = new PriorityQueue<>((a, b) -> b - a);
	    long sum = 0;
	    int index = 0;
	    
	    for(int b : bag) {
	    	// 현재 가방에 들어갈 수 있는 보석들 큐에 삽입
	    	while(index < N && gemstones[index].weight <= b) {
	    		pque.offer(gemstones[index++].value);
	    	}
	    	
	    	// 가장 비싼 거 훔치기
	    	if(!pque.isEmpty()) {
	    		sum += pque.poll();
	    	}
	    }

	    
	    System.out.println(sum);
	}	

}
