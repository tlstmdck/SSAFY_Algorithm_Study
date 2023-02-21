package week1;

import java.util.Scanner;

public class _13305 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		long distance[] = new long[n - 1];
		long sum = 0;
		for (int i = 0; i < n - 1; i++) {
			distance[i] = sc.nextInt();
		}

		long price[] = new long[n];

		for (int i = 0; i < n; i++) {
			price[i] = sc.nextInt();
		}

		boolean flag = false;

		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {

				sum += price[i] * distance[j - 1];

				if (price[i] > price[j]) {
					i = j;
				}

				if (j == n - 2) {
					sum += price[i] * distance[j];
					flag = true;
					break;
				}

			}
			if (flag == true) {
				break;
			}
		}

		System.out.println(sum);
	}

}