package algorithm.acmi.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class acmi9081_단어맞추기 {
    static char[] arr, result;
    static LinkedList<Character> list;
    static int cnt = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            arr = br.readLine().toCharArray();
            int idx = -1;
            int num = 0;
            for(int i=arr.length-1; i>=0; i--){
                if(num <= arr[i]){
                    num = arr[i];
                }
                else{
                    idx = i;
                    break;
                }
            }
            if(idx == -1){
                for(int i=0; i<arr.length; i++)
                    System.out.print(arr[i]);
                System.out.println();
                continue;
            }
            //System.out.println(Arrays.toString(arr));
            list = new LinkedList<>();
            for(int i=0; i<arr.length; i++){
                list.add(arr[i]);
            }
            for(int i=arr.length-1; i>=1; i--){
                boolean flag = false;
                for(int j=i-1; j>=0; j--){
                    if(arr[i] > arr[j]){
                        list.add(j,list.remove(i));
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;
            }
            for(int i=0; i<list.size(); i++){
                System.out.print(list.get(i));
            }
            System.out.println();
        }
    }
}
