package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class acmi11725 {
    private static int[] parents;
    private static ArrayList<ArrayList<Integer>> list;
    // 1에 도착 해야함
        // 1 -> 6, 4
        // 2 -> 4
        // 3 -> 6, 5
        // 4 -> 1, 2, 7
        // 5 -> 3
        // 6 -> 3, 1
        // 7 -> 4

        // 6 -> 1
    public static void dfs(int child, int parent){
        ArrayList<Integer> start = list.get(child);
        for(int i=0; i<start.size(); i++){
            if(start.get(i) != parent){
                parents[start.get(i)] = child;
                dfs(start.get(i),child);
            }
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<Integer>());
        }
        StringTokenizer st;
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            int output = Integer.parseInt(st.nextToken());

            list.get(input).add(output);
            list.get(output).add(input);
        }
        
        parents = new int[N+1];
        dfs(1, N+2);
        for(int i=2; i<= N; i++){
            System.out.println(parents[i]);
        }
    }
}
