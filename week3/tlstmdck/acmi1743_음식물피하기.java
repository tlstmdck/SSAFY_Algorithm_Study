package algorithm.acmi.study.week3.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class acmi1743_음식물피하기 {
    private static int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
    private static int N;
    private static int M;
    private static int cnt = 0;
    private static int max_cnt = 0;
    private static int[][] map;
    private static boolean[][] visited; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1]; 
        visited = new boolean[N+1][M+1];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(map[i][j] == 1 && visited[i][j] == false){
                    cnt = 0;
                    dfs(i,j);
                    if(max_cnt < cnt){
                        max_cnt = cnt;
                    }
                }
                
            }
        }
        System.out.println(max_cnt);
    }
    public static void dfs(int x, int y){   
        visited[x][y] = true;
        cnt++;
        for(int i=0; i<4; i++){
            int next_x = x + d[i][0];
            int next_y = y + d[i][1];
            if(next_x < 1 || next_x > N || next_y < 1 || next_y > M)
                continue;
            if(visited[next_x][next_y] == true || map[next_x][next_y] == 0)
                continue;
            dfs(next_x,next_y);
        }
    }
}
