import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static int nodeCnt;
    static int targetCnt;
    static Pos robot;
    static List<Pos> node;
    static int[][] graph;
    static int[][] dp;
    static int allVisited;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Route {
        int x;
        int y;
        int dist;

        public Route(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (N == 0 || M == 0)
                break;

            board = new int[N][M];

            // 입력값
            node = new ArrayList<>();
            String line;
            for (int i = 0; i < N; i++) {
                line = br.readLine();
                for (int j = 0; j < M; j++) {
                    char now = line.charAt(j);
                    if (now == '.') {
                        continue;
                    } else if (now == '*') {
                        board[i][j] = 1;
                        node.add(new Pos(i, j));
                    } else if (now == 'x') {
                        board[i][j] = -1;
                    } else if (line.charAt(j) == 'o') {
                        robot = new Pos(i, j);
                    }
                }
            }
            node.add(robot); // 0, ..., nodeCnt -2는 먼지 위치를 의미, nodeCnt -1은 로봇 초기 위치를 의미
            nodeCnt = node.size();
            targetCnt = nodeCnt - 1;

            graph = new int[nodeCnt][nodeCnt];

            boolean possible = makeGraph();
            if (!possible) {
                sb.append(-1).append("\n");
                continue;
            }

            allVisited = (1 << targetCnt) - 1;
            dp = new int[allVisited + 1][targetCnt];
            for (int i = 0; i <= allVisited; i++) {
                for (int j = 0; j < targetCnt; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < targetCnt; i++) {
                tsp(allVisited, i);
                answer = Math.min(answer, dp[allVisited][i]);
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    // 그래프를 만드는 함수
    // 로봇의 처음 위치와 모든 target들 서로서로의 모든 간선을 만듦
    static boolean makeGraph() {
        // 모든 노드들 끼리의 양방향 연결관계를 설정
        for (int i = 0; i < nodeCnt; i++) {
            for (int j = i + 1; j < nodeCnt; j++) {
                int nowDist = checkRoute(node.get(i), node.get(j));
                if (nowDist == -1)
                    return false;
                graph[i][j] = nowDist;
                graph[j][i] = nowDist;
            }
        }

        return true;
    }

    // 길을 탐색하는 함수
    static int checkRoute(Pos start, Pos target) {
        Deque<Route> queue = new ArrayDeque<>();

        queue.add(new Route(start.x, start.y, 0));
        boolean[][] boardVisited = new boolean[N][M];
        boardVisited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Route now = queue.poll();
            if (now.x == target.x && now.y == target.y) {
                return now.dist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                if (!(nx >= 0 && nx < N && ny >= 0 && ny < M))
                    continue;
                if (boardVisited[nx][ny] || board[nx][ny] == -1)
                    continue;

                queue.add(new Route(nx, ny, now.dist + 1));
                boardVisited[nx][ny] = true;
            }
        }
        return -1;
    }

    static int tsp(int visited, int now) {
        int prev = visited - (1 << now);

        if (prev == 0) {
            dp[visited][now] = graph[nodeCnt - 1][now];
            return dp[visited][now];
        } else {
            for (int i = 0; i < targetCnt; i++) {
                if ((prev & (1 << i)) != 0) {
                    dp[visited][now] = Math.min(dp[visited][now], tsp(prev, i) + graph[i][now]);
                }
            }
            return dp[visited][now];
        }
    }
}