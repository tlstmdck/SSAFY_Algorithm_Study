package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class acmi1260 {
    private static ArrayList<ArrayList<Integer>> list;
    private static StringBuilder str;
    private static boolean[] visited;
    public static void dfs(int num){
        visited[num] = true;
        str.append(num + " ");
        ArrayList<Integer> child = list.get(num);
        for(int child_num : child){
            if(visited[child_num] == false){
                dfs(child_num);
            }
            
        }
    }
    public static void bfs(int num){
        Queue<Integer> qu = new LinkedList<>();
        qu.add(num);
        while(!qu.isEmpty()){
            int cur = qu.poll();
            visited[cur] = true;
            str.append(cur + " ");
            ArrayList<Integer> child = list.get(cur);
            for(int child_num : child){
                if(visited[child_num] == false){
                    visited[child_num] = true;
                    qu.add(child_num);
                }
                
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            int output = Integer.parseInt(st.nextToken());
            list.get(input).add(output);
            list.get(output).add(input);
        }
        for(int i=1; i<=N; i++){
            Collections.sort(list.get(i));
        }
        str = new StringBuilder();
        visited = new boolean[N+1];
        dfs(V);
        str.append("\n");
        visited = new boolean[N+1];
        bfs(V);
        System.out.println(str);
    
    }
}
