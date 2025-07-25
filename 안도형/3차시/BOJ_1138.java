import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] answer = new int[arr.length];
        int index = 0;
        while(index < arr.length) {
            int count = arr[index];
            for (int i = 0; i < N; i++) {
                if(answer[i] != 0) continue;

                if (count > 0) {
                    count--;
                }else {
                    answer[i] = index + 1;
                    break;
                }
            }
            index++;
        }
        for (int val : answer) {
            System.out.print(val + " ");
        }
    }


}
