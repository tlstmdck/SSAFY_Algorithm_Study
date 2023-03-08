package gold3;

import java.io.*;
import java.util.*;

/*
 * 행과 열 한 줄씩 검사하며, 인접한 두 칸이 서로 1씩 차이날 때 경사로 놓기 가능한지 검사.
 * 검사하는 함수에 더 작은 곳의 좌표를 주어, 그곳을 from과  to로 놓음. 
 * 또한 to는 작아지거나 커지거나 해야하므로 operator로 -1또는 +1을 줌
 * 검사 매커니즘: 
 * from과 to의 거리가 L이 될 때까지, 작은 곳에서부터 to를 벌려가며 탐색
 * 반복문을 충족하기 전 to의 범위가 벗어나거나, from과 to의 값이 다르면 안됨.
 * 또한 이전에 다리를 놓았을 경우에도 불가능함.
 * =======검사 매커니즘을 통해 결과가 true로 나왔을 경우 결과값+1
 */
public class B14890_경사로 {
	static int N, L, res;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		// 디버깅용
		for (int[] row : map) {
			System.out.println(Arrays.toString(row));
		}

		// 행 검사
		for (int i = 0; i < N; i++) {
			boolean isPossible = true;
			visit = new boolean[N][N];

			System.out.println("현재 행: " + Arrays.toString(map[i]));

			for (int j = 0; j < N - 1; j++) {
				if (!isPossible)
					break; // 어느 한 곳에서라도 불가능하다는 진단 나왔으면 안됨
				// 한 줄에서 인접한 두 값씩 비교
				int a = map[i][j];
				int b = map[i][j + 1];

				System.out.println("   a: " + a + ", b: " + b);

				if (Math.abs(a - b) == 1) {
					if (a < b) { // 왼쪽 열에 있던 값이 작음
						isPossible = checkRow(i, j, -1); // 왼쪽 위치를 from, to로 두고 탐색 시작
						System.out.println("   a 가 더 작음. 가능여부: " + isPossible);
					} else if (a > b) {
						isPossible = checkRow(i, j + 1, +1);
						System.out.println("   b 가 더 작음. 가능여부: " + isPossible);
					}
				} else if (Math.abs(a - b) > 1) {
					isPossible = false;
				}
			}
			// 만약 한 줄의 값이 다 같다면? 위의 조건문을 방문하지 않았을 것이고 isPossible은 계속 true
			if (isPossible) {
				System.out.println("  이 줄은 가능함");
				res++;
			}
		}
		// 열 검사
		for (int j = 0; j < N; j++) {
			boolean isPossible = true;
			visit = new boolean[N][N];

			int[] debug = new int[N]; // 디버깅용
			for (int i = 0; i < N; i++) {
				debug[i] = map[i][j];
			}
			System.out.println("현재 열: " + Arrays.toString(debug));

			for (int i = 0; i < N - 1; i++) {
				if (!isPossible)
					break; 

				int a = map[i][j];
				int b = map[i + 1][j];

				System.out.println("   a: " + a + ", b: " + b);

				if (Math.abs(a - b) == 1) {
					if (a < b) {
						isPossible = checkCol(i, j, -1);
						System.out.println("   a 가 더 작음. 가능여부: " + isPossible);
					} else if (a > b) {
						isPossible = checkCol(i + 1, j, +1);
						System.out.println("   b 가 더 작음. 가능여부: " + isPossible);
					}
				} else if (Math.abs(a - b) > 1) {
					isPossible = false;
				}
			}
			if (isPossible) {
				System.out.println("  이 줄은 가능함");
				res++;
			}
		}
		System.out.println(res);
	}

	static boolean checkRow(int i, int j, int op) {
		int from = j;
		int to = j;
		boolean isPossible = true;

		// from과 to의 거리가 L이 될 때까지, 작은 곳에서부터 to를 벌려가며 탐색
		while (Math.abs(from - to) != L) {
			// to의 범위가 벗어났다면 -> 어쨌든 거리를 충족하기 전에 끝났으므로 망함
			if (to < 0 || to >= N) {
				System.out.println("     범위를 벗어남");
				isPossible = false;
				break;
			}

			// 거리를 충족하기 전 to의 위치에 있는 값이 from과 다르다면 -> 망함
			if (map[i][to] != map[i][from]) {
				System.out.println("     to와 from 다름");
				isPossible = false;
				break;
			}

			// 위에 조건 다 만족했는데(가능하지만)방문했던 경우(이미 다리 놓아짐)
			if (visit[i][to]) {
				System.out.println("      이미 다리를 놓았음");
				isPossible = false;
				break;
			}
			visit[i][to] = true;
			to += op;
		}
		return isPossible;
	}

	static boolean checkCol(int i, int j, int op) {
		int from = i;
		int to = i;
		boolean isPossible = true;

		// from과 to의 거리가 L이 될 때까지, 작은 곳에서부터 to를 벌려가며 탐색
		while (Math.abs(from - to) != L) {
			// to의 범위가 벗어났다면 -> 어쨌든 거리를 충족하기 전에 끝났으므로 망함
			if (to < 0 || to >= N) {
				isPossible = false;
				break;
			}

			// 거리를 충족하기 전 to의 위치에 있는 값이 from과 다르다면 -> 망함
			if (map[to][j] != map[from][j]) {
				isPossible = false;
				break;
			}

			// 위에 조건 다 만족했는데(가능하지만)방문했던 경우(이미 다리 놓아짐)
			if (visit[to][j]) {
				isPossible = false;
				break;
			}
			visit[to][j] = true;
			to += op;
		}
		return isPossible;
	}
}
