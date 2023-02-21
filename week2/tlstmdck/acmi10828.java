package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class acmi10828 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        int[] arr = new int[10000];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int start_idx = 0;
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String menu = st.nextToken();
            switch(menu){
                case "push":{
                    arr[start_idx] = Integer.parseInt(st.nextToken());
                    start_idx++;
                    break;
                }
                case "pop":{
                    arr[start_idx] = 0;
                    start_idx--;
                    break;
                }
                case "size":{
                    System.out.println(start_idx);
                    break;
                }
                case "empty":{
                    break;
                }
                case "top":{
                    break;
                }   
                
            }

        }
    }
}
