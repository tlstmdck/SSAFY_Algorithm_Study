package algorithm.acmi.study.week3.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmi1303_전쟁 {
    private static int N;
    private static int M;
    private static char[][] map;
    private static boolean[][] visited;
    private static int cnt = 0;
    private static int sum_w = 0;
    private static int sum_b = 0;
    private static int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        visited = new boolean[M][N];
        for(int i=0; i<M; i++){
            String Line = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = Line.charAt(j);
            }
        }
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j] == false){
                    char team = map[i][j];
                    cnt = 0;
                    dfs(i,j,team);
                    if(team == 'W')
                        sum_w += Math.pow(cnt,2);
                    else
                        sum_b += Math.pow(cnt,2);
                }
            }
        }
        System.out.println(sum_w +" " + sum_b);
    }
    public static void dfs(int x, int y, char team){
        visited[x][y] = true;
        cnt++;
        for(int i=0; i<4; i++){
            int next_x = x + d[i][0];
            int next_y = y + d[i][1];
            if(next_x < 0 || next_x >= M || next_y < 0 || next_y >= N)
                continue;
            if(visited[next_x][next_y] == true || map[next_x][next_y] != team)
                continue;
            dfs(next_x, next_y, team);
        }
    }
}