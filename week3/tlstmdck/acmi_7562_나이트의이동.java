package algorithm.acmi.study.week3.tlstmdck;

import java.io.*;
import java.util.*;

public class acmi_7562_나이트의이동 {
    private static int[][] d = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
    private static Pair start;
    private static Pair end;
    private static int I;
    private static int[][] map;

    public static double get_distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2) );
    }
    
    public static void bfs(){
        boolean[][] visited = new boolean[I][I];
        Queue<Pair> st = new LinkedList<>();
        st.add(start);
        while(!st.isEmpty()){
            Pair cur = st.poll();
            double cur_distance = get_distance(cur.getX(), cur.getY(), end.getX(), end.getY());
            if(cur_distance == 0){
                System.out.println(cur.getCnt());
                return;
            }
            for(int i=0; i<8; i++){
                Pair next = new Pair(cur.getX()+d[i][0], cur.getY()+d[i][1]);
                if(next.getX() < 0 || next.getX() >= I || next.getY() < 0 || next.getY() >= I)
                    continue;
                if(visited[next.getX()][next.getY()] == false){
                    next.setCnt(cur.getCnt()+1);
                    visited[next.getX()][next.getY()] = true;
                    st.add(next);
                }
                
            }
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t=0; t<T; t++){
            I = Integer.parseInt(br.readLine());
            map = new int[I][I];
            st = new StringTokenizer(br.readLine());
            int start_x = Integer.parseInt(st.nextToken());
            int start_y = Integer.parseInt(st.nextToken());
            start = new Pair(start_x, start_y);
            st = new StringTokenizer(br.readLine());
            int end_X = Integer.parseInt(st.nextToken());
            int end_y = Integer.parseInt(st.nextToken());
            end = new Pair(end_X, end_y);
            bfs();
        }
    }
    public static class Pair{
        private int x;
        private int y;
        private int cnt;

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        Pair(int x, int y){
            this.x = x;
            this.y = y;
            this.cnt = 0;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
}
