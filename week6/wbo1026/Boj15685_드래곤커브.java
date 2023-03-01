package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj15685_드래곤커브 {

    static int N;
    static boolean[][] map;
    static int[] my = {0, -1, 0, 1}; // 우 상 좌 하(0,1,2,3)
    static int[] mx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new boolean[101][101]; // 0~100까지
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 시작 x 좌표
            int y = Integer.parseInt(st.nextToken()); // 시작 y 좌표
            int d = Integer.parseInt(st.nextToken()); // 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            drawMap(y, x, d, g);
        }

        System.out.println(checkSquare());

    }

    static int checkSquare() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) { // 0부터 99까지
            for (int j = 0; j < 100; j++) {
                if (map[i][j]) { // 칠해진 부분 만나면
                    // 우, 하, 오른쪽 아래 대각 true확인
                    if (map[i + my[0]][j + mx[0]] && map[i + my[3]][j + mx[3]]
                            && map[i + my[0] + my[3]][j + mx[0] + mx[3]])
                        cnt++; // 다맞으면 cnt++;
                }
            }
        }
        return cnt;
    }

    private static void drawMap(int y, int x, int d, int g) {

        map[y][x] = true; // 자기 자리 칠하기

        Stack<Integer> s = new Stack<>(); // 원본 스택
        Stack<Integer> copyS = new Stack<>(); // 복사 스택

        s.push(d); // 0세대
        copyS.push(d);
        int ny = y + my[d];
        int nx = x + mx[d];
        map[ny][nx] = true;

        // 0세대 셋팅 끝

        for (int i = 1; i <= g; i++) { // 1세대부터 입력받은 세대까지
            while (!copyS.isEmpty()) {
                d = (copyS.pop() + 1) % 4; // 이전 방향 꺼내기(원본 훼손 방지를 위해 복사한 스택에서 뽑기)
                ny += my[d]; // 이전 마지막 방향 + 1 = 새로운 세대 처음 이동 방향
                nx += mx[d];

                map[ny][nx] = true; // 색칠하기
                s.push(d); // 원본에 추가
            }
            copyS.addAll(s);
        }
    }
}
