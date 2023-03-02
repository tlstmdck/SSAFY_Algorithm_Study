package algorithm.acmi.study.week6.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class acmi17144_미세먼지안녕 {
    static int R,C;
    static int[][] map;
    static int[][] tmp;
    static int[][] d = {{0,1},{0,-1},{1,0},{-1,0}};
    static Airclean airclean;
    public static class Dust{
        int x;
        int y;
        int data;
        Dust(int x, int y, int data){
            this.x = x;
            this.y = y;
            this.data = data;
        }
    }
    public static class Airclean{
        int x;
        int y;
        Airclean(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        Queue<Dust> dustqu = new ArrayDeque<>();
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    airclean = new Airclean(i, j);
                }
                if(map[i][j] >= 5){ // 퍼지는 미세먼지 저장
                    dustqu.add(new Dust(i, j, map[i][j]));
                }
            }
        }
        // 시뮬레이션 시작
        for(int t=0; t<T; t++){
            tmp = new int[R][C];
            // 큐에 존재하는 미세먼지 모두 실행
            while(!dustqu.isEmpty()){ 
                dust_spread(dustqu.poll());
            }
            // 모두 동시에 실행되기 때문에 따로 저장한 후 더해줘야함
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    map[i][j] += tmp[i][j];
                }
            }
            // 윗 공기청정기 실행
            up_air_rotate();
            // 아래 공기청정기 실행
            down_air_rotate();

            // 퍼질수 있는 미세먼지 큐에 저장
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(map[i][j] >= 5)
                        dustqu.add(new Dust(i, j, map[i][j]));
                }
            }
        }
        int res = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] != -1){
                    res += map[i][j];
                }
            }
        }
        // for(int [] list : map){
        //     System.out.println(Arrays.toString(list));
        // }
        System.out.println(res);
    }
    // 사방탐색 하여 별도의 tmp 맵에 퍼지는양 저장
    public static void dust_spread(Dust cur){ 
        int cnt = 0 ;
        for(int i=0; i<4; i++){
            int next_x = cur.x + d[i][0];
            int next_y = cur.y + d[i][1];
            if(next_x < 0 || next_x >= R || next_y < 0 || next_y >= C)
                continue;
            if(map[next_x][next_y] == -1)
                continue;
            tmp[next_x][next_y] += cur.data / 5;
            cnt++;
        }
        // 퍼졌으므로 감소해야함
        map[cur.x][cur.y] -= (cur.data/5) * cnt;
    }
    // 반시계
    public static void up_air_rotate(){
        Queue<Integer> qu = new ArrayDeque<>();
        for(int i=airclean.x-2; i>0; i--){
            qu.add(map[i][0]);
        }
        for(int i=0; i<C-1; i++){
            qu.add(map[0][i]);
        }
        for(int i=0; i<airclean.x-1; i++){
            qu.add(map[i][C-1]);
        }
        for(int i=C-1; i>=1; i--){
            qu.add(map[airclean.x-1][i]);
        }
        qu.poll();
        for(int i=airclean.x-2; i>0; i--){
            map[i][0] = qu.poll();
        }
        for(int i=0; i<C-1; i++){
            map[0][i] = qu.poll();
        }
        for(int i=0; i<airclean.x-1; i++){
            map[i][C-1] = qu.poll();
        }
        for(int i=C-1; i>=2; i--){
            map[airclean.x-1][i] = qu.poll();
        }
        map[airclean.x-1][1] = 0;
    }
    // 시계
    public static void down_air_rotate(){
        Queue<Integer> qu = new ArrayDeque<>();
        for(int i=airclean.x+1; i<R-1; i++){
            qu.add(map[i][0]);
        }
        for(int i=0; i<C-1; i++){
            qu.add(map[R-1][i]);
        }
        for(int i=R-1; i>=airclean.x+1; i--){
            qu.add(map[i][C-1]);
        }
        for(int i=C-1; i>=1; i--){
            qu.add(map[airclean.x][i]);
        }
        qu.poll();
        for(int i=airclean.x+1; i<R-1; i++){
            map[i][0] = qu.poll();
        }
        for(int i=0; i<C-1; i++){
            map[R-1][i] = qu.poll();
        }
        for(int i=R-1; i>=airclean.x+1; i--){
            map[i][C-1] = qu.poll();
        }
        for(int i=C-1; i>=2; i--){
            map[airclean.x][i] = qu.poll();
        }
        map[airclean.x][1] = 0;
    }
}
