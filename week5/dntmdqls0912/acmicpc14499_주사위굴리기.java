import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 주사위가 각 방향으로 이동할 때, 각 면의 값이 어떻게 바뀌는지 찾는것이 문제의 관건
 * 일정한 규칙을 찾으려 했지만, 규칙을 찾을 수 없어 각 경우에 대해 모두 그려봄
 */

public class Main {

    static int N, M, row, col;	// row, col : 주사위의 현재 위치
    static int[][] map;	// 지도 정보를 담은 2차원 배열 (0,0)이 서북 끝임
    static int[] dice;	// 주사위의 위, 뒤, 오른쪽, 왼쪽, 앞, 아래 를 각각 순서대로 담음
    static StringBuilder sb;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dice = new int[7];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            rollDice(Integer.parseInt(st.nextToken()));
        }
        System.out.println(sb);
    }
    /*
     * 주사위의 이동에따라 배열의 숫자를 변화시킴
     * 		-------
     * 	   /| 1   /|		 	 2 - 뒤
     * 	  /4|----/3|     왼쪽 - 4  1 - 위  3 - 오른쪽
     * 	  | /5---|-/			 5 - 앞
     * 	  |/   6 |/ 			 6 - 아래
     * 	  --------
     */
    static void rollDice(int dir) {
        /*
         * 주사위 동쪽으로 이동
         * 주사위 숫자의 변화
         * 위 -> 오른쪽 -> 아래 -> 왼쪽 -> 위
         */
        if (dir == 1) {
            if (!isValid(row, col+1)) return;

            int temp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
            col++;

            if (map[row][col] == 0) {
                map[row][col] = dice[6];
            } else {
                dice[6] = map[row][col];
                map[row][col] = 0;
            }
            sb.append(dice[1]).append("\n");

            /*
             * 주사위 서쪽으로 이동
             * 주사위 숫자의 변화
             * 위 -> 왼쪽 -> 아래 -> 오른쪽 -> 위
             */
        } else if (dir == 2) {
            if (!isValid(row, col-1)) return;

            int temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
            col--;

            if (map[row][col] == 0) {
                map[row][col] = dice[6];
            } else {
                dice[6] = map[row][col];
                map[row][col] = 0;
            }
            sb.append(dice[1]).append("\n");

            /*
             * 주사위 북쪽으로 이동
             * 주사위 숫자의 변화
             * 위 -> 뒤 -> 아래 -> 앞 -> 위
             */
        } else if (dir == 3) {
            if (!isValid(row-1, col)) return;

            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
            row--;

            if (map[row][col] == 0) {
                map[row][col] = dice[6];
            } else {
                dice[6] = map[row][col];
                map[row][col] = 0;
            }
            sb.append(dice[1]).append("\n");

            /*
             * 주사위 북쪽으로 이동
             * 주사위 숫자의 변화
             * 위 -> 앞 -> 아래 -> 뒤 -> 위
             */
        } else {
            if (!isValid(row+1, col)) return;

            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
            row++;

            if (map[row][col] == 0) {
                map[row][col] = dice[6];
            } else {
                dice[6] = map[row][col];
                map[row][col] = 0;
            }
            sb.append(dice[1]).append("\n");
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}