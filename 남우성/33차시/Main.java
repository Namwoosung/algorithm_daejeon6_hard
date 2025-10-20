import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 2~14개 까지의 case는 직접 입력(15 이상부터는 로직으로 처리 가능)
        String[] minValue = new String[101];
        String[] initialValues = { "-1", "-1", "1", "7", "4", "2", "6", "8", "10", "18", "22", "20", "28", "68", "88" };
        for (int i = 2; i <= 14; i++) {
            minValue[i] = initialValues[i];
        }

        StringBuilder min;
        for (int num = 15; num <= 100; num++) {
            min = new StringBuilder();

            // 규칙을 찾아보면 나머지가 1,2인 경우, 3~5인 경우, 6,7인 경우 3가지에 따라 달라짐
            int stand = num % 7;
            int preValue;
            if (1 <= stand && stand <= 2) {
                min.append("1");
                preValue = num - 2;

                // 13, 20, 27, ... 이런 애들은 앞을 0으로 바꿀 수 있음
                if (preValue % 7 == 6) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(minValue[preValue]);
                    temp.setCharAt(0, '0');
                    min.append(temp);
                } else {
                    min.append(minValue[preValue]);
                }
            } else if (3 <= stand && stand <= 5) {
                min.append("2");
                preValue = num - 5;

                // 12, 19, 20, ... 이런 애들은 앞에 2개를 0으로 바꿀 수 있음
                if (preValue % 7 == 5) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(minValue[preValue]);
                    temp.setCharAt(0, '0');
                    temp.setCharAt(1, '0');
                    min.append(temp);
                } else if (preValue % 7 == 6) { // 13, 20, 27, ... 이런 애들은 앞을 0으로 바꿀 수 있음
                    StringBuilder temp = new StringBuilder();
                    temp.append(minValue[preValue]);
                    temp.setCharAt(0, '0');
                    min.append(temp);
                } else {
                    min.append(minValue[preValue]);
                }
            } else if (stand == 6) {
                min.append("6");
                preValue = num - 6;
                min.append(minValue[preValue]);
            } else {
                min.append("8");
                preValue = num - 7;
                min.append(minValue[preValue]);
            }
            minValue[num] = min.toString();
        }

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            StringBuilder max = new StringBuilder();

            // max를 구하는 로직: max값은 1과 7 2개만 사용하는게 최적
            // 최대한 1로 채움
            int div = (num / 2) - 1;
            for (int temp = 0; temp < div; temp++) {
                max.append("1");
            }

            // 마지막은 짝수, 홀수에 따라 1 혹은 7로 채움
            if (num % 2 == 0) {
                max.append("1");
            } else {
                max.append("7");
            }
            max.reverse();

            sb.append(minValue[num]).append(" ").append(max).append("\n");
        }
        System.out.print(sb);
    }
}