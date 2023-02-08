package algorithm.acmi.study.week3.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class acmi1012_유기농배추 {
    private static int[][] map;
    private static int M;
    private static int N;
    private static int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
    private static boolean[][] visited;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }
            int cnt = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(visited[i][j] == false && map[i][j] == 1){
                        cnt++;
                        dfs(i,j);
                    }
                }
            }   
            System.out.println(cnt);
        }
    }
    public static void dfs(int x, int y){
        visited[x][y] = true;
        for(int i=0; i<4; i++){
            int next_x = x + d[i][0];
            int next_y = y + d[i][1];
            if(next_x < 0 || next_x >= N || next_y < 0 || next_y >=M)
                continue;
            if(map[next_x][next_y] == 0 || visited[next_x][next_y] == true)
                continue;
            dfs(next_x,next_y);
        }
    }
}
