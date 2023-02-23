import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 곱셈의 분배 법칙을 이용
// (()[[]])([])를 예로 들어보자
// 2 * (2 + 3 * 3) + 2 * 3 =
// 2*2 + 2*3*3 + 2*3
// 이는 곱하는 수가 먼저 들어온 (와 [와 같게되며,
// 각 곱셈의 마지막은 바로 이전 인덱스의 짝을 만났을 때 이다.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stk = new Stack<>();

//        최종 결과 값
        int result = 0;
//        곱셈값을 일시 저장할 버퍼
        int buffer = 1;

        boolean isMultiplex = false;
//        입력받은 수식 배열
        char[] formula = br.readLine().toCharArray();


        for(int i=0; i<formula.length; i++) {
            if (formula[i] == '(') {
                buffer *= 2;
                stk.push(formula[i]);

            } else if (formula[i] == '[') {
                buffer *= 3;
                stk.push(formula[i]);

            } else if (formula[i] == ')') {
                if (stk.empty() || stk.peek() != '(') {
                    System.out.println(0);
                    return;

                } else if (formula[i-1] == '(') {
                    result += buffer;
                }

                buffer /= 2;
                stk.pop();

//           formula[i] == ']' 인 경우
            } else {
                if (stk.empty() || stk.peek() != '[') {
                    System.out.println(0);
                    return;

                } else if (formula[i-1] == '[') {
                    result += buffer;
                }
                buffer /= 3;
                stk.pop();
            }
        }

        if (stk.empty()) {
            System.out.println(result);
        }
        else {
            System.out.println(0);
        }
    }
}