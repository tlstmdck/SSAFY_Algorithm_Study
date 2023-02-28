package silver1;

import java.util.Arrays;
import java.util.Scanner;

public class B12785_토쟁이의등굣길 {
	static int w, h;
	static int[][] dp;
	static int[] toast;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		w = sc.nextInt();
		h = sc.nextInt();
		int toast_x = sc.nextInt();
		int toast_y = sc.nextInt();
		toast = new int[]{ Math.abs(toast_y-h),  toast_x-1};
		dp = new int[h][w];
		
		dp[h-1][0] = 0;
		for (int i = 0; i < h; i++) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < toast_x; i++) {
			dp[h-1][i] = 1;
		}
		
		
		//dp[i][j] = dp[i-1][j] + dp[i][j-1]
		for (int i = h-2; i >= 0; i--) {
			for (int j = 1; j < toast_x; j++) {
				dp[i][j] = dp[i+1][j] + dp[i][j-1];
			}
		}
		
		for (int[] arr: dp) {
			System.out.println(Arrays.toString(arr));
		}

		
	}

}
