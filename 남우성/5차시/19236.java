import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Fish {
		int x, y, num, dir;
		boolean alive;

		public Fish(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.alive = true;
		}

		public Fish(Fish other) {
			this.x = other.x;
			this.y = other.y;
			this.num = other.num;
			this.dir = other.dir;
			this.alive = other.alive;
		}
	}

	static class Shark {
		int x, y, dir;

		public Shark(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		public Shark(Shark other) {
			this.x = other.x;
			this.y = other.y;
			this.dir = other.dir;
		}
	}

	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int result = 0;
	static Fish[][] board;
	static Fish[] fishes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		board = new Fish[4][4];
		fishes = new Fish[16];

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				Fish fish = new Fish(i, j, num, dir);
				board[i][j] = fish;
				fishes[num - 1] = fish;
			}
		}

		int score = board[0][0].num;
		Shark shark = new Shark(0, 0, board[0][0].dir);
		board[0][0].alive = false;
		board[0][0] = null;

		result = score;

		dfs(shark, score, fishes);

		System.out.println(result);
	}

	static void dfs(Shark shark, int score, Fish[] fishes) {
		// 1. 깊은 복사된 fishes, board 구성
		Fish[][] newBoard = new Fish[4][4];
		Fish[] newFishes = new Fish[16];

		for (int i = 0; i < 16; i++) {
			if (fishes[i] != null) {
				Fish temp = new Fish(fishes[i]);
				newFishes[i] = temp;
				if (temp.alive) {
					newBoard[temp.x][temp.y] = temp;
				}
			}
		}

		Shark newShark = new Shark(shark);

		// 2. 물고기 이동
		for (int i = 0; i < 16; i++) {
			Fish fish = newFishes[i];
			if (fish == null || !fish.alive) continue;

			int x = fish.x;
			int y = fish.y;
			int standDir = fish.dir;

			for (int d = 0; d < 8; d++) {
				int ndir = (fish.dir + d) % 8;
				int nx = x + dx[ndir];
				int ny = y + dy[ndir];

				if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (nx == newShark.x && ny == newShark.y))
					continue;

				Fish target = newBoard[nx][ny];
				if (target != null) {
					// swap
					newBoard[x][y] = target;
					newBoard[nx][ny] = fish;

					int tx = fish.x, ty = fish.y;
					fish.x = nx; fish.y = ny;
					target.x = tx; target.y = ty;

					newFishes[fish.num - 1] = fish;
					newFishes[target.num - 1] = target;
				} else {
					// move
					newBoard[x][y] = null;
					fish.x = nx; fish.y = ny;
					newBoard[nx][ny] = fish;
					newFishes[fish.num - 1] = fish;
				}
				fish.dir = ndir;
				break;
			}
		}

		// 3. 상어 이동
		int nx = newShark.x;
		int ny = newShark.y;

		while (true) {
			nx += dx[newShark.dir];
			ny += dy[newShark.dir];

			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;
			if (newBoard[nx][ny] == null || !newBoard[nx][ny].alive) continue;

			Fish target = newBoard[nx][ny];

			Fish[] copiedFishes = new Fish[16];
			for (int i = 0; i < 16; i++) {
				if (newFishes[i] != null) copiedFishes[i] = new Fish(newFishes[i]);
			}

			copiedFishes[target.num - 1].alive = false;
			Shark copiedShark = new Shark(nx, ny, target.dir);

			dfs(copiedShark, score + target.num, copiedFishes);
		}

		result = Math.max(result, score);
	}
}
