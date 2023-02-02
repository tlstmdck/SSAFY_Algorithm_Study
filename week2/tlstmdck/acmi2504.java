package algorithm.acmi.study.week2.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class acmi2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Line = br.readLine();
        Stack<Character> st = new Stack<>();
        int sum = 0;
        int last = 1;
        boolean flag = false;
        for (int i = 0; i < Line.length(); i++) {
            char cur = Line.charAt(i);
            if (cur == '(') {
                st.push(cur);
                last *= 2;
            } else if (cur == '[') {
                st.push(cur);
                last *= 3;
            } else {
                if (cur == ')') {
                    if (!st.empty() && st.peek() == '(') {

                    } else {
                        flag = true;
                        break;
                    }
                    if (Line.charAt(i - 1) == '(') {
                        sum += last;
                    }
                    last /= 2;
                    st.pop();
                } else if (cur == ']') {
                    if (!st.empty() && st.peek() == '[') {

                    } else {
                        flag = true;
                        break;
                    }
                    if (Line.charAt(i - 1) == '[') {
                        sum += last;
                    }
                    last /= 3;
                    st.pop();
                } else {
                    flag = true;
                    break;
                }
            }
        }
        if (!st.empty() || flag == true) {
            System.out.println(0);
        } 
        if (flag == false && st.empty() == true)
            System.out.println(sum);
    }
}
