package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1303 {
    static int MAX;
    static int[] MX = new int[] { -1, 1, 0, 0 }; // 상 하 좌 우
    static int[] MY = new int[] { 0, 0, -1, 1 };

    public static void search(Queue<Integer> qX, Queue<Integer> qY, int[][] map, boolean[][] visited, int mode) {
        int area = 0;

        while (!qX.isEmpty()) {
            area++;
            int x = qX.poll();
            int y = qY.poll();

            for (int i = 0; i < 4; i++) {
                int dx = x + MX[i];
                int dy = y + MY[i];
                if (dx >= 0 && dx < map.length && dy >= 0 && dy < map[0].length && !visited[dx][dy]
                        && map[dx][dy] == mode) {
                    qX.add(dx);
                    qY.add(dy);
                    visited[dx][dy] = true;
                }
            }

        }
        MAX += Math.pow(area, 2);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 가로
        int m = Integer.parseInt(st.nextToken()); // 세로

        Queue<Integer> qX = new LinkedList<>();
        Queue<Integer> qY = new LinkedList<>();
        int[][] map = new int[m][n]; // W는 1, B는 0
        boolean[][] visited = new boolean[m][n]; // false: 방문안함, true: 방문함

        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                if(str.charAt(j) == 'W')
                    map[i][j] = 1;
                else
                    map[i][j] = 0;
            }
        }

        // end input

        MAX = 0;
        int maxW = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    qX.add(i);
                    qY.add(j);
                    visited[i][j] = true;
                    search(qX, qY, map, visited, 1);
                }
            }
        }
        maxW = MAX;

        MAX = 0;
        int maxB = 0;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    qX.add(i);
                    qY.add(j);
                    visited[i][j] = true;
                    search(qX, qY, map, visited, 0);
                }
            }
        }
        maxB = MAX;

        System.out.printf("%d %d",maxW, maxB);

    }
}
