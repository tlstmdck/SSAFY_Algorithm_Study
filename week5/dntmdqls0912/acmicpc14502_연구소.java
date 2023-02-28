import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 조합을 통해 3개의 빈칸을 선택해 1로 바꾼 후,
 * BFS를 통해 바이러스를 퍼트리고 안전영역을 구한다
 *
 *
 * #주의점#
 * 바이러스가 퍼저나가는 중에 바이러스가 있는 곳을 만날 경우 어떻게 처리할 것 인가?
 * => 바이러스의 위치만 기억하고 배열 상에서는 빈칸으로 두고,
 * 바이러스를 퍼트리기 시작할 때, 빈칸이 아니면 시행하지 않는 방법으로 해결
 */

public class Main {

    static int N, M, defaultSafeArea, maxSafeArea;
    static int[][] lab;
    static boolean[][] visit;
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
    /*
     * @param list 벽을 세울 수 있는 후보 리스트
     * @param viruses 바이러스의 위치 리스트
     * @param select 선택한 벽 조합
     */
    static List<Pair> list, viruses;
    static Pair[] select;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        list = new ArrayList<>();
        viruses = new ArrayList<>();
        select = new Pair[3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

                /*
                 * 칸이 0 이면 list 목록에 추가, 2이면 바이러스 목록에 추가
                 */
                if (lab[i][j] == 0) {
                    list.add(new Pair(i, j));
                    defaultSafeArea++;
                } else if (lab[i][j] == 2) {
                    viruses.add(new Pair(i, j));
                    lab[i][j] = 0;	// 바이러스 위치만 가져오고 배열에는 0으로 기록
                    defaultSafeArea++;
                }
            }
        }

        // 빈 칸중 3개를 골라 벽을 만들것 이므로 기본 안전영역 크기에서 3 감소
        defaultSafeArea -= 3;
        setWall(0, 0);
        System.out.println(maxSafeArea);


    }

    static void setWall(int idx, int cnt) {
        if (cnt == 3) {
            /*
             *  조합이 생성되면 lab 배열에 조합 좌표마다 벽을 세우고,
             *  바이러스가 퍼진 영역의 넓이 spreadArea를 구한다
             *  이후 벽을 다시 허물고서 재귀를 종료한다
             */
            int spreadArea = 0;
            visit = new boolean[N][M];
            for(Pair p : select) {
                lab[p.row][p.col] = 1;
            }

            for(Pair v : viruses) {
                spreadArea += spreadVirus(v);
            }

            maxSafeArea = Math.max(maxSafeArea, defaultSafeArea - spreadArea);

            for(Pair p : select) {
                lab[p.row][p.col] = 0;
            }

            return;
        }

        /*
         * 3개의 빈칸을 고르는 조합 생성
         */
        for(int i=idx; i<list.size(); i++) {
            select[cnt] = list.get(i);
            setWall(i+1, cnt+1);
        }
    }

    /*
     * 바이러스를 퍼트리는 BFS 메소드
     */
    static int spreadVirus(Pair start) {
        if (visit[start.row][start.col]) return 0;

        visit[start.row][start.col] = true;
        int virusSpreadArea = 1;


        Queue<Pair> q = new ArrayDeque<Pair>();
        q.offer(start);

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            for(int i=0; i<4; i++) {
                int nextRow = cur.row + dr[i];
                int nextCol = cur.col + dc[i];

                if (isValid(nextRow, nextCol) &&
                        lab[nextRow][nextCol] == 0 &&
                        !visit[nextRow][nextCol]
                ) {
                    visit[nextRow][nextCol] = true;
                    virusSpreadArea++;
                    q.offer(new Pair(nextRow, nextCol));
                }
            }
        }

        return virusSpreadArea;
    }

    /*
     * 현재 영역 범위 유효 검사 메소드
     */
    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    static void printArea() {
        for(int[] r : lab) {
            for(int c : r) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

class Pair {
    int row;
    int col;

    public Pair(int r, int c) {
        this.row = r;
        this.col = c;
    }

    @Override
    public String toString() {
        return "("+row+", "+col+")";
    }
}