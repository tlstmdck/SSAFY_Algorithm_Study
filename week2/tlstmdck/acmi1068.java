package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmi1068 {
    private static int cnt;
    private static int[] arr;
    private static boolean[] visited;
    public static void dfs1(int num){
        arr[num] = -2;
        visited[num] = true;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == num){
                dfs1(i);
            }
        }
    }
    public static void dfs2(int num){
        visited[num] = true;
        boolean flag = false;
        if(arr[num] != -2){
            for(int i=0; i<arr.length; i++){
                if(arr[i] == num && visited[i] == false){
                    dfs2(i);
                    flag = true;
                }
            }
            if(flag == false)
                cnt++;
        }
        return;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = 0;
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == -1)
                root = i;
        }
        int remove = Integer.parseInt(br.readLine());
        dfs1(remove);
        
        dfs2(root);
        System.out.println(cnt);
    }
}
