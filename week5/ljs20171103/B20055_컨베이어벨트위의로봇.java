package gold5;

import java.io.*;
import java.util.*;

public class B20055_컨베이어벨트위의로봇 {
	static int N;
	static int K;
	static int[] belt;
	static boolean[] robots; // 로봇이 있는 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 벨트길이
		K = Integer.parseInt(st.nextToken()); // 내구도가 0인 개수 제한
		belt = new int[2 * N];
		robots = new boolean[N];

		st = new StringTokenizer(br.readLine()); // 벨트입력용
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

//		System.out.println("====초기=====");
//		System.out.println("belt: " + Arrays.toString(belt));
//		System.out.println("로봇 위치: " + Arrays.toString(robots));

		// 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
		for (int i = 1; i < 100000000; i++) {
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			rotate();

			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
			// 만약 이동할 수 없다면 가만히 있는다.
			// 2-1. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며,
			// 그 칸의 내구도가 1 이상 남아 있어야 한다.
			moveIfCan();

			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (belt[0] > 0 && !robots[0]) {
				belt[0] -= 1; // 로봇 적재
				robots[0] = true;
				if (belt[0] == 0)
					K--;
			}
//			System.out.println("====0번에 적재=====");
//			System.out.println("belt: " + Arrays.toString(belt));
//			System.out.println("로봇 위치: " + Arrays.toString(robots));

			if (K <= 0) {
				System.out.println(i);
				return;
			}
		}
	}

	// 컨베이어벨트 회전. 시계방향임
	static void rotate() {
//		System.out.println("====rotate=====");
		// 로봇 한 칸씩 이동
		for (int i = robots.length - 1; i > 0; i--) {
			robots[i] = robots[i - 1];
		}
		robots[0] = false;
		if (robots[N - 1])
			robots[N - 1] = false; // 내리는자리에 로봇 있으면 없애버림

		//벨트회전
		int temp = belt[belt.length - 1];
		for (int i = belt.length - 1; i > 0; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = temp;

//		System.out.println("belt: " + Arrays.toString(belt));
//		System.out.println("로봇 위치: " + Arrays.toString(robots));
	}

	// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며,
	// 그 칸의 내구도가 1 이상 남아 있어야 한다.
	static void moveIfCan() {
		// 가장 먼저 벨트에 올라간 로봇부터 내리는 위치 전까지 본다
		for (int i = N - 2; i >= 0; i--) { // i는 벨트번호. 내리는위치 이전부터 앞으로 탐색
			if (robots[i] && !robots[i + 1]) { // i번에 위치한 로봇 있고 다음 칸에 로봇 없으면
				if (belt[i + 1] > 0) { // 다음칸 내구도 남았으면
//					System.out.println("====move if can=====");
//					System.out.println("go to i: " + (i + 1));
					robots[i] = false; // 다음칸으로 이동
					robots[i + 1] = true;
					if (robots[N - 1])
						robots[N - 1] = false; // 내리는자리에 로봇 있으면 없애버림

					belt[i + 1] -= 1;
					if (belt[i + 1] == 0)
						K--; // 0됐으니 K카운트 셈

					i++; // 지금 로봇이 다음칸으로 이동했으므로 반복문이 돌면 이미 움직인 걸 또 움직일 수 있음 => 인덱스를 한번 더 건너뜀

//					System.out.println("belt: " + Arrays.toString(belt));
//					System.out.println("로봇 위치: " + Arrays.toString(robots));
				}
			}
		}
	}
}