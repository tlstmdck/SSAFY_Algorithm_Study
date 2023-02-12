import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS를 통한 영역 탐색 문제

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] init = {0, 0};
        int people = 0;

//        캠퍼스 정보를 담는 2차원 배열
        char[][] campus = new char[n][m];

        for(int i=0; i<n; i++) {
            campus[i] = br.readLine().toCharArray();
        }

//        초기의 도연이 위치를 찾는다
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if (campus[i][j] == 'I') {
                    init[0] = i;
                    init[1] = j;
                    break;
                }
            }
        }

//        초기위치에서부터 BFS 실행
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(init[1], init[0]));
        campus[init[0]][init[1]] = 'X';
        while(!q.isEmpty()) {
            Pair pos = q.poll();

            for(int i=0; i<4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

//                현재 위치에서 지나갈수 있는 위치들을 찾고, 만약 갈 수있다면 그 영역을
//                'X'로 바꾼후 큐에 삽입, 또한 만약 영역이 'P'이면 카운트
                if (0 <= nx && nx < m &&
                        0 <= ny && ny < n &&
                        campus[ny][nx] != 'X'
                ) {
                    if (campus[ny][nx] == 'P') {
                        people++;
                    }
                    campus[ny][nx] = 'X';
                    q.offer(new Pair(nx, ny));
                }
            }
        }
        if (people > 0) {
            System.out.println(people);
        } else {
            System.out.println("TT");
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