import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2632 {
	
	static final int MAX_SIZE = 2000000;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(br.readLine());
		 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] pizzaA = new int[m];
		int[] pizzaB = new int[n];
		
		for(int i=0;i<m;i++) {
			pizzaA[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0;i<n;i++) {
			pizzaB[i] = Integer.parseInt(br.readLine());
		}
		
		int[] pizzaANum = new int[MAX_SIZE];
		int[] pizzaBNum = new int[MAX_SIZE];
		
		pizzaANum[0] = 1;
		pizzaBNum[0] = 1;
		
		int idx = pizzaA[0];
		pizzaANum[idx]++;
		
		for(int i=1;i<m;i++) {
			idx += pizzaA[i];
			pizzaANum[idx]++;
		}
	
		for(int i=1;i<m;i++) {
			fillPizzaNum(0, i, pizzaA[i], pizzaA, pizzaANum, m);
		}
		
		idx = pizzaB[0];
		pizzaBNum[idx]++;
		
		for(int i=1;i<n;i++) {
			idx += pizzaB[i];
			pizzaBNum[idx]++;
		}
		
		for(int i=1;i<n;i++) {
			fillPizzaNum(0, i, pizzaB[i], pizzaB, pizzaBNum, n);
		}
		
		int idxA = 0;
		int idxB = size;
		int ans = 0;
		for(int i=0;i<=size;i++) {
			ans += pizzaANum[idxA++] * pizzaBNum[idxB--];
		}
		
		System.out.println(ans);
		
	}
	
	
	public static void fillPizzaNum(int depth, int idxPizza, int idxPizzaNum, int[] pizza, int[] pizzaNum, int max) {
		if(idxPizzaNum > MAX_SIZE) return;
		
		pizzaNum[idxPizzaNum]++;
		
		if(depth == max - 2) {
			return;
		}
		
		idxPizza++;
		
		if(idxPizza == max) {
			idxPizza = 0;
		}
		fillPizzaNum(depth + 1, idxPizza, idxPizzaNum + pizza[idxPizza], pizza, pizzaNum, max);
	}
}
