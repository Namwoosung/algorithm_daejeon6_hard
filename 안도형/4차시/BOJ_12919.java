package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {
    static String S;
    static String T;
    static boolean found;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        dfs(T);

        System.out.println(found ? "1" : "0");
    }

    public static void dfs(String str){
        if(found) return;

        if(str.length() == S.length()){
            if(S.equals(str)){
                found = true;
                return;
            }
        }

        if(str.endsWith("A"))
            dfs(str.substring(0, str.length() - 1));

        if(str.startsWith("B"))
            dfs((new StringBuilder(str).reverse()).substring(0, str.length() - 1));
    }
}