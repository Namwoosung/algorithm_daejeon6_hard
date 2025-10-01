import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[2 * M]; // 왼쪽과 위 부분을 일자로 핀 배열(마지막은 그냥 버리는 값)

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a] += 1;
            arr[a + b] += 1;
        }

        int[][] board = new int[M][M];

        board[M - 1][0] = 1 + arr[0];
        int cnt = 1;
        for (int i = M - 2; i >= 0; i--) {
            board[i][0] = board[i + 1][0] + arr[cnt];
            cnt++;
        }

        cnt = M;
        for (int i = 1; i < M; i++) {
            board[0][i] = board[0][i - 1] + arr[cnt];
            cnt++;
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0) {
                    sb.append(board[i][j]).append(" ");
                } else {
                    sb.append(board[0][j]).append(" ");
                }
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}