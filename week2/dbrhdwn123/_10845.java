package week1;

import java.util.Scanner;

public class _10845 {
	static int size = 0;
	static int que[];
	static int fron = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		que = new int[num];
		StringBuilder sb = new StringBuilder();

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
			case "front": {
				sb.append(front() + "\n");
				break;
			}
			case "back": {
				sb.append(back() + "\n");
				break;
			}
			}
		}
		System.out.println(sb);
	}

	public static void push(int num) {
		que[size] = num;
		size++;

	}

	public static int pop() {
		if (size() == 0) {
			return -1;
		} else {
			int res = que[fron];
			fron++;
			return res;
		}

	}

	public static int size() {
		return size - fron;
	}

	public static int empty() {
		if (size() == 0) {
			return 1;

		} else {
			return 0;
		}
	}

	public static int front() {
		if (size() == 0) {
			return -1;
		} else {
			return que[fron];
		}
	}

	public static int back() {
		if (size() == 0) {
			return -1;
		} else {
			return que[size - 1];
		}
	}

}
