import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS로 연결된 영역들 탐색

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] picture;
    static int n, m;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        picture = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                picture[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxArea = 0;
        int pictureNum = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if (picture[i][j] == 1) {
                    int area = BFS(new Pair(i, j));
                    maxArea = Math.max(maxArea, area);
                    pictureNum++;
                }
            }
        }
        System.out.println(pictureNum);
        System.out.println(maxArea);
    }

    public static int BFS(Pair start) {
        int area = 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        picture[start.y][start.x] = 0;
        area++;

        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < m &&
                        0 <= ny && ny < n &&
                        picture[ny][nx] == 1
                ) {
                    q.offer(new Pair(ny, nx));
                    picture[ny][nx] = 0;
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