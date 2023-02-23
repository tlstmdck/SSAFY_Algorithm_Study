package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class acmi4358 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        HashMap<String,Integer> map = new HashMap<>();
        int size = 0;
        // while(str.length() != 0 && str != null){
        //     map.put(str, map.getOrDefault(str, 0) + 1);
        //     size++;
        //     str = br.readLine();
        // }
        while(true){
            map.put(str, map.getOrDefault(str, 0) + 1);
            size++;
            str = br.readLine();
            if(str == null || str.length() == 0)
                break;
        }
        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        StringBuilder ans = new StringBuilder();
        for(String name : list){
            double num = (double)map.get(name) / (double)size;
            num = num * 100;
            ans.append(name + " " + String.format("%.4f",num));
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
