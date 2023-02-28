package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10845_큐 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] queue = new int[10000];
		int tail = 0; // queue의 tail
		int head = 0; // queue의 head
		int size = 0; // queue의 정수 개수

		// ******************** circular queue로 구현 ********************
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int num = 0;
			if (st.hasMoreTokens())
				num = Integer.parseInt(st.nextToken());

			switch (command) {
//			정수 X를 큐에 넣는 연산이다.
			case "push":
				if (tail >= 10000)
					// tail이 인덱스 벗어났으면 위치 0으로 변경 후 push
					tail = 0;
				queue[tail++] = num;
//				if (tail == head) { // tail이 head를 만나면
//					tail -= 1; // 그 앞으로 빼기. 그런데 이런 상황이 벌어질지 ??
//				}
				size++;
				break;
//			큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.	
			case "pop":
				if (queue[head] == 0) { // 헤드가 0이면 비었음
					System.out.println(-1);
					continue;
				}
				System.out.println(queue[head]); // pop된 수
				queue[head] = 0; // pop된 수 없애기
				head++; // head는 다음으로
				if (head >= 10000) { // head가 큐를 벗어나면
					head = 0;
				}
				size--;
				break;
//			큐에 들어있는 정수의 개수를 출력한다.	
			case "size":
				System.out.println(size);
				break;
//			큐가 비어있으면 1, 아니면 0을 출력한다.	
			case "empty":
				if (queue[head] == 0) // 헤드가 0이면 비었음
					System.out.println(1);
				else
					System.out.println(0);
				break;
//			큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.				
			case "front":
				if (queue[head] == 0) // 헤드가 0이면 비었음
					System.out.println(-1);
				else
					System.out.println(queue[head]);
				break;
//			큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
			case "back":
				if (queue[head] == 0) // 헤드가 0이면 비었음
					System.out.println(-1);
				else
					System.out.println(queue[tail - 1]);
				break;
			default:
				break;
			}
		}
	}

}
