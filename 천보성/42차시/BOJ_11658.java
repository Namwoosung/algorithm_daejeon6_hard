package seg_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11658 {

    static int[][] tree;
    static int[][] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        arr = new int[N + 1][N + 1];
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        tree = new int[4*N][4*N];

        buildTree(1,1,N);

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());

            if (w == 0) {
                int yIdx = Integer.parseInt(st.nextToken());
                int xIdx = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                int diff = c - arr[yIdx][xIdx];
                arr[yIdx][xIdx] = c;

                update(yIdx, xIdx, 1, 1, N, diff);
            } else {

                int y1 = Integer.parseInt(st.nextToken());
                int x1 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());

                sb.append(query(1, 1, N, y1, y2, x1, x2)).append('\n');
            }
        }
        System.out.println(sb);
    }

    public static void buildTree(int yNode, int yStart, int yEnd) {
        if (yStart == yEnd) {
            buildTreeX(yNode, 1, 1, N, yStart);
            return;
        }

        int yMid = (yStart + yEnd) / 2;
        buildTree(yNode * 2, yStart, yMid);
        buildTree(yNode * 2 + 1, yMid + 1, yEnd);

        mergeXTrees(yNode, 1, 1, N);
    }

    public static void buildTreeX(int yNode, int xNode, int xStart, int xEnd, int rowIdx) {
        if (xStart == xEnd) {
            tree[yNode][xNode] = arr[rowIdx][xStart];
            return;
        }
        int xMid = (xStart + xEnd) / 2;
        buildTreeX(yNode, xNode * 2, xStart, xMid, rowIdx);
        buildTreeX(yNode, xNode * 2 + 1, xMid + 1, xEnd, rowIdx);
        tree[yNode][xNode] = tree[yNode][xNode * 2] + tree[yNode][xNode * 2 + 1];
    }
    public static void mergeXTrees(int yNode, int xNode, int xStart, int xEnd) {
        tree[yNode][xNode] = tree[yNode * 2][xNode] + tree[yNode * 2 + 1][xNode];

        if (xStart != xEnd) {
            int xMid = (xStart + xEnd) / 2;
            mergeXTrees(yNode, xNode * 2, xStart, xMid);
            mergeXTrees(yNode, xNode * 2 + 1, xMid + 1, xEnd);
        }
    }

    public static long query(int yNode, int yStart, int yEnd, int y1, int y2, int x1, int x2) {
        if (yEnd < y1 || y2 < yStart) {
            return 0;
        }
        if (y1 <= yStart && yEnd <= y2) {
            return queryX(yNode, 1, 1, N, x1, x2);
        }

        int yMid = (yStart + yEnd) / 2;
        long lSum = query(yNode * 2, yStart, yMid, y1, y2, x1, x2);
        long rSum = query(yNode * 2 + 1, yMid + 1, yEnd, y1, y2, x1, x2);

        return lSum + rSum;
    }

    public static long queryX(int yNode, int xNode, int xStart, int xEnd, int x1, int x2) {
        if (xEnd < x1 || x2 < xStart) {
            return 0;
        }
        if (x1 <= xStart && xEnd <= x2) {
            return tree[yNode][xNode];
        }

        int xMid = (xStart + xEnd) / 2;
        long lSum = queryX(yNode, xNode * 2, xStart, xMid, x1, x2);
        long rSum = queryX(yNode, xNode * 2 + 1, xMid + 1, xEnd, x1, x2);

        return lSum + rSum;
    }

    public static void update(int yIdx, int xIdx, int yNode, int yStart, int yEnd, long diff) {
        if (yStart > yIdx || yIdx > yEnd) {
            return;
        }

        updateX(yNode, 1, 1, N, xIdx, diff);

        if (yStart == yEnd) {
            return;
        }

        int yMid = (yStart + yEnd) / 2;
        update(yIdx, xIdx, yNode * 2, yStart, yMid, diff);
        update(yIdx, xIdx, yNode * 2 + 1, yMid + 1, yEnd, diff);
    }

    public static void updateX(int yNode, int xNode, int xStart, int xEnd, int xIdx, long diff) {
        if (xStart > xIdx || xIdx > xEnd) {
            return;
        }

        tree[yNode][xNode] += diff;

        if (xStart == xEnd) {
            return;
        }

        int xMid = (xStart + xEnd) / 2;
        updateX(yNode, xNode * 2, xStart, xMid, xIdx, diff);
        updateX(yNode, xNode * 2 + 1, xMid + 1, xEnd, xIdx, diff);
    }
}
