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

public class acmi14502_연구소2 {
    static int N,M,after,before;
    static int min = Integer.MAX_VALUE;
    static int[][] map, time, arr;
    static boolean[][] visited;
    static List<Pair> virus = new ArrayList<>();
    static Queue<Pair> qu;
    static boolean flag = false;
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
        map = new int[N][N];
        arr = new int[M][2];
        visited = new boolean[N][N];
        qu = new LinkedList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 1){
                    before++;
                }
            }
        }
        comb(0,0,0);
        if(!flag)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    public static void comb(int idx ,int start, int size){
        if(size == M){
            // for(int[] list : arr){
            //     System.out.print(Arrays.toString(list));
            // }
            // System.out.println();
            for(int[] list : arr){
                map[list[0]][list[1]] = 3;
                virus.add(new Pair(list[0], list[1]));
            }
            go_check();
            
            for(int[] list : arr){
                map[list[0]][list[1]] = 2;
                virus.clear();
            }
           
            return;
        }
        for(int i=start; i<N*N; i++ ){
            int x = i/N;
            int y = i%N;
            if(map[x][y] != 2)
                continue;
            arr[idx][0] = x;
            arr[idx][1] = y;
            comb(idx+1, i+1, size+1);
        }
    }
    public static void go_check(){
        for(Pair start : virus){
            visited[start.x][start.y] = true;
            qu.add(start);
        }
        visited = new boolean[N][N];
        time = new int[N][N];
        bfs();
        
        int time_max = 0;
        after = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                //System.out.print(time[i][j]);
                if(visited[i][j] == true && map[i][j] != 1){
                    after++;
                }
                if(time_max < time[i][j]){
                    time_max = time[i][j];
                }
            }
            //System.out.println();
        }
        if(before == after){
            flag = true;
            min = Math.min(min, time_max);
        }
        // for(int i=0; i<N; i++){
        //     for(int j=0; j<N; j++){
        //         System.out.print(map[i][j]);
        //     }
        //     System.out.println();
        // }
        
    }
    public static void bfs(){
        while(!qu.isEmpty()){
            Pair cur = qu.poll();
            visited[cur.x][cur.y] = true;
            for(int i=0; i<4; i++){
                int next_x = cur.x + d[i][0];
                int next_y = cur.y + d[i][1];
                if(next_x < 0 || next_x >= N || next_y < 0 || next_y >= N)
                    continue;
                if(visited[next_x][next_y] == true)
                    continue;
                if(map[next_x][next_y] != 1 && map[next_x][next_y] != 3){
                    visited[next_x][next_y] = true;
                    time[next_x][next_y] += time[cur.x][cur.y] + 1;
                    qu.add(new Pair(next_x, next_y));   
                }
                
                
            }
        }
    }
}
