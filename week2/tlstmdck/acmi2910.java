package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;


/*
 * 소요시간: 약 1시간
 * 
 * 풀이 방법
 * 두개의 해시맵(map, comemap)을 통하여 map에는 갯수, comemap에는 들어온 순서를
 * 삽입한 후 각 키의 리스트를 정렬할때 두개의 맵을 이용하여 정렬 후 출력
 */
public class acmi2910 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        HashMap<Integer,Integer> map = new HashMap<>();
        HashMap<Integer,Integer> comemap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(map.get(num) == null){
                map.put(num, 1);
                comemap.put(num, i);
            }
            else{
                map.put(num, map.get(num)+1);
            }
        }
        /*
         * 맵의 key, value를 뒤집으려 하였으나 갯수가 중복인 숫자들 때문에 
         * 하면 안됨
         */
        // HashMap<Integer,Integer> rmap = new HashMap<>();
        // Iterator<Entry<Integer,Integer>> entries = map.entrySet().iterator();
        // while(entries.hasNext()){
        //     Map.Entry<Integer,Integer> entry = entries.next();
        //     rmap.put(entry.getValue(), entry.getKey());
        // }
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<Integer>(){
            @Override
            public int compare(Integer arg0, Integer arg1) {
                if(map.get(arg1) == map.get(arg0))
                    return comemap.get(arg0) - comemap.get(arg1);
                return map.get(arg1) - map.get(arg0);
            }
        });
        StringBuilder str = new StringBuilder();
        for(Integer list_num : list){
            int size = map.get(list_num);
            for(int i=0; i<size; i++){
                str.append(list_num + " ");
            }
        }
        System.out.println(str);
    }
}
