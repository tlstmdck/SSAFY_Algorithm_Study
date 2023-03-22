package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16234_인구이동 {

    static int N, L, R, ans, sum;
    static boolean flag;
    static int[][] map;
    static boolean[][] visited;
    static List<Nation> list;
    static int[] my = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] mx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N x N 땅 크기
        L = Integer.parseInt(st.nextToken()); // 최소 인원 차이(이상)
        R = Integer.parseInt(st.nextToken()); // 최대 인원 차이(이하)

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        movePopulation();
        System.out.println(ans);
    }

    static void movePopulation() {

        while (true) { // 인구이동이 없을 때 까지 반복
            visited = new boolean[N][N];
            flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) { // 방문 안했으면
                        bfs(i, j);
                        if(flag) // 이동할 얘들이 있으면
                            simulation();
                    }
                }
            }
            if(!flag) // 이동할 얘들이 없으면
                return;

            ans++; // 횟수 추가

        }
    }

    static void simulation() { // 실제 인구 이동
        int rnt = sum / list.size();

        for (int i = 0; i < list.size(); i++) {
            Nation n = list.get(i);
            int y = n.y;
            int x = n.x;

            map[y][x] = rnt;
        }
    }

    static void bfs(int y, int x) {
        Queue<Nation> q = new ArrayDeque<>();
        list = new ArrayList<>(); // 인구 이동이 필요한 나라를 담음

        Nation firstN = new Nation(y, x); // 초기 넣기
        q.offer(firstN);
        list.add(firstN);
        visited[y][x] = true;
        sum = map[y][x];

        while (!q.isEmpty()) {
            Nation cn = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cn.y + my[i]; // 다음 좌표
                int nx = cn.x + mx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx])
                    continue;

                int abs = Math.abs(map[cn.y][cn.x] - map[ny][nx]); // 두 지역의 차이

                if(abs < L || abs > R)
                    continue;

                visited[ny][nx] = true;
                Nation createN = new Nation(ny, nx);
                q.offer(createN);
                list.add(createN); // 인구이동할 목록에 추가
                sum += map[ny][nx];
                flag = true;
            }
        }
    }
}

class Nation {
    int y, x; // 위치
    public Nation(int y, int x) {

        this.y = y;
        this.x = x;
    }
}
