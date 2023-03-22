import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS로 인접국가간의 연합 여부 및 연합의 종류를 분류하고
// 각 연합마다 인구의 수를 각각 따로 재조정한다

public class Main {
    static int N, L, R;
    static boolean mapChanged;
    static int[][] map, visit;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // END INPUT

        mapChanged = true;  // 인구이동 발생 여부 flag
        int day = 0;        // 이주 날짜 기록
        int nthUnite;       // 생성되는 각 연합을 구분하는 변수
        while(mapChanged) {
            mapChanged = false;
            visit = new int[N][N];
            nthUnite = 1;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if (visit[i][j] == 0) {
                        BFS(i, j, nthUnite++);  // 매 BFS 발생시마다 연합 종류를 구분
                    }
                }
            }

            if (mapChanged) day++;  // 이주가 발생하면 날짜 계산
        }

        System.out.println(day);
    }

    static void BFS(int sr, int sc, int nthUnite) {
        int uniteNum = 1;   // 연합 가입국의 수
        int sumPeople = map[sr][sc];    // 총 인구수
        Queue<Point> q = new ArrayDeque<>();
        visit[sr][sc] = nthUnite;
        q.offer(new Point(sc, sr));

        while(!q.isEmpty()) {
            Point now = q.poll();
            int r = now.y;
            int c = now.x;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isValid(nr, nc) && visit[nr][nc] == 0 && checkConn(r, c, nr, nc)) {
                    visit[nr][nc] = nthUnite;
                    sumPeople += map[nr][nc];
                    uniteNum++;
                    q.offer(new Point(nc, nr));
                }
            }
        }

        // 연합의 수가 2 이상이면 현재 연합에 가입한 국가들만 인구를 재분배
        if (uniteNum > 1) {
            mapChanged = true;
            int avgPeople = sumPeople / uniteNum;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if (visit[i][j] == nthUnite) {
                        map[i][j] = avgPeople;
                    }
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c <N;
    }

    // 두 나라간 국경의 개방 여부 체크
    static boolean checkConn(int r, int c, int nr, int nc) {
        int diff = Math.abs(map[r][c] - map[nr][nc]);
        return L <= diff && diff <= R;
    }
}