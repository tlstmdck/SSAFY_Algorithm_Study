package wogus0;

import java.util.Scanner;

public class Bj13458_시험감독_B2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			arr[i] = a;
		}

		int b = sc.nextInt();
		int c = sc.nextInt();
		long cnt = 0;

		for (int i = 0; i < n; i++) {
			if (arr[i] - b <= 0) {
				cnt += 1;
				continue;
			} else {
				arr[i] = arr[i] - b;
				if (arr[i] % c != 0) {
					cnt += 1;
				}
				cnt += arr[i] / c + 1;
			}
		}

		System.out.println(cnt);

	}

}