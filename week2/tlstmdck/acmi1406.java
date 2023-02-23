package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class acmi1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Line = br.readLine();
        int N = Integer.parseInt(br.readLine());
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for(int i=0; i<Line.length(); i++){
            left.push(Line.charAt(i));
        }
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String menu = st.nextToken();
            if(menu.equals("L") && !left.empty()){
                right.push(left.pop());
            }else if(menu.equals("P")){
                char newword = st.nextToken().charAt(0);
                left.push(newword);
            }else if(menu.equals("D") && !right.empty()){
                left.push(right.pop());
            }else if(menu.equals("B") && !left.empty()){
                left.pop();
            }
        }
        StringBuilder str = new StringBuilder();
        Stack<Character> tmp = new Stack<>();
        while(!left.empty()){
            tmp.push(left.pop());
        }
        while(!tmp.empty()){
            str.append(tmp.pop());
        }
        while(!right.empty()){
            str.append(right.pop());
        }
        System.out.println(str);
    }
}
