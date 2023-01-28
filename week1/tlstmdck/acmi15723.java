package algorithm.acmi.study.week1.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class acmi15723 {
    //dfs를 이용하여 a->b->c->d 탐색
    public static boolean dfs(ArrayList<ArrayList<Integer>> graph, int input, int output){
        Stack<Integer> st = new Stack<>();
        st.push(input);
        while(!st.empty()){
            int cur = st.peek();
            st.pop();
            ArrayList<Integer> list = graph.get(cur);
            for (Integer element : list) {
                if(output == element){
                    return true;
                }
                else{
                    st.push(element);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for(int i=0; i<26; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0; i<n; i++){
            String Line = br.readLine();
            int input = Line.charAt(0) - 'a';
            int output = Line.charAt(5) - 'a';
            graph.get(input).add(output);
        }
        /*
         * a -> b
         * b -> c
         * c -> d
         * d -> 
         * ...
         * z ->
         */
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for(int j=0; j<m; j++){
            String Line = br.readLine();
            int input = Line.charAt(0) - 'a';
            int output = Line.charAt(5) - 'a';
            if(dfs(graph, input, output) == true)
                System.out.println("T");
            else
                System.out.println("F");
        }
    }
}
