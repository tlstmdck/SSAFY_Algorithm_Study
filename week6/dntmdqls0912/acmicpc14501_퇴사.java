package codingTest;

import java.util.Scanner;

public class acmicpc14501_퇴사 {
	
	static int N;
	static int[] dp;	// dp 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		dp = new int[N+1];
		// dp[0] = 0; dp 점화식 초기값
		
		
		for(int i=1; i<=N; i++) { // 1일쨰부터 N일째 까지
			int days = sc.nextInt();
			int cost = sc.nextInt();
			
			// 전날까지 받을수 있는 가격과 현재 받을수 있는 가격 중 큰 것을 가져옴
			dp[i] = Math.max(dp[i-1], dp[i]);
			
			if (i+days-1 <= N)
				// 오늘 상담을 받을 경우, 상담이 끝나는 날의 금액은 기존값과 어제까지의 금액 + 상담 금액 중 큰것을 선택
				dp[i+days-1] = Math.max(dp[i+days-1], dp[i-1] + cost);
		}
		
		System.out.println(dp[N]);
	}
}
