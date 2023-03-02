package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17144_미세먼지안녕 {

    static int R, C, T; // 세로, 가로, 시간
    static int[][] map; // 지도
    static int[] my = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] mx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 세로
        C = Integer.parseInt(st.nextToken()); // 가로
        T = Integer.parseInt(st.nextToken()); // 시간(초)
        map = new int[R + 1][C + 1]; // 지도 생성
        int sy = 0;
        int sx = 0;

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) { // 공기청정기면
                    sy = i; // 공기청정기 두칸 중 밑에 칸
                    sx = j;
                }

            }
        }

        // end input

        for (int i = 0; i < T; i++) {
            // 미세먼지 확산
            spreadDust();
            // 청정기 시계
            clockWise(sy, sx);
            // 청정기 반시계
            antiClockWise(sy - 1, sx);
        }

        answer(); // 정답 출력
    }

    static void clockWise(int sy, int sx) { // 시계방향 - 2, 1, 0, 3
        // 현재 위치 받기 - 공기청정기 위치에서 아래 한칸
        int cy = sy + 1;
        int cx = sx;
        int d = 2; // 시작은 아래로
        while (true) {
            // 다음 위치 받기
            int ny = cy + my[d];
            int nx = cx + mx[d];

            // if 다음 위치가 범위 바깥이면 방향 회전
            if (ny < sy || ny > R || nx < 1 || nx > C) {
                d--;
                if (d == -1)
                    d = 3;
                continue;
            }

            if (map[ny][nx] == -1) {
                map[cy][cx] = 0;
                break;
            }

            // 현재 위치에 다음 위치 값 덮어쓰기
            map[cy][cx] = map[ny][nx];

            // 현재 위치를 다음 위치로 변경
            cy = ny;
            cx = nx;
        }

    }

    static void antiClockWise(int sy, int sx) { // 반시계 방향 - 0, 1, 2, 3
        // 현재 위치 받기 - 공기청정기 위치에서 위 한칸
        int cy = sy - 1;
        int cx = sx;
        int d = 0; // 시작은 위로

        while (true) {
            // 다음 위치 받기
            int ny = cy + my[d];
            int nx = cx + mx[d];

            // if 다음 위치가 범위 바깥이면 방향 회전
            if (ny > sy || ny < 1 || nx < 1 || nx > C) {
                d = (d + 1) % 4;
                continue;
            }

            if (map[ny][nx] == -1) {
                map[cy][cx] = 0;
                break;
            }

            // 현재 위치에 다음 위치 값 덮어쓰기
            map[cy][cx] = map[ny][nx];

            // 현재 위치를 다음 위치로 변경
            cy = ny;
            cx = nx;
        }

    }

    static void spreadDust() {
        // 미세먼지가 들어있는 q 생성
        Queue<Integer> q = createQ();

        while (!q.isEmpty()) {
            int sy = q.poll(); // 미세먼지 y, x
            int sx = q.poll();
            int dCost = q.poll() / 5; // 확산되는 양

            // 4방향 확인
            int plusCnt = 0;
            for (int i = 0; i < 4; i++) {
                int ny = sy + my[i]; // 다음 칸
                int nx = sx + mx[i];

                // 범위를 벗어나거나 공기청정기인 부분은 건너뜀
                if (ny < 1 || ny > R || nx < 1 || nx > C || map[ny][nx] == -1)
                    continue;

                map[ny][nx] += dCost; // 미세먼지 더해주기
                plusCnt++; // 4구역중 얼만큼 퍼졌는지 확인

            }
            map[sy][sx] -= dCost * plusCnt; // 남은 미세먼지 양
        }
    }

    static Queue<Integer> createQ() {
        Queue<Integer> q = new ArrayDeque<>(); // q생성

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == 0 || map[i][j] == -1)
                    continue;

                q.add(i); // 미세먼지 y값 넣기
                q.add(j); // 미세먼지 x값 넣기
                q.add(map[i][j]); // 미세먼지 농도 넣기
            }
        }

        return q;
    }

    static void answer() { // 미세먼지 양 출력 메서드
        int ans = 0;

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == -1 || map[i][j] == 0) // 공기청정기거나 0인 부분은 더하지 않음
                    continue;

                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }
}
