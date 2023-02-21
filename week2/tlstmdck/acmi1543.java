package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class acmi1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();
        int index = 0;
        int cnt = 0;
        while(index <= first.length()-second.length()){
            boolean flag = false;
            for(int j=0; j<second.length(); j++){
                if(first.charAt(index+j) != second.charAt(j)){
                    flag = true;
                    break;
                }
            }
            if(flag == true){
                index++;
            }
            if(flag == false){
                index = index + second.length();
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
