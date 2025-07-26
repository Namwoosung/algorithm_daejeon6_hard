import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish {
	int num; //0번은 상어
	int direct;
	int eaten;
	
	public Fish(int num, int direct, int eaten) {
		this.num = num;
		this.direct = direct;
		this.eaten = eaten;
	}
	
}

public class BOJ_19236 {
	
	static int[] dx = { 0, -1, -1 ,-1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int maxSum = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[][] map = new Fish[4][4];
		
		//맵 초기화
		for(int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int direct = Integer.parseInt(st.nextToken());
				
				map[i][j] = new Fish(num, direct - 1, 0);
			}
		}
		
		Fish shark = new Fish(0, 0, 0);
		// 첫 번째 물고기 먹기
		shark.eaten += map[0][0].num;
		shark.direct = map[0][0].direct;
		maxSum = Math.max(maxSum, shark.eaten);
		map[0][0] = shark;
		
		cycle(map);
		
		System.out.print(maxSum);
	}
	
	public static void cycle(Fish[][] map) {
		for(int i = 1; i <= 16; i++) { //1번 물고기부터 움직이기
			outer:
			for(int y = 0; y < 4; y++) {
				for(int x = 0; x < 4; x++) {
					if(map[y][x] != null && map[y][x].num == i) {
						moveFish(map, x, y);
						break outer;
					}
				}
			}
		}
		
//		/// 디버깅 ///
//		System.out.println("----------물고기 이동 후----------");
//		for(int y = 0; y < 4; y++) {
//			for(int x = 0; x < 4; x++) {
//				if(map[y][x] != null) {
//					System.out.print(map[y][x].num + " ");
//				} else {
//					System.out.print( "x ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println("--------------------");
//		/// 디버깅 ///
		
		outer:
		for(int y = 0; y < 4; y++) { // 상어 움직이기
			for(int x = 0; x < 4; x++) {
				if(map[y][x] != null && map[y][x].num == 0) {
					moveShark(map, x, y);
					break outer;
				}
			}
		}
	}

	public static void moveFish(Fish[][] map, int x, int y) {
		for(int i = 0; i < 9; i++) { //이동 가능 방향 찾기 -> 없는 경우 방향 원위치
			int d = (map[y][x].direct + i) % 8;
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 맵 밖 이동 불가
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
			// 상어 있는 곳 이동 불가
			if(map[ny][nx] != null && map[ny][nx].num == 0) continue;
			
			// 이동
			if(map[ny][nx] == null) { // 빈 칸
				map[y][x].direct = d;
				map[ny][nx] = map[y][x];
				map[y][x] = null;	
				break;
			} else {
				map[y][x].direct = d;
				Fish temp = map[ny][nx];
				map[ny][nx] = map[y][x];
				map[y][x] = temp;		
				break;
			}
		}
	}
	
	public static void moveShark(Fish[][] map, int x, int y) {
		for(int i = 1; i < 4; i++) { //이동 가능 칸 찾기 -> 최대 3칸까지
			int nx = x + dx[map[y][x].direct] * i;
			int ny = y + dy[map[y][x].direct] * i;
			
			// 맵 밖이나 비어있는 곳은 이동 불가
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || map[ny][nx] == null) continue;
			
			// 맵 복사
			Fish[][] newMap = new Fish[4][4];
			for(int iy = 0; iy < 4; iy++) {
				for(int ix = 0; ix < 4; ix++) {
					if(map[iy][ix] != null) {
						newMap[iy][ix] = new Fish(map[iy][ix].num, map[iy][ix].direct, map[iy][ix].eaten);
					} else {
						newMap[iy][ix] = null;
					}
				}
			}
			
			// 상어 이동
			newMap[y][x].eaten += newMap[ny][nx].num; // 물고기 먹기
			newMap[y][x].direct = newMap[ny][nx].direct; // 물고기 방향 갖기
//			/// 디버깅 ///
//			System.out.println(newMap[ny][nx].num + "물고기 먹음");
//			/// 디버깅 ///
			maxSum = Math.max(maxSum, newMap[y][x].eaten); // 최대값 갱신
			newMap[ny][nx] = newMap[y][x];
			newMap[y][x] = null;
//			/// 디버깅 ///
//			for(int iy = 0; iy < 4; iy++) {
//				for(int ix = 0; ix < 4; ix++) {
//					if(newMap[iy][ix] != null) {
//						System.out.print(newMap[iy][ix].num + " ");
//					} else {
//						System.out.print( "x ");
//					}
//				}
//				System.out.println();
//			}
//			System.out.println("--------------------");
//			/// 디버깅 ///
			cycle(newMap);
		}
	}
}