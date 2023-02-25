package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499_주사위굴리기 {

    static int[] dice;
    static int N, M, diceX, diceY; // 세로, 가로, 주사위 좌표
    static int[][] map;
    static int[] my = {0, 0, -1, 1}; // 오 왼 상 하
    static int[] mx = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        diceY = Integer.parseInt(st.nextToken());
        diceX = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken()); // 명령어 개수

        dice = new int[6]; // 북, 서, 상단, 동, 남, 하단
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }

        System.out.println(sb);

    }

    static void move(int type) {
        int ny = diceY + my[type - 1];
        int nx = diceX + mx[type - 1];

        if (nx < 0 || ny < 0 || nx > M - 1 || ny > N - 1) // 바깥으로 나가려하면
            return;

        turn(nx, ny, type);
        diceY = ny;
        diceX = nx;

    }

    static void turn(int x, int y, int type) {
        switch (type) {
            case 1: // 오
                turnR();
                break;
            case 2: // 왼
                turnL();
                break;
            case 3: // 하
                turnD();
                break;
            case 4: // 상
                turnU();
                break;
        }

        // 지도가 0이면 지도로 복사, 아니면 주사위로 복사
        if (map[y][x] == 0) {
            map[y][x] = dice[5];
        } else {
            dice[5] = map[y][x];
            map[y][x] = 0; // 복사 후 지도를 0으로 초기화 해줘야함
        }

        sb.append(dice[2]).append("\n");
    }

    static void turnR() {
        int tmp = dice[2];
        dice[2] = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[1];
        dice[1] = tmp;
    }

    static void turnL() {
        int tmp = dice[2];
        dice[2] = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[3];
        dice[3] = tmp;
    }

    static void turnD() {
        int tmp = dice[2];
        dice[2] = dice[4];
        dice[4] = dice[5];
        dice[5] = dice[0];
        dice[0] = tmp;
    }

    static void turnU() {
        int tmp = dice[2];
        dice[2] = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[4];
        dice[4] = tmp;
    }
}