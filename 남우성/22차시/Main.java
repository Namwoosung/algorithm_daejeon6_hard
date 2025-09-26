
// MyCode. 아래에 정답코드도 따로 있는데, 뭐가 틀린건지 모르겠네요... 전 오히려 제 코드가 더 효율적이라고 생각했는데..
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class Candiate implements Comparable<Candiate> {
        int node;
        long weight;
        int change;

        public Candiate(int node, long weight, int change) {
            this.node = node;
            this.weight = weight;
            this.change = change;
        }

        // 가중치가 낮은 순, 동일하면 기회가 많은 순
        @Override
        public int compareTo(Candiate o) {
            if (this.weight != o.weight) {
                return Long.compare(this.weight, o.weight);
            } else {
                return o.change - this.change;
            }
        }
    }

    static List<Edge>[] graph;
    static long[][] dist;
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new long[N + 1][K + 1]; // 백트래킹을 위함
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();

            for (int j = 0; j <= K; j++) {
                dist[i][j] = Long.MAX_VALUE;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        PriorityQueue<Candiate> pq = new PriorityQueue<>();
        pq.add(new Candiate(1, 0, K));
        long result = 0;
        while (!pq.isEmpty()) {
            Candiate now = pq.poll();

            if (now.node == N) {
                result = now.weight;
                break;
            }

            if (!isCandiate(now.node, now.weight, now.change))
                continue;
            dist[now.node][now.change] = now.weight;

            for (int i = 0; i < graph[now.node].size(); i++) {
                Edge next = graph[now.node].get(i);

                // 기회를 쓰지 않은 경우
                if (isCandiate(next.node, now.weight + next.weight, now.change)) {
                    pq.add(new Candiate(next.node, now.weight + next.weight, now.change));
                    dist[next.node][now.change] = now.weight + next.weight;
                }

                // 기회를 쓰는 경우
                if (now.change > 0) {
                    if (isCandiate(next.node, now.weight, now.change - 1)) {
                        pq.add(new Candiate(next.node, now.weight, now.change - 1));
                        dist[next.node][now.change - 1] = now.weight;
                    }
                }
            }
        }

        System.out.println(result);
    }

    // 가능한 후보인지 탐색
    static boolean isCandiate(int node, long weight, int chance) {
        // 현재 기회 기준 더 최적이 검사되었던 경우
        if (dist[node][chance] < weight)
            return false;

        // 현재 기회보다 많은데, 비용이 현재 이하인 경우
        for (int i = chance + 1; i <= K; i++) {
            if (dist[node][i] <= weight)
                return false;
        }
        return true;
    }
}

// 정답 코드
// import java.io.*;
// import java.util.*;

// public class Main {
// static class Edge {
// int to;
// int w;
// Edge(int to, int w) { this.to = to; this.w = w; }
// }
// static class Candidate implements Comparable<Candidate> {
// int node;
// int left; // 남은 포장 횟수
// long dist; // 누적 거리
// Candidate(int node, int left, long dist) {
// this.node = node; this.left = left; this.dist = dist;
// }
// @Override public int compareTo(Candidate o) {
// return Long.compare(this.dist, o.dist); // 가중치만 비교
// }
// }

// static List<Edge>[] g;
// static long[][] dist;
// static int N, M, K;

// public static void main(String[] args) throws Exception {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// StringTokenizer st = new StringTokenizer(br.readLine());
// N = Integer.parseInt(st.nextToken());
// M = Integer.parseInt(st.nextToken());
// K = Integer.parseInt(st.nextToken());

// g = new ArrayList[N + 1];
// for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

// for (int i = 0; i < M; i++) {
// st = new StringTokenizer(br.readLine());
// int a = Integer.parseInt(st.nextToken());
// int b = Integer.parseInt(st.nextToken());
// int c = Integer.parseInt(st.nextToken());
// g[a].add(new Edge(b, c));
// g[b].add(new Edge(a, c));
// }

// // dist[node][left] = 그 상태로의 최소 거리
// dist = new long[N + 1][K + 1];
// for (int i = 1; i <= N; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
// dist[1][K] = 0L;

// PriorityQueue<Candidate> pq = new PriorityQueue<>();
// pq.add(new Candidate(1, K, 0L));

// while (!pq.isEmpty()) {
// Candidate cur = pq.poll();

// // 이미 더 좋은 경로가 있으면 스킵
// if (cur.dist != dist[cur.node][cur.left]) continue;

// // 첫 pop된 N이 전역 최소
// if (cur.node == N) {
// System.out.println(cur.dist);
// return;
// }

// for (Edge e : g[cur.node]) {
// // 1) 포장 안 함
// long nd = cur.dist + e.w;
// if (nd < dist[e.to][cur.left]) {
// dist[e.to][cur.left] = nd;
// pq.add(new Candidate(e.to, cur.left, nd));
// }
// // 2) 포장 해서 0 비용으로 지나감
// if (cur.left > 0) {
// if (cur.dist < dist[e.to][cur.left - 1]) {
// dist[e.to][cur.left - 1] = cur.dist;
// pq.add(new Candidate(e.to, cur.left - 1, cur.dist));
// }
// }
// }
// }

// // 이 문제에선 항상 연결 가정이지만, 안전하게 남겨둠
// long ans = Long.MAX_VALUE;
// for (int k = 0; k <= K; k++) ans = Math.min(ans, dist[N][k]);
// System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
// }
// }