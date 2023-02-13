package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1992 {

    static int[][] video;
    static StringBuilder sb;

    static void makeQuad(int startX, int startY, int size) {

        if (isQuad(startX, startY, size)) {
            sb.append(video[startX][startY]);
        } else {
            sb.append("(");

            int reSize = size / 2;

            // 왼위
            makeQuad(startX, startY, reSize);
            // 오위
            makeQuad(startX, startY + reSize, reSize);
            // 왼아
            makeQuad(startX + reSize, startY, reSize);
            // 오아
            makeQuad(startX + reSize, startY + reSize, reSize);

            sb.append(")");
        }
    }

    static boolean isQuad(int startX, int startY, int size) {

        int select = video[startX][startY];

        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (select != video[i][j])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        video = new int[N][N];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                video[i][j] = str.charAt(j) - '0';
            }
        }
        // end input

        makeQuad(0, 0, N);

        System.out.println(sb);
    }

}
