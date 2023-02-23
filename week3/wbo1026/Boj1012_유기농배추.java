package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1012 {
    static int[][] farm;
    static boolean[][] visited;
    static int[] mx = { 0, 0, -1, 1 }; // 상, 하, 좌, 우
    static int[] my = { -1, 1, 0, 0 };
    static Queue<Integer> qX, qY;
    static int cnt;

    static void bfs() { // x,y는 좌표

        while (!qX.isEmpty()) {
            int x = qX.poll();
            int y = qY.poll();

            for (int i = 0; i < 4; i++) {
                int dx = x + mx[i];
                int dy = y + my[i];
                if (dy >= 0 && dy < farm.length && dx >= 0 && dx < farm[0].length && !visited[dy][dx]
                        && farm[dy][dx] == 1) {
                    qX.add(dx);
                    qY.add(dy);
                    visited[dy][dx] = true;
                }
            }
        }
        cnt++;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 가로
            int N = Integer.parseInt(st.nextToken()); // 세로
            int K = Integer.parseInt(st.nextToken()); // 배추 위치의 개수
            farm = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                farm[y][x] = 1;
            }

            // end input
            cnt = 0;
            qX = new LinkedList<>();
            qY = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && farm[i][j] == 1) {
                        qY.add(i);
                        qX.add(j);
                        visited[i][j] = true;
                        bfs();
                    }
                }
            }

            System.out.println(cnt);

        }
    }
}