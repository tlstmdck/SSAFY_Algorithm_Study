package algorithm.acmi.study.week3.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class acmi21736_현내기 {
    private static int[][] d = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        Pair start = new Pair(0, 0);
        for(int i=0; i<N; i++){
            String Line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = Line.charAt(j);
                if(map[i][j] == 'I'){
                    start = new Pair(i, j);
                }
            }
        }
        Queue<Pair> qu = new LinkedList<>();
        qu.add(start);
        boolean[][] visited = new boolean[N][M];
        int res = 0;
        while(!qu.isEmpty()){
            Pair cur = qu.remove();
            visited[cur.getX()][cur.getY()] = true;
            for(int i=0; i<4; i++){
                int next_x = cur.getX() + d[i][0];
                int next_y = cur.getY() + d[i][1];
                if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= M)
                    continue;
                if(visited[next_x][next_y] == true || map[next_x][next_y] == 'X')
                    continue;
                visited[next_x][next_y] = true;
                if(map[next_x][next_y] == 'P'){
                    res++;
                    map[next_x][next_y] = 'O';
                }
                qu.add(new Pair(next_x, next_y));
            }
        }
        if(res == 0){
            System.out.println("TT");
        }
        else{
            System.out.println(res);
        }
        
        // for(boolean[] visit : visited){
        //     System.out.println(Arrays.toString(visit));
        // }
        
    }
    static class Pair{
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
