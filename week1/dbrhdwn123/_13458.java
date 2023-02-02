package week1;

import java.util.Scanner;

public class _13458 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		long big = 0;
		long small = 0;
		int n = sc.nextInt();
		int  testper[] = new int[n];
		for (int i = 0; i < n; i++) {
			testper[i] = sc.nextInt();
		}
		int bigmanager = sc.nextInt();
		int smallmanager = sc.nextInt();

		for (int i = 0; i < n; i++) {

			if (testper[i] >= bigmanager * 1) {
				big++;

				testper[i] =  (testper[i] - bigmanager * 1);
				// System.out.println(testper[i]);
				if (testper[i] > 0) {
					if (testper[i] % smallmanager != 0) {
						small += (testper[i] / smallmanager) + 1;
					} else {
						small += (testper[i] / smallmanager);
					}
				}
			}

			else {
				big++;
			}

			// System.out.println(big);
			// System.out.println(small);

		}
		System.out.println(big + small);

	}

}