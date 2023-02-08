package algorithm.acmi.study.week3.tlstmdck;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class acmi1325_효율적인해킹 {
    private static ArrayList<ArrayList<Integer>> map;
    private static boolean[] visited;
    private static int[] cnt;
    private static int cnt_num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();
        cnt = new int[N+1];
        for(int i=0; i<=N; i++){
            map.add(new ArrayList<Integer>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            map.get(B).add(A);
        }
        for(int i=1; i<=N; i++){
            visited = new boolean[N+1];
            cnt_num = 0;
            dfs(i);
            cnt[i] = cnt_num;
        }
        int max = 0;
        for(int i=1; i<=N; i++){
            if(max < cnt[i])
                max = cnt[i];
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(max == cnt[i])
                sb.append(i + " ");
        }
        System.out.println(sb);
    }

    public static void dfs(int cur){
        ArrayList<Integer> list = map.get(cur);
        visited[cur] = true;
        for(int num : list){
            if(visited[num] == true)
                continue;
            visited[num] = true;
            cnt_num++;
            dfs(num);
            
        }
    }
}