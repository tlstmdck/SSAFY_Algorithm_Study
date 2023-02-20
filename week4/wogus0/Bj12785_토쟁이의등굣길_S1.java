package wogus0;

import java.util.Scanner;

public class Bj12785_토쟁이의등굣길_S1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();
		int t_w = sc.nextInt();
		int t_h = sc.nextInt();

		long dp[][] = new long[h][w];

		for (int i = 0; i < h; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i < w; i++) {
			dp[0][i] = 1;
		}

		for (int i = 1; i < h; i++) {
			for (int j = 1; j < w; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000007;
			}
		}

		System.out.println(dp[t_h - 1][t_w - 1] * dp[h - t_h][w - t_w] % 1000007);

	}

}