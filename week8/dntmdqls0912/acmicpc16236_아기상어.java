import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 상어의 위치로부터 조건에 부합하는 BFS를 수행한다
 * BFS는 q.size를 이용해 각 BFS의 step을 정의하고, 각 step 수행중 먹을수 있는 물고기를 만나면
 * 최상단 왼쪽에있는 물고기를 조건으로 탐색한다
 * 이를 모든 물고기를 먹거나, 먹을수 있는 물고기가 없을때 까지 수행한다
 */


public class Main {
    static int N, sharkSize = 2, ateFishNum = 0, fishes, sharkR, sharkC, timer;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    if (map[i][j] == 9) {
                        sharkR = i;
                        sharkC = j;
                        map[i][j] = 0;
                    } else {
                        fishes++;
                    }
                }
            }
        } // end Input

        boolean ateFish = true; // BFS 반복 flag

        while(ateFish && fishes != 0) {
            ateFish = BFS(sharkR, sharkC);
        }

        System.out.println(timer);
    }

    // 배열 print 함수
    static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (i == sharkR && j == sharkC) System.out.print(9 + " ");
                else System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("move : " + timer + " sharkSize : " + sharkSize);
    }

    // BFS
    static boolean BFS(int initR, int initC) {
        boolean[][] visit = new boolean[N][N]; // visit 체크 배열
        int steps = 0; // 상어가 나아간 거리

        // 초기위치 or 마지막으로 물고기를 먹은 위치
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(initC, initR));
        visit[initR][initC] = true;

        // 잡아먹을 물고기의 위치
        Point fish = new Point(N, N);

        while(!q.isEmpty()) {
            steps++;
            int size = q.size();
            
            // queue를 step별로 나누어 반복 시행
            for(int i=0; i<size; i++) {
                Point now = q.poll();

                for(int dir=0; dir<4; dir++) {
                    int nr = now.y + dr[dir];
                    int nc = now.x + dc[dir];

                    // 다음 탐색 위치가 배열 안이고 아직 방문하지 않은 위치이면
                    if (isValid(nr, nc) && !visit[nr][nc]) {
                        int mapVal = map[nr][nc];

                        // 잡아먹을수 있는 물고기가 있는 위치이면
                        if (mapVal != 0 && mapVal < sharkSize) {
                            visit[nr][nc] = true;
                            
                            // 이전에 찾은 물고기와 비교하여 더 상단 왼쪽에 있는 물고기를 저장
                            if (nr < fish.y || nr == fish.y && nc < fish.x) {
                                fish.setLocation(nc, nr);
                            }
                        
                        // 잡아먹을 수는 없지만 지나갈 수 있는 위치라면
                        } else if (mapVal == 0 || mapVal == sharkSize) {
                            q.offer(new Point(nc, nr)); // 다음 탐색 위치로 저장
                            visit[nr][nc] = true;
                        }
                    }
                } // end dir for
            } // end q.size for
            
            // 현재 스탭에 대한 물고기 탐색을 끝낸 후, 만약 잡아먹을 수 있는 물고기가 있다면
            if (fish.x != N) {
                fishes--;   // 전체 물고기 수 감소
                ateFishNum++;   // 잡아먹은 물고기 수 카운트

                // 잡아먹은 물고기 수가 상어의 크기와 같다면 상어의 크기 +1
                if (ateFishNum == sharkSize) {
                    ateFishNum = 0;
                    sharkSize++;
                }

                timer += steps; // 현재까지 이동한 거리를 총 이동 거리에 더함
                map[fish.y][fish.x] = 0; // 잡아먹은 물고기의 위치를 0으로 세팅
                sharkR = fish.y;    // 다음 상어의 시작위치 저장
                sharkC = fish.x;
                return true;    // BFS 종료
            }
        }

        return false;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
