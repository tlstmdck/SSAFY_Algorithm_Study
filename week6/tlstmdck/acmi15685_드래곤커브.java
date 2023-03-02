package algorithm.acmi.study.week6.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class acmi15685_드래곤커브 {
    static int[][] map;
    static int[][] d = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

    static class Dragon {
        int x;
        int y;
        int g;
        ArrayList<Integer> list;

        Dragon(int x, int y, int g) {
            this.x = x;
            this.y = y;
            this.g = g;
            list = new ArrayList<>();
        }
        /*
         * 0
         * (0세대) 0 -> 1 
         * (1세대) 0 1 -> 2 1 
         * (2세대) 0 1 2 1 -> 2 3 2 1 
         * N세대 = 2^N <= 1024
         * 기존 리스트에 있던 원소들을 뒤집어서 다시 리스트에 저장
         */
        public void next_generatoin() {
            Stack<Integer> st = new Stack<>();
            for (int num : list) {
                if (num == 3) {
                    st.add(0);
                } else
                    st.add(num + 1);
            }
            while (!st.isEmpty()) {
                list.add(st.pop());
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Dragon[] dragon = new Dragon[N];
        map = new int[101][101];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragon[i] = new Dragon(x, y, g);
            dragon[i].list.add(start);
        }
        // 각 드래곤 커브의 세대에 대한 방향 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < dragon[i].g; j++) {
                dragon[i].next_generatoin();
            }
        }
        // 방향으로 이동하면서 1 저장
        for (int i = 0; i < N; i++) {
            map[dragon[i].y][dragon[i].x] = 1;
            for (int dir : dragon[i].list) {
                dragon[i].x += d[dir][1];
                dragon[i].y += d[dir][0];
                map[dragon[i].y][dragon[i].x] = 1;
            }
        }
        int res = 0;
        // for(int[] arr : map){
        // System.out.println(Arrays.toString(arr));
        // }
        
        // 네 꼭짓점 측정하여 1이면 사각형 완성
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
