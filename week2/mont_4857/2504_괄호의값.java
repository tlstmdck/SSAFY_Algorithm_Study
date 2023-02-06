import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.next();

        int tmp = 1;
        int result = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < in.length(); i++) {
            switch (in.charAt(i)) {

                case '(':
                    stack.push('(');
                    tmp *= 2;
                    break;

                case '[':
                    stack.push('[');
                    tmp *= 3;
                    break;

                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        result = 0;
                        tmp =0;
                        break;
                    }

                    if (in.charAt(i - 1) == '(') result += tmp;
                    stack.pop();
                    tmp /= 2;
                    break;

                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        result = 0;
                        tmp = 0;
                        break;
                    }

                    if (in.charAt(i - 1) == '[') result += tmp;
                    stack.pop();
                    tmp /= 3;
                    break;
            }
        }

        System.out.println(!stack.isEmpty() ? 0 : result);
    }

}