package codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class SWEA_5653 {
 
    static class Cell implements Comparable<Cell>{
        int x, y;          // 좌표
        int energy;       // 생명력
        boolean active;  // 비활성, 활성 상태
        int t;            // 남은 시간
 
        Cell(int x, int y, int energy, boolean status, int t){
            this.x = x;
            this.y = y;
            this.energy = energy;
            this.active = status;
            this.t = t;
        }
 
        @Override
        public int compareTo(Cell c) {
        	//둘 다 활성 상태라면 생명력 기준 내림차순
            if (c.active && this.active){
                return c.energy - this.energy;
            }
            //둘 다 비활성 상태라면
            if (!this.active && !c.active) {
            	// 남은 시간도 같다면 생명력 기준 내림차순
                if (this.t == c.t) {
                    return c.energy - this.energy;
                }
                // 아니라면 남은 시간 기준 오름차순
                return this.t - c.t;
            }
 
            // 활성 상태를 우선으로
            return this.active ? -1 : 1;
        }
    }
 
    static PriorityQueue<Cell> pq;
    static HashSet<Integer> set;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
 
        for(int testcase=1;testcase<=T;testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
 
            pq = new PriorityQueue<>();
            set = new HashSet<>();
 
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++) {
                    int now = Integer.parseInt(st.nextToken());
                    if(now > 0) {
                        pq.add(new Cell(i, j, now, false, now));
                        set.add(i * 1000 + j); // 좌표 중복 막기
                    }
                }
            }
 
            simul(K);
            sb.append('#').append(testcase).append(' ');
            sb.append(pq.size());
            sb.append('\n');
 
        }
        System.out.println(sb);
    }
 
    public static void simul(int k) {
        ArrayList<Cell> list;
 
        for(int i=1;i<=k;i++) {
            list = new ArrayList<>();
 
            while(!pq.isEmpty()) {
                Cell now = pq.poll();
                
                // 비활성 상태
                if(!now.active) {
                    now.t--;
                    //활성화 시키기
                    if(now.t == 0) {
                        now.t = now.energy;
                        now.active = true;
                    }
                }
                else {
                	// 번식은 1번만
                    if(now.t == now.energy) {
                        for(int dir=0;dir<4;dir++) {
                            int nextX = now.x + dx[dir];
                            int nextY = now.y + dy[dir];
                            if(set.contains(nextX * 1000 + nextY)) continue;
                            set.add(nextX * 1000 + nextY);
                            list.add(new Cell(nextX, nextY, now.energy, false, now.energy));
                        }
 
 
                    }
                    now.t--;
 
                }
 
                if(now.t > 0) {
                    list.add(now);
                }
            }
 
            pq.addAll(list);
        }
 
    }
 
}