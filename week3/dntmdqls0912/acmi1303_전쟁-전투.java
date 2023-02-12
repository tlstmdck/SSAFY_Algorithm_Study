import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS로 영역을 탐색하며, 시작 영역이 B인지 W인지에 따라
// 나누어 영역을 저장

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] field;
    static int n, m;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        field = new char[m][n];

        for(int i=0; i<m; i++) {
            field[i] = br.readLine().toCharArray();

        }

        int manPowerB = 0;
        int manPowerW = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if (field[i][j] != '#') {
//                    현재 탐색 영역이 B인지 W인지 판단
                    boolean isBlue = field[i][j] == 'B' ? true : false;
                    int power = BFS(new Pair(i, j), field[i][j]);

                    if (isBlue)
                        manPowerB += power*power;
                    else
                        manPowerW += power*power;
                }
            }
        }
        System.out.println(manPowerW + " " + manPowerB);
    }

    public static int BFS(Pair start, char team) {
        int area = 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        field[start.y][start.x] = '#';
        area++;

        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < n &&
                        0 <= ny && ny < m &&
                        field[ny][nx] == team
                ) {
                    q.offer(new Pair(ny, nx));
                    field[ny][nx] = '#';
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