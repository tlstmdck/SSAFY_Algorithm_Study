package algorithm.acmi.study.week3.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class acmi1926_그림 {
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
    private static int N,M;
    private static int art_size = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int art_cnt = 0;
        int max_art_size = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j] == false && map[i][j] == 1){
                    art_cnt++;
                    art_size = 0;
                    dfs(i,j);
                    if(max_art_size < art_size)
                        max_art_size = art_size;
                }
            }
        }
        System.out.println(art_cnt);
        System.out.println(max_art_size);
    }
    public static void dfs(int x, int y){
        visited[x][y] = true;
        art_size++;
        for(int i=0; i<4; i++){
            int next_x = x + d[i][0];
            int next_y = y + d[i][1];
            if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= M)
                continue;
            if(visited[next_x][next_y] == true || map[next_x][next_y] == 0)
                continue;
            dfs(next_x,next_y);
        }
    }
    public static class Pair{
        private int x;
        private int y;
        
        Pair(int x, int y){
            this.x = x;
            this.y = y;

        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
}
