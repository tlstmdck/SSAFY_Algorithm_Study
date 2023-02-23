package silver4;

import java.util.*;
import java.io.*;

public class B10828_스택 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] stack = new int[10000];
		int top = 0; // stack의 top
		int size = 0; // stack의 정수 개수
		
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int num = 0;
			if (st.hasMoreTokens())
				num = Integer.parseInt(st.nextToken());
			
			switch (command) {
//		push X: 정수 X를 스택에 넣는 연산이다.
			case "push":
				stack[top++] = num;
				size++;
				break;
//		pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
			case "pop":
				if(stack[0]==0) {
					System.out.println(-1);
					continue;
				}
				
				System.out.println(stack[top-1]);
				stack[top-1] = 0;
				top--;
				size--;
				break;
//		size: 스택에 들어있는 정수의 개수를 출력한다.
			case "size":
				System.out.println(size);
				break;
//		empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
			case "empty":
				if(stack[0]==0)
					System.out.println(1);
				else
					System.out.println(0);
				break;
//		top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
			case "top":
				if(stack[0]==0) {
					System.out.println(-1);
					continue;
				}
				System.out.println(stack[top-1]);
				break;
			}
		}
		
	}

}
