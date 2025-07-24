import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_12919 {

    static int countAinS, countBinS;
    static String S, T;
    static boolean flag = false;
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        // S 와 T 의 A,B 개수 세기
        for(int i=0;i<S.length();i++){
            if (S.charAt(i) == 'A'){
                countAinS++;
            }
            else{
                countBinS++;
            }
        }

        int countAinT = 0;
        int countBinT = 0;

        for(int i=0;i<T.length();i++){
            if (T.charAt(i) == 'A'){
                countAinT++;
            }
            else{
                countBinT++;
            }
        }

        dfs(T, countAinT, countBinT);
        System.out.println(flag ? 1 : 0);
    }

    // T -> S 역순
    public static void dfs(String now, int countAinT, int countBinT){
        if (flag) return; // S로 만드는 것이 가능하다면 리턴
        if(countAinS > countAinT || countBinS > countBinT) return; // A, B 개수로 가지치기
        if (set.contains(now)) return; // Set을 활용해 이미 한번 와봤다면 가지치기

        set.add(now);

        if(now.length() == S.length()){
            if(now.equals(S)){
                flag = true;
            }
            return;
        }

        if (now.charAt(0) == 'B'){
            StringBuilder sb = new StringBuilder(now.substring(1));
            dfs(sb.reverse().toString(), countAinT, countBinT-1);
        }

        if (now.charAt(now.length()-1) == 'A'){
            dfs(now.substring(0, now.length()-1), countAinT-1, countBinT);
        }

    }
}
