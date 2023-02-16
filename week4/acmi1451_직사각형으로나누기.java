package algorithm.acmi.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmi1451_직사각형으로나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][M + 1];
        int[][] sum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String Line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = Line.charAt(j - 1) - '0';
                if (i == 1) {
                    sum[i][j] = sum[i][j - 1] + map[i][j];
                    continue;
                }
                if (j == 1) {
                    sum[i][j] = sum[i - 1][j] + map[i][j];
                    continue;
                }
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + map[i][j];
            }
        }
        long max = 0;
        /*
         * 1. 가로줄 두개 그었을 때
         * 1 2 3
         * - - -
         * 4 5 6
         * - - -
         * 7 8 9
         */
        for (int a = 1; a <= N-2; a++) {
            long cnt = 1;
            for (int b = a + 1; b <= N-1; b++) {
                cnt = (long)sum[a][M] * (long)(sum[b][M] - sum[a][M]) * (long)(sum[N][M] - sum[b][M]);
                max = Math.max(max, cnt);
            }
        }
        /*
         * 2. 세로줄 두개 그었을 떄
         * 1 | 2 | 3
         * 4 | 5 | 6
         * 7 | 8 | 9
         */
        for (int a = 1; a <= M-2; a++) {
            long cnt = 1;
            for (int b = a + 1; b <= M-1; b++) {
                cnt = (long)sum[N][a] * (long)(sum[N][b] - sum[N][a]) * (long)(sum[N][M] - sum[N][b]);
                max = Math.max(max, cnt);
            }
        }
        /*
         * 3. 짧은 세로 1줄 + 긴 가로1줄
         */
        for (int x = 1; x < N; x++) { // 가로줄 위치
            // 세로줄이 위에있을때
            /*
             * 1 | 2 3
             * - - - -
             * 4 5 6
             * 7 8 9
             */
            long a = sum[N][M] - sum[x][M];// 밑 부분 계산
            for (int j = 1; j < M; j++) { // 윗 부분 계산
                long b = sum[x][j];
                long c = sum[x][M] - sum[x][j];
                max = Math.max(max, a*b*c);
            }
            //System.out.println(max);
            // 세로줄이 아래에 있을떄
            /*
             * 1 2 3
             * 4 5 6
             * - - -
             * 7 | 8 9
             * 
             */
            a = sum[x][M]; // 윗 부분 계산
            for (int j = 1; j < M; j++) { // 밑 부분 계산
                long b = sum[N][j] - sum[x][j];
                long c = sum[N][M] - sum[x][M] - (sum[N][j] - sum[x][j]);
                max = Math.max(max, a*b*c);
            }
        }
        /*
         * 4. 긴 세로 1줄 + 짧은 가로 1줄
         */
        for (int y = 1; y < M; y++) { // 세로줄 위치
            // 가로줄이 왼쪽에있을때
            /*
             * 1 | 2 3
             * - |
             * 4 | 5 6
             * 7 | 8 9 
             */
            long a = sum[N][M] - sum[N][y]; // right 부분 계산
            for (int i = 1; i < N; i++) { // left 부분 계산
                long b = sum[i][y];
                long c = sum[N][y] - sum[i][y];
                max = Math.max(max, a*b*c);
            }
            //System.out.println(max);
            // 가로줄이 오른쪽에 있을떄
            /*
             * 1 | 2 3
             *   | - - 
             * 4 | 5 6
             * 7 | 8 9 
             */
            a = sum[N][y]; // left 부분 계산
            for (int i = 1; i < N; i++) { // right 부분 계산
                long b = sum[i][M] - sum[i][y];
                long c = sum[N][M] - sum[N][y] - (sum[i][M] - sum[i][y]);
                max = Math.max(max, a*b*c);
            }
        }
        System.out.println(max);
    }
}
