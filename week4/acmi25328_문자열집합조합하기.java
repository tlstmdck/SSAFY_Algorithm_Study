package algorithm.acmi.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class acmi25328_문자열집합조합하기 {
    static char[] result;
    static int k = 0;
    static Map<String,Integer> map;
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //1~17 인덱싱
        map = new HashMap<>();
        list = new ArrayList<>();
        String X = br.readLine();
        String Y = br.readLine();
        String Z = br.readLine();
        k = Integer.parseInt(br.readLine());
        result = new char[k];
        comb(X, 0, 0);
        comb(Y,0,0);
        comb(Z,0,0);
        Collections.sort(list);
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
        if(list.size() == 0){
            System.out.println(-1);
        }
    }
    public static void comb(String str, int idx, int start){
        if(idx == k){
            String res = String.valueOf(result);
            map.put(res, map.getOrDefault(res, 0)+1);
            if(map.get(res) == 2){
                list.add(res);
                map.remove(res);
            }
            return;
        }
        for(int i=start; i<str.length(); i++){
            result[idx] = str.charAt(i);
            comb(str, idx+1, i+1);
        }
    }
}
