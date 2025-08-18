import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767 {
    static int N, maxConnect, minLen;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] table;
    static List<Core> coreList;

    static class Core {
        int x;
        int y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    StringTokenizer st;
	    int T = Integer.parseInt(br.readLine());
	    
	    for(int tc = 1; tc <= T; tc++) {
	        N = Integer.parseInt(br.readLine());
	        table = new int[N][N];
	        maxConnect = minLen = 0;
	        coreList = new ArrayList<>();
	    
	        //�Է�
	        for(int i = 0; i < N; i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0; j < N; j++) {
	                table[i][j] = Integer.parseInt(st.nextToken());
	                //������ �ʿ��� �ھ� ����
	                if((i > 0 && i < N - 1) && (j > 0 && j < N - 1) && table[i][j] == 1) {
	                    coreList.add(new Core(j, i));
	                }
	            }
	        }
	        
	        search(0, 0, 0);
	        sb.append("#").append(tc).append(" ").append(minLen).append("\n");
	    }
	    System.out.print(sb);
	}
	
	public static void search(int core, int connect, int len) {
	    if(core == coreList.size()) { //��� �ھ� Ž�� �Ϸ�
	        if(maxConnect < connect) { // ����� �ھ �� ���ٸ�
	            minLen = len;  // �� ����
	            maxConnect = connect;
	        } else if(maxConnect == connect) { // ���ٸ�
	            minLen = Math.min(minLen, len);
	        }
	        return;
	    }
	    
	    // ���� ������ �ھ�
	    Core c = coreList.get(core);
	    
	    // 4����
	    for(int d = 0; d < 4; d++) {
	        if(check(c.x, c.y, d)) { // ���� ������ ������
	            int nx = c.x;
	            int ny = c.y;
	            int l = 0; // ���� �� ����
	            while(nx > 0 && nx < N - 1 && ny > 0 && ny < N - 1) {
	                nx += dx[d];
	                ny += dy[d];
	                table[ny][nx] = 2; // ������ ���
	                l++;
	            }
	            search(core + 1, connect + 1, len + l);
	            table[ny][nx] = 0; // ��Ʈ��ŷ
	            for(int i = 0; i < l; i++) {
	            	table[ny][nx] = 0;
	            	nx -= dx[d];
	                ny -= dy[d]; 
	            }
	        }
	    }
	    
	    search(core+1, connect, len);
	}

	public static boolean check(int x, int y, int d) {
	    while(x > 0 && x < N - 1 && y > 0 && y < N - 1) {
	        x += dx[d];
	        y += dy[d];
	        if(table[y][x] != 0) {
	            return false;
	        }
	    }
	    
	    return true;
	}
}
