package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12785 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        // end input

        long[][] distance = new long[h][w];

        // 초기 값 넣기
        for (int i = 0; i < h; i++) {
            distance[i][0] = 1L;
        }
        for (int i = 0; i < w; i++) {
            distance[0][i] = 1L;
        }

        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                distance[i][j] = (distance[i][j - 1] + distance[i - 1][j]) % 1000007;
            }
        }

        long ans = distance[y - 1][x - 1] * distance[h - y][w - x] % 1000007;

        System.out.println(ans);
    }
}
