package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class belt {
	int use;
	int robot;

	belt(int use, int robot) {
		this.use = use;
		this.robot = robot;
	}
}

public class _컨베이어벨트 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(br.readLine());
		LinkedList<belt> list = new LinkedList<>();

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int res = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n * 2; i++) {

			list.add(new belt(Integer.parseInt(st.nextToken()), 0));
		}

//		for (int i = 0; i < n * 2; i++) {
//			System.out.println(list.get(i).use);
//		}

		while (true) {
			// list 뒤에꺼빼서 앞에 넣기

			res++;
			list.addFirst(list.get(list.size() - 1));
			list.removeLast();
			// System.out.println(list.get(0).use);

			// 돌렸는데 로봇의 자리가 내려가는 부분이면 로봇 떨어뜨리기(-1)
			if (list.get(n - 1).robot == 1) {
				list.get(n - 1).robot--;
			}

			// 로봇을 뒤에부터 확인하면서 로봇 위치+1 자리에 내구성이 남아있다면 로봇을 옮기고 그 위치 내구성 -1
			// 하지만 로봇 위치+1이 내리는 구간이라면 내린다. 또한 위치+1의 내구성이 0이면 가만히 있는다.
			for (int i = n - 2; i >= 0; i--) { // n-1부터는 떨어지는 부분이므로 n-2부터 검사함.
				if (list.get(i).robot == 1) {
					if (list.get(i + 1).robot == 0 && list.get(i + 1).use > 0 && i + 1 != n - 1) {
						list.get(i).robot--; // 본래자리의 로봇의 수는 감소
						list.get(i + 1).robot++; // 넘어갈 자리의 수는 증가
						list.get(i + 1).use--; // 넘어간 부분의 내구도는 감소

					}
					if (list.get(i + 1).robot == 0 && list.get(i + 1).use > 0 && i + 1 == n - 1) {
						list.get(i).robot--; // 본래자리 로봇수 감소
						list.get(i + 1).use--; // 넘어간 자리가 내려가는 자리이므로 내구도만 감소함.

					}

				}
			}

			// 올리는 위치의 내구성이 0이 아니면 로봇을 list앞에 추가한다.
			if (list.get(0).use > 0) {
				list.get(0).robot++;
				list.get(0).use--;
			}
			// 벨트 내구도가 0인것을 체크해서 k번 이상이면 종료한다.
			int inner = 0;
			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).use == 0)
					inner++;
			}
			if (inner >= k)
				break;

		}

		System.out.println(res);
	}

}
