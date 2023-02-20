package wogus0;

import java.util.Scanner;

public class Bj1052_물병_S1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();
		int cnt = 0;
		int water = 0;

		if (n <= k) {
			System.out.println(0);
			return;
		}

		while (true) {
			cnt = 0;
			int copy_n = n + water;
			while (copy_n != 0) {
				if (copy_n % 2 == 1) {
					cnt++;
				}
				copy_n /= 2;
			}
			if (cnt <= k)
				break;
			water++;
		}
		System.out.println(water);

	}

}