import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] house;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		house = new int[R][C];
		int[] cleaner = {-1, -1}; // 공기청정기 열
		
		//입력
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) {
					house[i][j] = num;
					if(num == -1) { // 공기청정기 열 저장
						if(cleaner[0] == -1) { // 첫 번째 저장
							cleaner[0] = i;
						} else { // 두 번째 저장
							cleaner[1] = i;
						}
					}
				}
			}
		}
		
		// T초 동안 반복
		int t = 0;
		while(t < T) {
			difussion(cleaner, t);
			airClear(cleaner, t);
			t++;
		}
		
		//합계
		int sum = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if((i == cleaner[0] || i == cleaner[1]) && j == 0) {
					continue;
				}
				sum += house[i][j];
			}
		}
		System.out.print(sum); 
	}
	
	//확산
	public static void difussion(int[] cleaner, int t) {
		//원본 복사
		int[][] prevHouse = new int[house.length][];
		for(int i = 0; i < house.length; i++) {
			prevHouse[i] = house[i].clone();
		}
		for(int y = 0; y < house.length; y++) {
			for(int x = 0; x < house[0].length; x++) {
				if(prevHouse[y][x] > 0) { // 미세 먼지 존재
					int count = 0; // 확산 개수
					for(int i = 0; i < 4; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						
						// 범위를 벗어나거나 공기청정기인 경우 확산 X
						if(nx < 0 || nx >= house[0].length || ny < 0 || ny >= house.length 
								|| (nx == 0 && ny == cleaner[0]) || (nx == 0 && ny == cleaner[1])) {
							continue;
						}
						
						int mise = house[ny][nx] + (prevHouse[y][x] / 5);
						
						house[ny][nx] = mise;
						count++;
					}
					
					// 빼기
					house[y][x] -= ((prevHouse[y][x] / 5) * count);
				}
			}
		}
	}
	
	//공기청정기 작동
	public static void airClear(int[] cleaner, int t) {	
		// 밀기 -> 거꾸로 검사(겹쳐지는 부분 없애기 위해서)
		// 공청기 위쪽
		for(int y = cleaner[0] - 1; y > 0; y--) {
			house[y][0] = house[y - 1][0];
		}
		
		// 공청기 아래쪽
		for(int y = cleaner[1] + 1; y < house.length - 1; y++) {
			house[y][0] = house[y + 1][0];
		}
		
		// 위쪽 아래쪽
		for(int x = 0; x < house[0].length - 1; x++) {
			house[0][x] = house[0][x + 1];
			house[house.length - 1][x] = house[house.length - 1][x + 1];
		}
		
		// 오른쪽 위로
		for(int y = 0; y < cleaner[0]; y++) {
			house[y][house[0].length - 1] = house[y + 1][house[0].length - 1];
		}
		
		// 오른쪽 아래로
		for(int y = house.length - 1; y > cleaner[1]; y--) {
			house[y][house[0].length - 1] = house[y - 1][house[0].length - 1];
		}
		
		//공청기 옆
		for(int x = house[0].length - 1; x > 1 ; x--) {
			house[cleaner[0]][x] = house[cleaner[0]][x - 1];
			house[cleaner[1]][x] = house[cleaner[1]][x - 1];
		}
		//공청기 바로 옆
		house[cleaner[0]][1] = 0;
		house[cleaner[1]][1] = 0;	
	}

}