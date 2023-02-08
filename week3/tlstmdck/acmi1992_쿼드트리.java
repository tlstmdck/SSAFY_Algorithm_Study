package algorithm.acmi.study.week3.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class acmi1992_쿼드트리 {
    private static int[][] map;
    private static StringBuilder sb;
    public static void func(int N, int x, int y){
        int start = map[x][y];
        boolean flag = false;
        for(int i=x; i<x+N; i++){
            for(int j=y; j<y+N; j++){
                if(map[i][j] != start){
                    sb.append("(");
                    flag = true;
                    break;
                }
            }
            if(flag == true)
                break;
        }
        if(flag == false){
            sb.append(start);
            return;
        }
        func(N/2, x, y);
        func(N/2, x, y+N/2);
        func(N/2, x+N/2, y);
        func(N/2, x+N/2, y+N/2);
        sb.append(")");
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            String Line = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = Line.charAt(j) - '0';
            }
        }
        sb = new StringBuilder();
        func(N,0,0);
        System.out.println(sb);
    }
   
}
