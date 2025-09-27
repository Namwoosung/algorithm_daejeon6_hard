import java.io.*;
import java.util.*;

public class BOJ_1162_failed {
    
    static class Edge {
        int to, cost;
        
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    static class State {
        int node, totalCost, usedPavedCount;
        PriorityQueue<Integer> usedNormalRoads; // 사용한 일반도로들 (내림차순)
        
        State(int node, int totalCost, int usedPavedCount) {
            this.node = node;
            this.totalCost = totalCost;
            this.usedPavedCount = usedPavedCount;
            this.usedNormalRoads = new PriorityQueue<>((a, b) -> b - a);
        }
        
        State(int node, int totalCost, int usedPavedCount, PriorityQueue<Integer> usedRoads) {
            this.node = node;
            this.totalCost = totalCost;
            this.usedPavedCount = usedPavedCount;
            this.usedNormalRoads = new PriorityQueue<>((a, b) -> b - a);
            this.usedNormalRoads.addAll(usedRoads);
        }
    }
    
    static List<Edge>[] graph;
    static int[] dist;
    static int N, K;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 비용 큰 순으로 정렬
        PriorityQueue<int[]> pque = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 간선 입력받아서 큐에 넣기
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pque.offer(new int[]{a, b, v});
        }
        
        // 큰 비용부터 K개는 포장
        int k = 0;
        while(!pque.isEmpty()) {
            int[] road = pque.poll();
            int cost = (++k <= K) ? 0 : road[2];
            
            graph[road[0]].add(new Edge(road[1], cost));
            graph[road[1]].add(new Edge(road[0], cost));
        }
        
        State result = dijkstra();
        
        int finalCost = result.totalCost; // 현재 경로 비용
        int remaining = K - result.usedPavedCount; // 남은 포장 횟수
        
        // 남은 포장 횟수를 사용해서 큰 비용의 도로들 0으로
        while(!result.usedNormalRoads.isEmpty() && remaining > 0) {
            finalCost -= result.usedNormalRoads.poll();
            remaining--;
        }
        
        System.out.println(finalCost);
    }
    
    public static State dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.totalCost - b.totalCost);
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new State(1, 0, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()) {
            State current = pq.poll();
            
            if(current.node == N) return current;
            if(current.totalCost > dist[current.node]) continue;
            
            for(Edge edge : graph[current.node]) {
                int nextCost = current.totalCost + edge.cost;
                
                if(nextCost < dist[edge.to]) {
                    dist[edge.to] = nextCost;
                    State next = new State(edge.to, nextCost, current.usedPavedCount, current.usedNormalRoads);
                    
                    if(edge.cost > 0) { // 일반도로 사용
                        next.usedNormalRoads.offer(edge.cost);
                    } else { // 포장도로 사용
                        next.usedPavedCount++;
                    }
                    
                    pq.offer(next);
                }
            }
        }
        return null;
    }
}