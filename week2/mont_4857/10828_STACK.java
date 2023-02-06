import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] stack = new int[10001];
		int top = -1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String func = st.nextToken();
			int num = -1;
			if (st.hasMoreTokens()) {
				num = Integer.parseInt(st.nextToken());
			}

			switch (func) {
			case "push":
				stack[++top] = num;
				break;
			case "pop":
				if (top<0) {
					System.out.println(-1);
				} else {
					System.out.println(stack[top--]);
				}
				break;
			case "top":
				if (top<0) {
					System.out.println(-1);
				} else {
					System.out.println(stack[top]);
				}
				break;
			case "empty":
				if (top<0) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case "size":
				System.out.println(top+1);
				break;

			}

		}
		br.close();
	}

}