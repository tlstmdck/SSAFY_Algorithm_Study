import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    사각형을 나누는 경우를 일반화 시키면 6가지 경우가 있음
    이를 각각의 좌표에 대해 모든 경우를 계산해서 탐색해봐야 최대값을 알 수 있는 Brute-force 문제임
 */

public class Main {

    static int N, M;

    /*
        가능한 최대값 = 9*2500/3 = 7500, 7500^3 > Integer.MAX_VALUE
        따라서 long으로 계산해줘야 한다....(2시간동안 찾았음)
     */
    static long result = 0;
    static int[][] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];

        // 누적합 계산
        for(int i=1; i<=N; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=1; j<=M; j++) {
                arr[i][j] = (line[j-1] - '0') + arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1];
            }
        }

        /*
            첫번째 배열부터 맨 끝 배열-1 까지
         */
        for(int i=1; i<N; i++) {
            for(int j=1; j<M; j++) {
                calThreeDiv(i, j);
            }
        }

        /*
            최소 2칸이 남아 있어야 하므로 최대 가로 or 세로 -2 까지 루프
         */
        for(int i=1; i<=M-2; i++) {
            calcColDiv(i);
        }

        for(int i=1; i<=N-2; i++) {
            calcRowDiv(i);
        }

        System.out.println(result);

    }

    public static void calThreeDiv(int i, int j) {
        long rst = 0, square1, square2, square3;

        /*
         *   cur | square2
         *   ----------------
         *     square3
         */
        square1 = arr[i][j]; // cur 영역
        square2 = arr[i][M] - square1; 	// cur 오른쪽 영역
        square3 = arr[N][M] - square1 - square2; 	// cur 아래 영역

        rst = Math.max(rst, square1 * square2 * square3);


        /*   cur
         *   ----------------
         *   square2| square3
         *       	|
         */
        square1 = arr[i][M];	// cur 이 포함된 가로 영역
        square2 = arr[N][j] - arr[i][j];	// cur 아래 영역
        square3 = arr[N][M] - square1 - square2;   // cur 아래 오른쪽 영역

        rst = Math.max(rst, square1 * square2 * square3);


        /*   cur 	|
         *   -------| square3
         *   square2|
         *       	|
         */
        square1 = arr[i][j];	// cur 영역
        square2 = arr[N][j] - square1;	// cur 아래 영역
        square3 = arr[N][M] - square1 - square2;	// cur 오른쪽 영역

        rst = Math.max(rst, square1 * square2 * square3);


        /*   cur | square2
         *   	 |-----------
         *   	 | square3
         *       |
         */
        square1 = arr[N][j];	// cur 이 포함된 세로 영역
        square2 = arr[i][M] - arr[i][j];	// cur 오른쪽 위 영역
        square3 = arr[N][M] - square1 - square2;	// cur 오른쪽 아래 영역

        rst = Math.max(rst, square1 * square2 * square3);

        result = Math.max(rst, result);

    }

    public static void calcRowDiv(int row) {
        long rst = 0, square1, square2, square3;

        /*			cur
         * ---------------------- row
         * 		  square2
         * ---------------------- i
         * 		  square3
         * ---------------------- border
         */

        square1 = arr[row][M];
        for(int i=row+1; i<=N-1; i++) {
            square2 = arr[i][M] - square1;
            square3 = arr[N][M] - square1 - square2;

            rst = Math.max(rst, square1 * square2 * square3);
        }
        result = Math.max(rst, result);
    }

    public static void calcColDiv(int col) {
        long rst = 0, square1, square2, square3;

        /*	   col		i	  border
         * 		|		|		|
         * 		|		|		|
         * cur	|square2|square3|
         * 		|		|		|
         * 		|		|		|
         */

        square1 = arr[N][col];
        for(int i=col+1; i<=M-1; i++) {
            square2 = arr[N][i] - square1;
            square3 = arr[N][M] - square1 - square2;

            rst = Math.max(rst, square1 * square2 * square3);
        }
        result = Math.max(rst, result);
    }
}