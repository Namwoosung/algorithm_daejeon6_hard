import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1708 {
    static Point first = new Point(40001, 40001);

    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Point> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            arr.add(new Point(x, y));
        }

        Stack stack = convexHull(arr);
        System.out.println(stack.size());
    }

    public static Stack convexHull(List<Point> arr) {
        // y 좌표가 가장 작은 점을 기준점으로 잡는다.
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).y < first.y) {
                first = arr.get(i);
            }
            // y 좌표가 동일한 경우 x 좌표 비교
            else if (arr.get(i).y == first.y) {
                if (arr.get(i).x < first.x) {
                    first = arr.get(i);
                }
            }
        }

        // 기준점과 나머지점들이 ccw로 반시계방향(좌회전)이 되도록 정렬.
        // 만약, 세점이 일직선상에 있으면 거리가 증가하도록 정렬
        arr.sort(new Comparator<Point>() {
            @Override
            public int compare(Point second, Point third) {
                int result = ccw(first, second, third);
                if (result > 0)
                    return -1;
                else if (result < 0)
                    return 1;
                else { // result == 0으로 세 점이 일직선상에 있는 경우
                    if (dist(first, second) > dist(first, third))
                        return 1;
                }
                return -1;
            }
        });

        // Graham Scan 알고리즘
        Stack<Point> stack = new Stack<Point>();
        stack.add(first);
        for (int i = 1; i < arr.size(); i++) {
            while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), arr.get(i)) <= 0) {
                stack.pop();
            }
            stack.add(arr.get(i));
        }

        return stack;
    }

    private static int ccw(Point a, Point b, Point c) {
        long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        if (result > 0)
            return 1;
        if (result < 0)
            return -1;
        return 0;
    }

    private static long dist(Point a, Point b) {
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }
}
