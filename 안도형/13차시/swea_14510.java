package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_14510
{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            ArrayList<Integer> arr = new ArrayList<Integer>();

            int maxHeight = 0;
            for(int i = 0; i < N; i++) {
                arr.add(Integer.parseInt(st.nextToken()));
                maxHeight = arr.get(i) > maxHeight ? arr.get(i) : maxHeight;
            }

            System.out.println("#" + (t+1) + " " + cutTree(arr, maxHeight));
        }
    }

    public static int cutTree(ArrayList<Integer> arr, int maxHeight) {
        int day = 0;
        int oddCount = 0;  // 2cm 남은 경우
        int evenCount = 0; // 1cm 남은 경우

        for (int h : arr) {
            if (maxHeight != h) {
                int diff = maxHeight - h;
                evenCount += diff % 2;
                oddCount += diff / 2;
            }
        }

        int min = Math.min(oddCount, evenCount);
        oddCount -= min;
        evenCount -= min;
        day += min * 2;

        if (evenCount > 0)
            day += evenCount * 2 - 1;

        day += (oddCount / 3) * 4;
        oddCount %= 3;

        if (oddCount == 2) day += 3;
        else if (oddCount == 1) day += 2;

        return day;
    }
}