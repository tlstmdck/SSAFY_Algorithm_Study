import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
//        나이트의 가능한 이동 8가지를 변환 변수로 저장
        int[] dx = {2, 2, 1, -1, -2, -2, 1, -1};
        int[] dy = {1, -1, 2, 2, 1, -1, -2, -2};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());

        for(int t=1; t<=test; t++) {
            int l = Integer.parseInt(br.readLine());
            boolean[][] visit = new boolean[l][l];

//            시작 점
            StringTokenizer st = new StringTokenizer(br.readLine());
            int stX = Integer.parseInt(st.nextToken());
            int stY = Integer.parseInt(st.nextToken());

//            도착 점
            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

//            시작점과 도착점이 같을경우 바로 리턴
            if (stX == endX && stY == endY) {
                System.out.println(0);
                continue;
            }
            
//            BFS 탐색
            Queue<Move> q = new LinkedList<>();
            q.offer(new Move(stX, stY, 0));
            visit[stY][stX] = true;

            while(!q.isEmpty()) {
                Move pos = q.poll();

                for(int i=0; i<8; i++) {
                    int nx = pos.x + dx[i];
                    int ny = pos.y + dy[i];

//                    도착점에 도달했을 경우 현재까지의 이동카운트에 +1을 하여 리턴
                    if (nx == endX && ny == endY) {
                        System.out.println(pos.cnt + 1);
                        q.clear();
                        break;
                    }

                    if (0 <= nx && nx < l &&
                            0 <= ny && ny < l &&
                            !visit[ny][nx]
                    ) {
                        visit[ny][nx] = true;
                        q.offer(new Move(nx, ny, pos.cnt + 1));
                    }
                }
            }
        }
    }
}

// 각 이동은 현재 좌표와 현재좌표까지의 이동 횟수를 저장
class Move {
    int x;
    int y;
    int cnt;

    public Move(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}