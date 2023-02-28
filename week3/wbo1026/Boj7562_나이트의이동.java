package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7562 {
    static int min, N;
    static int[][] chess;
    static boolean[][] visited;
    static int[] dy = { -2, -2, 2, 2, -1, 1, 1, -1 }; // 나이트 이동 방향
    static int[] dx = { -1, 1, -1, 1, -2, -2, 2, 2 };
    static Queue<Integer> qy, qx, qCnt;

    static void bfs() {

        while (!qy.isEmpty()) {
            int y = qy.poll();
            int x = qx.poll();
            int cnt = qCnt.poll();

            if(chess[y][x] == 2) {
                if(min > cnt)
                    min = cnt;
            }

            for (int i = 0; i < 8; i++) {
                int my = y + dy[i];
                int mx = x + dx[i];
                int plusCnt = cnt + 1;
                if (my >= 0 && my < N && mx >= 0 && mx < N && !visited[my][mx]) {
                    qy.add(my);
                    qx.add(mx);
                    qCnt.add(plusCnt);
                    visited[my][mx] = true;
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;
        int t = Integer.parseInt(br.readLine());

        sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            N = Integer.parseInt(br.readLine());
            chess = new int[N][N];
            st = new StringTokenizer(br.readLine()); // 나이트 위치 받기 - 1
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            chess[y1][x1] = 1;

            st = new StringTokenizer(br.readLine()); // 도달 지점 위치 받기 - 2
            int y2 = Integer.parseInt(st.nextToken()); // 시작 지점
            int x2 = Integer.parseInt(st.nextToken());
            chess[y2][x2] = 2;

            // end input

            min = Integer.MAX_VALUE;
            visited = new boolean[N][N];
            qy = new LinkedList<>();
            qx = new LinkedList<>();
            qCnt = new LinkedList<>();
            qy.add(y1);
            qx.add(x1);
            qCnt.add(0);
            visited[y1][x1] = true;
            bfs();

            //print
            sb.append(min + "\n");
        }

        System.out.println(sb.toString());
    }
}

