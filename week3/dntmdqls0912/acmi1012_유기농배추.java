import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2차원 배열을 만들고, bfs를 돌려서 독립된 섬을 찾는 문제
// bfs가 돌아갈때마다 새로운 섬 이므로 지렁이가 한마리 씩 필요하다

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for(int t=0; t<testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

//			m - 가로,  n - 세로
            boolean[][] field = new boolean[n][m];
            int worm = 0;

            // 배추 위치 표시
            for(int i=0; i<k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                field[y][x] = true;
            }

            // 모든 배열을 한번씩 검사함
            for(int y=0; y<n; y++) {
                for(int x=0; x<m; x++) {
                    // 배추가 있다면
                    if (field[y][x]) {
                        worm++; // 벌레 추가

                        dfs(field, x, y, dx, dy, m, n); // BFS or DFS 실행
                    }
                }
            }
            System.out.println(worm);
        }
    }

    public static void bfs(boolean[][] field, int x, int y, int[] dx, int[] dy, int m, int n) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        field[y][x] = false;

        while(!q.isEmpty()) {
            Pair current = q.poll();

            for(int i=0; i<4; i++) {
                if (0 <= current.y+dy[i] && current.y+dy[i] < n &&
                        0 <= current.x+dx[i] && current.x+dx[i] < m &&
                        field[current.y+dy[i]][current.x+dx[i]]
                ) {

                    field[current.y+dy[i]][current.x+dx[i]] = false;
                    q.offer(new Pair(current.x+dx[i], current.y+dy[i]));
                }
            }
        }
    }

    public static void dfs(boolean[][] field, int x, int y, int[] dx, int[] dy, int m, int n) {
        if (!(0 <= y && y < n)) return;
        if (!(0 <= x && x < m)) return;
        if (!field[y][x]) return;

        field[y][x] = false;
        for(int i=0; i<4; i++) {
            dfs(field, x+dx[i], y+dy[i], dx, dy, m, n);
        }
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}