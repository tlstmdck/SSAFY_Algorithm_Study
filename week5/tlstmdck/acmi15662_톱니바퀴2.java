package algorithm.acmi.study.week5.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class acmi15662_톱니바퀴2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<LinkedList<Integer>> list = new ArrayList<>();
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<=T; i++){
            list.add(new LinkedList<Integer>());
        }   
        for(int i=1; i<=T; i++){
            String Line = br.readLine();
            for(int j=0; j<Line.length(); j++){
                list.get(i).add(Line.charAt(j) - '0');
            }
        }
        int K = Integer.parseInt(br.readLine());
        int sum = 0;
        for(int k=0; k<K; k++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int[] visited = new int[T+1];
            if(R == -1){
                visited[D] = -1;
            }
            else{
                visited[D] = 1;
            }
            for(int i=D; i<T; i++){
                if(visited[i] != 0 && list.get(i).get(2) != list.get(i+1).get(6)){
                    visited[i+1] = visited[i] * -1;
                }
            }
            for(int i=D; i>1; i--){
                if(visited[i] != 0 && list.get(i).get(6) != list.get(i-1).get(2)){
                    visited[i-1] = visited[i] * -1;
                }   
            }
            for(int i=1; i<=T; i++){
                if(visited[i] == -1){
                    list.get(i).addLast(list.get(i).removeFirst());
                }
                else if(visited[i] == 1){
                    list.get(i).addFirst(list.get(i).removeLast());
                }
            }
            // for(LinkedList<Integer> llist : list){
            //     for(Integer num : llist){
            //         System.out.print(num);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
        }
        for(int i=1; i<=T; i++){
            if(list.get(i).get(0) == 1)
                sum++;
        }
        System.out.println(sum);
    }
}
