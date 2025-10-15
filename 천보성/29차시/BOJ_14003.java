import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] tracking = new int[N];

        List<Integer> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(st.nextToken());

            int idx = Collections.binarySearch(list, numbers[i]);

            if (idx < 0){
                idx = -(idx + 1);
            }

            if (idx == list.size()){
                list.add(numbers[i]);
            }
            else{
                list.set(idx, numbers[i]);
            }

            tracking[i] = idx;
        }

        StringBuilder sb = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<>();

        sb.append(list.size()).append('\n');
        int idx = list.size() - 1;
        for (int i=N-1;i>=0;i--){
            if (tracking[i] == idx){
                stack.addFirst(numbers[i]);
                idx--;
            }
        }

        while (!stack.isEmpty()){
            sb.append(stack.pollFirst()).append(' ');
        }
        System.out.println(sb);

    }
}