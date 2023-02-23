package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15724 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로

        int[][] arr = new int[N + 1][M + 1];
        int[][] sum = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i][j - 1] + arr[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());

            int sY = Integer.parseInt(st.nextToken());
            int sX = Integer.parseInt(st.nextToken());
            int eY = Integer.parseInt(st.nextToken());
            int eX = Integer.parseInt(st.nextToken());
            int ans = 0;

            for (int i = sY; i <= eY; i++) {
                ans += sum[i][eX] - sum[i][sX-1];
            }

            sb.append(ans + "\n");
        }

        System.out.println(sb.toString());
    }
}
