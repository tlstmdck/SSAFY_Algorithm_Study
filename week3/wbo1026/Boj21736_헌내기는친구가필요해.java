package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj21736 {
    static boolean[][] visited;
    static int[][] campus;
    static int cnt;
    static int[] mx = new int[] { -1, 1, 0, 0 }; // 상,하,좌,우
    static int[] my = new int[] { 0, 0, -1, 1 };

    static void dfs(int startX, int startY) {

        if (visited[startX][startY]) {
            return;
        }

        visited[startX][startY] = true; // 방문 표시

        if(campus[startX][startY] == 2) // 지금 방문한 칸이 사람이면
            cnt++;

        for (int i = 0; i < 4; i++) {
            int dx = startX + mx[i]; // 4방향으로 움직일 x
            int dy = startY + my[i]; // 4방향으로 움직일 y

            if(dx >= 0 && dx < campus.length && dy >= 0 && dy < campus[0].length
                    && campus[dx][dy] != 1) { // 캠퍼스 밖으로 벗어나지 않고, 벽이 아닐때
                // 재귀
                dfs(dx, dy);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        campus = new int[N][M];
        visited = new boolean[N][M];
        int startX = 0;
        int startY = 0;

        // O: 빈 공간 - 0
        // X: 벽 - 1
        // P: 사람 - 2
        // I: 도연 - 3
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'O')
                    campus[i][j] = 0;
                else if (str.charAt(j) == 'X')
                    campus[i][j] = 1;
                else if (str.charAt(j) == 'P')
                    campus[i][j] = 2;
                else {
                    campus[i][j] = 3;
                    startX = i;
                    startY = j;
                }
            }
        } // end input

        cnt = 0;
        dfs(startX, startY);
        if(cnt == 0)
            System.out.println("TT");
        else
            System.out.println(cnt);
    }

}
