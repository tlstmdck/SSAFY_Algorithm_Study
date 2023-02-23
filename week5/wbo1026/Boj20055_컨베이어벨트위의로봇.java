package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj20055_컨베이어벨트위의로봇 {

	static List<Integer> list, isRobot; // 컨테이너의 내구도를 담을 리스트, 로봇 있음 = 1, 없음 = 0
	static int N, K, zeroCnt; // 길이, 0의 칸 제한 수, zero >= K 판별시 사용

	static void moveBelt() {
		// 삭제 후 추가 연산을 통해 회전
		int tmp = list.get(2 * N - 1);
		list.remove(2 * N - 1);
		list.add(0, tmp);

		// 로봇도 같이 움직여야하므로
		tmp = isRobot.get(2 * N - 1);
		isRobot.remove(2 * N - 1);
		isRobot.add(0, tmp);

		// 내려오는 자리에 로봇 제거
		isRobot.remove(N - 1);
		isRobot.add(N - 1, 0);
	}

	static void moveRobot() {
		for (int idx = N - 1; idx >= 0; idx--) {
			
			// 현재 자리에 로봇이 있고, 다음 자리가 비어있으며, 다음자리의 내구도가 1이상 일때 -> 이동 가능
			if (isRobot.get(idx) == 1 && isRobot.get(idx + 1) == 0 && list.get(idx + 1) >= 1) {
				// 자기 자리 0으로 변경
				isRobot.remove(idx);
				isRobot.add(idx, 0);

				// 다음 자리에 로봇 상태 추가
				isRobot.remove(idx + 1);
				isRobot.add(idx + 1, 1);

				// 내구도 차감
				int tmp = list.get(idx + 1) - 1;
				list.remove(idx + 1);
				list.add(idx + 1, tmp);
			}

			// 내려오는 자리에 로봇이 있으면 제거
			isRobot.remove(N - 1);
			isRobot.add(N - 1, 0);
		}
	}

	static void putRobot() {
		// 내구도가 1이상이면 로봇 추가
		if (list.get(0) > 0) {
			int tmp = list.get(0) - 1;
			list.remove(0);
			list.add(0, tmp);

			isRobot.remove(0);
			isRobot.add(0, 1);
		}
	}

	static boolean zeroCntCheck() { // 내구도가 0인 벨트 찾기
		int cnt = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 0)
				cnt++;
			if (cnt >= K)
				return true;
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		list = new ArrayList<>(2 * N);
		isRobot = new ArrayList<>(2 * N);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
			isRobot.add(0);
		}

		// end input

		int rnt = 1;
		while (true) {
			moveBelt();
			moveRobot();
			putRobot();

			if (zeroCntCheck())
				break;
			rnt++;
		}

		System.out.println(rnt);
	}
}