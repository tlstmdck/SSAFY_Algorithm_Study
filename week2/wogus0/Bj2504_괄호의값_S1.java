package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Bj2504_괄호의값_S1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String word = br.readLine();
		Stack<Character> stack = new Stack<>();

		int result = 0;
		int tmp = 1;

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '(') {
				stack.push(word.charAt(i));
				tmp *= 2;
			} else if (word.charAt(i) == '[') {
				stack.push(word.charAt(i));
				tmp *= 3;
			} else if (word.charAt(i) == ')') {
				if (stack.isEmpty() || stack.peek() != '(') {
					result = 0;
					break;
				} else if (word.charAt(i - 1) == '(') {
					result += tmp;
				}
				stack.pop();
				tmp = tmp / 2;
			} else if (word.charAt(i) == ']') {
				if (stack.isEmpty() || stack.peek() != '[') {
					result = 0;
					break;
				} else if (word.charAt(i - 1) == '[') {
					result += tmp;
				}
				stack.pop();
				tmp = tmp / 3;
			}

		}
		if(stack.isEmpty()) {
			sb.append(result);
			System.out.println(result);
		}else
			System.out.println(0);

	}

}
