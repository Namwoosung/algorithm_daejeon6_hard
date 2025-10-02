import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10836 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = 1;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = N - 1, y = 0;

			for (int j = 0; j < 3; j++) {
				int num = Integer.parseInt(st.nextToken());

				for (int d = 0; d < num; d++) {
					map[x][y] += j;
					if (x > 0)
						x--;
					else
						y++;
				}
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				map[i][j] = Math.max(map[i - 1][j - 1], Math.max(map[i - 1][j], map[i][j - 1]));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
