package algorithm.acmi.study.week4.tlstmdck;

import java.util.Scanner;

public class acmi1052_물병 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int res = 0;
        while(true){
            
            String str = Integer.toBinaryString(N);
            int cnt = 0;
            for(int i=0; i<str.length(); i++){
                if(str.charAt(i) =='1')
                    cnt++;
            }
            
            if(K >= cnt){
                break;
            }
            N++;
            res++;
        }
        System.out.println(res);
    }
}
