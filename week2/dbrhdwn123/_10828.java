package week2;


import java.util.Scanner;

//  static을 붙여야 하는 이유?
public class _10828 {

	static int size = 0;
	static int stack[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int num = sc.nextInt();
		stack = new int[num];

		for (int i = 0; i < num; i++) {

			String str = sc.next();

			switch (str) {
			case "push": {
				push(sc.nextInt());
				break;
			}
			case "pop": {
				sb.append(pop() + "\n");
				break;
			}
			case "size": {
				sb.append(size() + "\n");
				break;
			}
			case "empty": {
				sb.append(empty() + "\n");
				break;
			}
			case "top": {
				sb.append(top() + "\n");
				break;
			}

			}

		}
		System.out.println(sb);
	}

	public static void push(int x) {
		stack[size] = x;
		size++;
	}

	public static int pop() {
		if (size == 0) {
			return -1;
		} else {
			int res = stack[size - 1];
			stack[size - 1] = 0;
			size--;
			return res;
		}
	}

	public static int size() {
		return size;
	}

	public static int empty() {
		if (size == 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int top() {
		if (size == 0) {
			return -1;
		} else {
			int res = stack[size - 1];
			return res;
		}
	}

}

