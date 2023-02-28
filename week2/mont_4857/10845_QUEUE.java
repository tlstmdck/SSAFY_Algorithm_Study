import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] queue = new int[10001];
		int head = 1;
		int tail = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String func = st.nextToken();
			int num = -1;
			if(st.hasMoreTokens()) {
				num = Integer.parseInt(st.nextToken());
			}
			
			switch (func) {
			case "push":
					queue[++tail] = num;
				break;
			case "front":
				if(head <= tail) {
					System.out.println(queue[head]);
				}else {
					System.out.println(-1);
				}
				break;
			case "back":
				if(head <= tail) {
					System.out.println(queue[tail]);
				}else {
					System.out.println(-1);
				}
				break;
			case "pop":
				if(head <= tail) {
					System.out.println(queue[head++]);
				}else {
					System.out.println(-1);
				};
				break;
			case "empty":
				if(head <= tail ) {
					System.out.println(0);
				}else {
					System.out.println(1);
				};
				break;
			case "size":
				System.out.println(tail-head+1);
				break;
			
			}
			
		}
		br.close();
	}

}