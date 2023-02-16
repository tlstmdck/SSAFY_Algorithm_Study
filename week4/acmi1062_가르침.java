package algorithm.acmi.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class acmi1062_가르침 {
    static boolean[] isused;
    static int N,K;
    static ArrayList<Integer> list;
    static int[] result, bit_arr;
    static String[] arr;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new String[N];
        isused = new boolean[27];
        bit_arr = new int[N];
        for(int i=0; i<N; i++){
            String Line = br.readLine();
            arr[i] = Line;
            for(int j=0; j<arr[i].length(); j++){
                bit_arr[i] |= (1 << (arr[i].charAt(j)-'a'));
                isused[arr[i].charAt(j)-'a'] = true;
            }
        }
        list = new ArrayList<>();
        for(int i=0; i<27; i++){
            if(isused[i]){
                list.add(i);
            }
        }
        //System.out.println(list.size());
        if(list.size() < K){
            K = list.size();
        }
        result = new int[K];
        comb(0,0);
        System.out.println(max);
    }
    public static void comb(int idx, int start){
        if(idx == K){
            int bitset = 0;
            int cnt = 0;
            for(int num : result){
                bitset |= (1<<num);
            }
            for(int bit : bit_arr){
                if((bit & bitset) == bit){
                    cnt ++;
                }
            }
            max = Math.max(cnt, max);
            return;
        }
        for(int i=start; i<list.size(); i++){
            result[idx] = list.get(i);
            comb(idx+1, i+1);
        }
    }
}
