import java.io.*;
import java.util.*;

public class BOJ_1708 {
	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static Point[] points;
	static Point root;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		points = new Point[N - 1];
			
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		root = new Point(x, y);
		// 입력
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			Point p = new Point(x, y);
			points[i] = p;
			// 기준점 - y값이 가장 작고 x값이 가장 작은 점
			if(root.y > points[i].y || (root.y == points[i].y && root.x > points[i].x)) {
				p = root;
				root = points[i];
				points[i] = p;
			}
		}
		
		// 반시계 방향 정렬 - CCW
		Arrays.sort(points, (a, b) -> {
			long ccw = CCW(root, a, b);
			if(ccw > 0) { // 양수 (반시계 방향임)
				return -1;
			} else if(ccw < 0) { // 음수 (시계 방향임)
				return 1;
			} else { // 0이면 직선 (거리순 정렬)
				long p1 = calcDist(root, a);
				long p2 = calcDist(root, b);
				return Long.compare(p1, p2);
			}
		});
		
		// Graham Scan
		Deque<Point> stack = new ArrayDeque<>();
		// 두 점을 넣고 시작
		stack.push(root);
		stack.push(points[0]);
		
		// N개의 점을 모두 볼 때 까지
		for (int i = 1; i < N - 1; i++) {
			Point p = points[i];
			
			// 반시계 방향인 점이 나올 때까지 pop
			while(stack.size() >= 2) {
				Point second = stack.pop();
				Point first = stack.peek();
				
				long ccw = CCW(first, second, p);
				
				// 반시계 방향이면 second점을 스택에 넣고 루프 탈출
				if(ccw > 0) {
					stack.push(second);
					break;
				}
			}
			
			// 비교점을 스택에 push
			stack.push(p);
		}
		
		// stack에 있는 점들이 볼록 껍질을 이루는 점
		System.out.println(stack.size());
	}

	// CCW(Counter Clock Wise) 알고리즘 - 신발끈 공식
	public static long CCW(Point p1, Point p2, Point p3) {
		return 1L * p1.x * p2.y + 1L * p2.x * p3.y + 1L * p3.x * p1.y - (1L * p1.x * p3.y + 1L * p2.x * p1.y + 1L * p3.x * p2.y);
	}

	// 두 점의 거리 구하기
	public static long calcDist(Point p1, Point p2) {
		return (1L * (p1.x - p2.x) * (p1.x - p2.x)) + (1L * (p1.y - p2.y) * (p1.y - p2.y));
	}
}