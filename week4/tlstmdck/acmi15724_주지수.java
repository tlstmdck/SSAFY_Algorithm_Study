package algorithm.acmi.study.week4.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class acmi15724_주지수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N+1][M+1];
        int[][] sum = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(i == 1 && j == 1){
                    sum[i][j] = arr[i][j];
                    continue;
                }
                if(i == 1){
                    sum[i][j] += arr[i][j] + sum[i][j-1];
                    continue;
                }
                if(j == 1){
                    sum[i][j] += arr[i][j] + sum[i-1][j];
                    continue;
                }
                sum[i][j] += arr[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
            }
        }
        
        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            sb.append(sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1] + "\n");
        }
        System.out.println(sb);
    }
}
