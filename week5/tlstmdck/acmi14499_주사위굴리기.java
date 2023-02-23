package algorithm.acmi.study.week5.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmi14499_주사위굴리기 {
    static int x, y, N, M, cur_dice;
    static int[] dice = new int[7];
    static int[][] map;
    static int[] reverse = { 0, 6, 5, 4, 3, 2, 1 };
    static int[][] d = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        cur_dice = 1;
        for (int i = 0; i < K; i++) {
            int menu = Integer.parseInt(st.nextToken());
            switch (menu) {
                case 1: // 동
                    go(1, x, y);
                    break;
                case 2: // 서
                    go(2, x, y);
                    break;
                case 3: // 북
                    go(3, x, y);
                    break;
                case 4: // 남
                    go(4, x, y);
                    break;
            }
        }
    }

    public static void go(int go_dir, int cur_x, int cur_y) {
        int next_x = cur_x + d[go_dir - 1][0];
        int next_y = cur_y + d[go_dir - 1][1];
        if (next_x < 0 || next_x >= N || next_y < 0 || next_y >= M)
            return;
        rotate(go_dir);
        if (map[next_x][next_y] == 0) {
            map[next_x][next_y] = dice[1];
        } else {
            dice[1] = map[next_x][next_y];
            map[next_x][next_y] = 0;
        }
        x = next_x;
        y = next_y;
        System.out.println(dice[6]);
    }

    public static void rotate(int go_dir) {
        int tmp = 0;
        switch (go_dir) {
            case 1: // 동
                tmp = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = tmp;
                break;
            case 2: // 서
                tmp = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = tmp;
                break;
            case 3: // 북
                tmp = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = dice[1];
                dice[1] = tmp;
                break;
            case 4: // 남
                tmp = dice[6];
                dice[6] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = tmp;

                break;
        }
    }
}
