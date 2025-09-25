import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static class Rec {
        int height;
        int startIndex;

        public Rec(int height, int startIndex) {
            this.height = height;
            this.startIndex = startIndex;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = br.readLine()).equals("0")) {
            String[] tokens = line.split(" ");
            int N = Integer.parseInt(tokens[0]);

            int[] heights = new int[N];
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(tokens[i + 1]);
            }

            // 계산 시작
            long result = 0;
            Deque<Rec> stack = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                int currentHeight = heights[i];
                int startIndex = i;

                while (!stack.isEmpty() && stack.peekLast().height > currentHeight) {
                    Rec popped = stack.pollLast();
                    result = Math.max(result, (long) popped.height * (i - popped.startIndex));
                    startIndex = popped.startIndex;
                }

                stack.addLast(new Rec(currentHeight, startIndex));
            }

            while (!stack.isEmpty()) {
                Rec popped = stack.pollLast();
                result = Math.max(result, (long) popped.height * (N - popped.startIndex));
            }

            System.out.println(result);
        }
    }
}