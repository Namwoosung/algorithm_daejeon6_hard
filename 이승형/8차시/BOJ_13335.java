
import java.util.*;
import java.io.*;

public class BOJ_13335 {
	static int N, bridgeLength, bridgeMaximumWeight;
	static int [] trucks;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Input
		StringTokenizer str = new StringTokenizer(br.readLine());
		N = Integer.parseInt(str.nextToken());
		bridgeLength = Integer.parseInt(str.nextToken());
		bridgeMaximumWeight = Integer.parseInt(str.nextToken());
		trucks = new int[N];
		str = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			trucks[i] = Integer.parseInt(str.nextToken());
		}
		
		// Solve
		// 큐, 트럭 인덱스, 현재 총 무게, 시간 
		Queue<Truck> bridge = new LinkedList<>();
		int truckIdx = 0;
		int weightSum = 0;
		int time = 0;
		
		// 첫 트럭 넣기
		Truck firstTruck = new Truck(bridgeLength, trucks[truckIdx++]);
		bridge.offer(firstTruck);
		weightSum += firstTruck.weight;
		
		while(!bridge.isEmpty()) {
			time++;
			
			// empty 아닌 경우 peek 시간==0 -> 큐에서 삭제
			if(bridge.peek().remainingTime==0) {
				Truck passedTruck = bridge.poll();
				weightSum -= passedTruck.weight;
			}
			
			// 현재 총 무게 확인, 출발 가능시 추가 
			while(truckIdx<N && bridgeMaximumWeight >= weightSum+trucks[truckIdx]) {
				Truck enterTruck = new Truck(bridgeLength, trucks[truckIdx++]);
				bridge.offer(enterTruck);
				weightSum += enterTruck.weight;
				break;
			}
			
			// 전체 큐 내 시간 감소 
			Iterator<Truck> it = bridge.iterator();
			while(it.hasNext()) {
				it.next().decreseTime();
			}
		}
		System.out.println(time);
	}
	
	static class Truck {
		int remainingTime;
		int weight;
		public Truck(int remainingTime, int weight) {
			this.remainingTime = remainingTime;
			this.weight = weight;
		}
		
		public void decreseTime() {
			if(this.remainingTime>0) {
				this.remainingTime -= 1;
			}
		}
	}
}
