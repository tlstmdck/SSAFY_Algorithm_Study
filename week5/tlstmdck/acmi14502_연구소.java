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
/*
 * 기본적으로 0중에 3개 조합으로 꺼낸후 벽으로 변경
 * 그 후 바이러스 퍼트린 후 다시 복구하면서 반복
 */
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
                // 바이러스 에서 bfs진행해야 하므로 start지점 저장
                if(map[i][j] == 2){
                    virus.add(new Pair(i, j));
                }
            }
        }
        comb(0,0,0);
        System.out.println(max);
    }
    //arr을 통해 map이 0인 곳 3개 조합
    public static void comb(int idx ,int start, int size){
        if(size == 3){
            //해당 좌표 1로 변경
            for(int[] list : arr){
                map[list[0]][list[1]] = 1;
            }
            go_check();
            // check했으므로 다시 복구
            for(int[] list : arr){
                map[list[0]][list[1]] = 0;
            }
           
            return;
        }
        // 편의성을 위해 i는 모든 좌표의 범위를 나타내도록함
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
        // bfs시작 전에 시작점 qu 저장 및 visited 배열 초기화
        for(Pair start : virus){
            qu.add(start);
        }
        visited = new boolean[N][M];
        
        // bfs 진행
        bfs();
        
        // bfs수행이 끝난 후 전염되지 않은 구역 계산
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0 && visited[i][j] == false){
                    after++;
                }
            }
        }

        // 최대값 계산
        max = Math.max(max, after) ;
        after = 0;
    }

    // bfs 진행
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
