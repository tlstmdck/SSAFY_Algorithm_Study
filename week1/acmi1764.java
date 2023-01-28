package algorithm.acmi.study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class acmi1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String,Boolean> map = new HashMap<>();  //듣도 못한 사람들 HashMap

        for(int i=0; i<N; i++){
            map.put(br.readLine(), true);
        }
        int cnt = 0;
        StringBuilder res = new StringBuilder(); //시간 최적화를 위해 다 만들고 출력

        String[] arr = new String[M];   //보도 못한 사람들 Array
        for(int i=0; i<M; i++){
            arr[i] = br.readLine();
        }

        Arrays.sort(arr);   //사전순 출력을 위한 정렬

        for(int i=0; i<M; i++){
            if(map.getOrDefault(arr[i],false) == true){ //HashMap에 존재하지 않을시 Default값인 false 반환
                cnt++;
                res.append(arr[i]);
                res.append("\n");
            }
        }

        System.out.println(cnt);
        System.out.println(res);
    }
}
