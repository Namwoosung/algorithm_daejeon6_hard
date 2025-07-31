import java.util.*;
import java.io.*;

public class BOJ_13335 {
    static class Truck{
        int weight;
        int time;

        Truck(int weight, int time){
            this.weight = weight;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] weights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++){
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Truck> queue = new LinkedList<>();    // 트럭이 올라갈 다리
        int index = 0;                              //  다리에 올라갈 트럭의 인덱스
        int time = 0;                               // 총 진행 시간
        int total = 0;                             // 다리에 올라간 트럭들의 무게 합

        while(index < n){
            time++;                                 // 1초씩 진행
            if(!queue.isEmpty()){
                Truck first = queue.peek();
                if(time - first.time >= w){         // 다리 가장 앞 트럭의 시간 비교 후 빼기
                    total -= first.weight;
                    queue.poll();
                }
            }

            if (queue.size() >= w) continue;         // 다리가 꽉 찼다면 continue

            if(total + weights[index] <= L){
                queue.add(new Truck(weights[index], time));      // 다음 트럭이 들어 올 수 있으면 넣기
                total += weights[index++];
            }

        }


        // 다리에 올라가 있는 마지막 트럭만 건너면 끝
        while(queue.size() > 1){
            queue.poll();
        }

        System.out.println(queue.poll().time + w);
    }
}
