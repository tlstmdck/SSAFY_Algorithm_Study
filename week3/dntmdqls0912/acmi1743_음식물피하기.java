import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 여러 영역애 대하여 BFS로 탐색하여
// 연속된 크기가 가장 큰 영역을 저장

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] passage;
    static int n, m;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        passage = new boolean[n][m];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            passage[y][x] = true;

        }

        int maxArea = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if (passage[i][j]) {
                    int area = BFS(new Pair(i, j));
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        System.out.println(maxArea);
    }

    public static int BFS(Pair start) {
        int area = 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        passage[start.y][start.x] = false;
        area++;

        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < m &&
                        0 <= ny && ny < n &&
                        passage[ny][nx]
                ) {
                    q.offer(new Pair(ny, nx));
                    passage[ny][nx] = false;
                    area++;
                }
            }
        }

        return area;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int y, int x) {
        this.x = x;
        this.y = y;
    }
}