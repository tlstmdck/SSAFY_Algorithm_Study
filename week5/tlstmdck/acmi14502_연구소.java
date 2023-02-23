package algorithm.acmi.study.week5.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class acmi14502_연구소 {
    static int N,M,after, before, max;
    static int[][] map;
    static int[][] arr;
    static boolean[][] visited;
    static List<Pair> virus = new ArrayList<>();
    static Queue<Pair> qu;
    static int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};

    static class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        arr = new int[3][2];
        visited = new boolean[N][M];
        qu = new LinkedList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    virus.add(new Pair(i, j));
                }
                else if(map[i][j] == 0){
                    before++;
                }
            }
        }
        comb(0,0,0);
        // for(int i=0; i<N; i++){
        //     for(int j=0; j<M; j++){
        //         if(map[i][j] == 0){
        //             comb(i,j,0,0);
        //         }
        //     }
        // }
        System.out.println(max);
    }

    public static void comb(int idx ,int start, int size){
        if(size == 3){
            // for(int[] list : arr){
            //     System.out.print(Arrays.toString(list));
            // }
            // System.out.println();
            for(int[] list : arr){
                map[list[0]][list[1]] = 1;
            }
            go_check();
            
            for(int[] list : arr){
                map[list[0]][list[1]] = 0;
            }
           
            return;
        }
        for(int i=start; i<N*M; i++ ){
            int x = i/M;
            int y = i%M;
            if(map[x][y] != 0)
                continue;
            arr[idx][0] = x;
            arr[idx][1] = y;
            comb(idx+1, i+1, size+1);
        }
    }
    public static void go_check(){
        for(Pair start : virus){
            qu.add(start);
        }
        visited = new boolean[N][M];
        bfs();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0 && visited[i][j] == false){
                    after++;
                }
            }
        }
        max = Math.max(max, after) ;
        after = 0;
    }
    public static void bfs(){
        
        while(!qu.isEmpty()){
            Pair cur = qu.poll();
            visited[cur.x][cur.y] = true;
            for(int i=0; i<4; i++){
                int next_x = cur.x + d[i][0];
                int next_y = cur.y + d[i][1];
                if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= M)
                    continue;
                if(visited[next_x][next_y] == true || map[next_x][next_y] != 0)
                    continue;
                qu.add(new Pair(next_x, next_y));
                
            }
        }
    }
}
