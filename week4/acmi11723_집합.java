package algorithm.acmi.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class acmi11723_집합 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int bitset = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            String menu = st.nextToken();
            switch(menu){
                case "add":{
                    int num = Integer.parseInt(st.nextToken());
                    bitset |= (1 << (num-1));
                    break;
                }
                case "remove":{
                    int num = Integer.parseInt(st.nextToken());
                    bitset = bitset & ~(1 << (num-1));
                    break;
                }
                case "check":{
                    int num = Integer.parseInt(st.nextToken());
                    if((bitset & (1 << (num-1))) != 0){
                        sb.append(1 + "\n");
                    }
                    else{
                        sb.append(0 + "\n");
                    }
                    break;
                }
                case "toggle":{
                    int num = Integer.parseInt(st.nextToken());
                    bitset ^= (1 << (num -1));
                    break;
                }
                case "all":{
                    bitset |= (~0);
                    break;
                }
                case "empty":{
                    bitset &= 0;
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}
