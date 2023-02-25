package study_230221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj14891_톱니바퀴_G5 {

	/*
	 * 1. 1번~4번 톱니바퀴들은 모두 12시부터 시계방향으로 입력 8개씩 주어진다. -> 생각해야할 인덱스는 1번: 인덱스 2 (2번
	 * 톱니바퀴와 물려있음) <-> 2번: 인덱스 6 (1번 톱니바퀴와 물려있음) 2번: 인덱스 2 (3번 톱니바퀴와 물려있음) <-> 3번:
	 * 인덱스 6 (2번 톱니바퀴와 물려있음) 3번: 인덱스 2 (4번 톱니바퀴와 물려있음) <-> 4번: 인덱스 6 (3번 톱니바퀴와 물려있음)
	 * 
	 * 2. 돌리려는 톱니의 번호가 주어짐 
	 * 3. N극 <-> S극이 마주보고있으면 반대방향으로 돌아감 -> 같은 극끼리는 회전하지 않음. 
	 * 4. 1 <= k <= 100 5. k번 다 돌았을 때 0번째 인덱스의 합을 구하면 된다. -> 1번 톱니바퀴의 12시방향이 N극이면 0점,
	 * S극이면 1점 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점 4번
	 * 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점 첫 번째 정수는 회전시킨 톱니바퀴의 번호, 
	 * 두 번째 정수는 방향이다. 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.
	 */
	static int[][] topni;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		topni = new int[4][8];

		for (int i = 0; i < 4; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < 8; j++) {
				topni[i][j] = tmp.charAt(j) - '0';
			}
		}

		int k = Integer.parseInt(br.readLine());
		for (int t = 0; t < k; t++) {
			st = new StringTokenizer(br.readLine());
			int t_num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			if (direction == 1) {
				clock(t_num);
			} else if (direction == -1) {
				nonclock(t_num);
			}

		}
		int sum = 0;
		sum += topni[0][0] + topni[1][0] * 2 + topni[2][0] * 4 + topni[3][0] * 8;
		System.out.println(sum);

	}

	// 시계방향 일 때
	private static void clock(int t_num) {
		// t_num은 돌아가는 톱니번호
		switch (t_num) {
		case 1:
			if (topni[0][2] == topni[1][6]) {
				right(1);
			} else if (topni[0][2] != topni[1][6] && topni[1][2] == topni[2][6]) {
				right(1);
				left(2);
			} else if (topni[0][2] != topni[1][6] && topni[1][2] != topni[2][6] && topni[2][2] == topni[3][6]) {
				right(1);
				left(2);
				right(3);
			} else if (topni[0][2] != topni[1][6] && topni[1][2] != topni[2][6] && topni[2][2] != topni[3][6]) {
				right(1);
				left(2);
				right(3);
				left(4);
			}

			break;
		case 2:
			if (topni[1][6] != topni[0][2]) {
				left(1);
			}
			if (topni[1][2] == topni[2][6]) {
				right(2);
			} else if (topni[1][2] == topni[2][6]) {
				right(2);
				left(1);
			} else if (topni[1][2] != topni[2][6] && topni[2][2] == topni[3][6]) {
				right(2);
				left(3);
			} else if (topni[1][2] != topni[2][6] && topni[2][2] != topni[3][6]) {
				right(2);
				left(3);
				right(4);
			}

			break;
		case 3:
			if (topni[2][2] != topni[3][6]) {
				left(4);
			}

			if (topni[2][6] == topni[1][2]) {
				right(3);
			} else if (topni[2][6] != topni[1][2] && topni[1][6] == topni[0][2]) {
				right(3);
				left(2);
			} else if (topni[2][6] != topni[1][2] && topni[1][6] != topni[0][2]) {
				right(3);
				left(2);
				right(1);
			}

			break;
		case 4:
			if (topni[3][6] == topni[2][2]) {
				right(4);
			} else if (topni[3][6] != topni[2][2] && topni[2][6] == topni[1][2]) {
				right(4);
				left(3);
			} else if (topni[3][6] != topni[2][2] && topni[2][6] != topni[1][2] && topni[1][6] == topni[0][2]) {
				right(4);
				left(3);
				right(2);
			} else if (topni[3][6] != topni[2][2] && topni[2][6] != topni[1][2] && topni[1][6] != topni[0][2]) {
				right(4);
				left(3);
				right(2);
				left(1);
			}
			break;
		}

		return;
	}

	// 시계방향 일 때
	private static void nonclock(int t_num) {
		// t_num은 돌아가는 톱니번호
		switch (t_num) {
		case 1:
			if (topni[0][2] == topni[1][6]) {
				left(1);
			} else if (topni[0][2] != topni[1][6] && topni[1][2] == topni[2][6]) {
				left(1);
				right(2);
			} else if (topni[0][2] != topni[1][6] && topni[1][2] != topni[2][6] && topni[2][2] == topni[3][6]) {
				left(1);
				right(2);
				left(3);
			} else if (topni[0][2] != topni[1][6] && topni[1][2] != topni[2][6] && topni[2][2] != topni[3][6]) {
				left(1);
				right(2);
				left(3);
				right(4);
			}

			break;
		case 2:
			if (topni[1][6] != topni[0][2]) {
				right(1);
			}
			if (topni[1][2] == topni[2][6]) {
				left(2);
			} else if (topni[1][2] == topni[2][6]) {
				left(2);
				right(1);
			} else if (topni[1][2] != topni[2][6] && topni[2][2] == topni[3][6]) {
				left(2);
				right(3);
			} else if (topni[1][2] != topni[2][6] && topni[2][2] != topni[3][6]) {
				left(2);
				right(3);
				left(4);
			}

			break;
		case 3:
			if (topni[2][2] != topni[3][6]) {
				right(4);
			}

			if (topni[2][6] == topni[1][2]) {
				left(3);
			} else if (topni[2][6] != topni[1][2] && topni[1][6] == topni[0][2]) {
				left(3);
				right(2);
			} else if (topni[2][6] != topni[1][2] && topni[1][6] != topni[0][2]) {
				left(3);
				right(2);
				left(1);
			}

			break;
		case 4:
			if (topni[3][6] == topni[2][2]) {
				left(4);
			} else if (topni[3][6] != topni[2][2] && topni[2][6] == topni[1][2]) {
				left(4);
				right(3);
			} else if (topni[3][6] != topni[2][2] && topni[2][6] != topni[1][2] && topni[1][6] == topni[0][2]) {
				left(4);
				right(3);
				left(2);
			} else if (topni[3][6] != topni[2][2] && topni[2][6] != topni[1][2] && topni[1][6] != topni[0][2]) {
				left(4);
				right(3);
				left(2);
				right(1);
			}
			break;
		}

		return;
	}

	// 오른쪽 => 시계방향으로 돌리는 메소드
	private static void right(int t_num) {
		int tmp = topni[t_num - 1][7];
		for (int i = 6; i >= 0; i--) {
			topni[t_num - 1][i + 1] = topni[t_num - 1][i];
		}
		topni[t_num - 1][0] = tmp;
	}
	
	// 왼쪽 => 반시계방향으로 돌리는 메소드
	private static void left(int t_num) {
		int tmp = topni[t_num - 1][0];
		for (int i = 1; i < 8; i++) {
			topni[t_num - 1][i - 1] = topni[t_num - 1][i];
		}
		topni[t_num - 1][7] = tmp;
	}

}
