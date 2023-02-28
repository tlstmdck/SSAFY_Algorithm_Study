package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _주사위굴리기 {
	static int arr1[] = new int[3];// 동,서
	static int arr2[] = new int[4];// 남,북
	static int dicex, dicey, n, m, onedir, twodir;
	static int area[][];
	static int di[] = { 0, 0, -1, 1 }; // 동 서 북 남
	static int dj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();
		onedir = 1;
		twodir = 1;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dicex = Integer.parseInt(st.nextToken());
		dicey = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		area = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < k; i++) {

			int kk = Integer.parseInt(st.nextToken());
			// System.out.println(kk);

			if (kk == 1) { // 동
				changeone(1, 3, kk);

				if (onedir == 1) {
					arr2[onedir] = arr1[onedir];
				}
				System.out.print("출력" + " " + checkone() + "|");
				sb.append(checkone());
				print();

			}

			if (kk == 2) { // 서
				changeone(-1, 3, kk);
				if (twodir == 1) {
					arr2[onedir] = arr1[onedir];
				}
				System.out.print("출력" + " " + checkone() + "|");
				sb.append(checkone());
				print();

			}

			if (kk == 3) { // 북
				changetwo(-1, 4, kk);
				if (twodir == 1) {
					arr1[twodir] = arr2[twodir];
				}
				System.out.print("출력" + " " + checktwo() + "|");
				sb.append(checktwo());
				print();

			}

			if (kk == 4) { // 남
				changetwo(1, 4, kk);
				if (twodir == 1) {
					arr1[twodir] = arr2[twodir];
				}
				System.out.print("출력" + " " + checktwo() + "|");
				sb.append(checktwo());
				print();

			}

		}

		System.out.println(sb);

	}

	public static void changeone(int dirplus, int div, int kk) {

		onedir = (onedir + dirplus) % div;
		if (onedir < 0)
			onedir += 3;
		System.out.println("동서dir : " + onedir);
		System.out.print("x : " + dicex + " ");
		System.out.println("y : " + dicey);
		int nexti = dicex + di[kk - 1];
		System.out.print("nexti" + nexti + " ");

		int nextj = dicey + dj[kk - 1];
		System.out.println("nextj" + nextj);
		if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < m) {
			dicex = nexti;
			dicey = nextj;
			if (area[nexti][nextj] == 0)
				area[nexti][nextj] = arr1[onedir];
			else if (area[nexti][nextj] > 0) {
				arr1[onedir] = area[nexti][nextj];

				area[nexti][nextj] = 0;
			}

		}

	}

	public static void changetwo(int dirplus, int div, int kk) {

		twodir = (twodir + dirplus) % div;
		if (twodir < 0)
			twodir += 4;
		System.out.println("남북dir : " + twodir);
		System.out.print("x : " + dicex + " ");
		System.out.println("y : " + dicey);
		int nexti = dicex + di[kk - 1];
		System.out.print("nexti" + nexti + " ");

		int nextj = dicey + dj[kk - 1];
		System.out.println("nextj" + nextj);
		if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < m) {
			dicex = nexti;
			dicey = nextj;
			if (area[nexti][nextj] == 0)
				area[nexti][nextj] = arr2[twodir];
			else if (area[nexti][nextj] > 0) {
				arr2[twodir] = area[nexti][nextj];

				area[nexti][nextj] = 0;
			}

		}

	}

	public static int checkone() {

		if (onedir == 1)
			return arr2[3];
		if (onedir == 0)
			return arr1[2];
		if (onedir == 2)
			return arr1[0];
		if (onedir == 3)
			return arr1[1];

		return 0;
	}

	public static int checktwo() {

		if (twodir == 1)
			return arr2[3];
		if (twodir == 0)
			return arr2[2];
		if (twodir == 2)
			return arr2[0];
		if (twodir == 3)
			return arr2[1];

		return 0;
	}

	public static void print() {
		for (int i : arr1) {
			System.out.print(i + " ");
		}
		System.out.print("|");
		for (int j : arr2) {
			System.out.print(j + " ");
		}

	}

}