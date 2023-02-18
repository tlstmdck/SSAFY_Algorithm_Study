package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 각 지점으로 도착할 수 있는 경우를 DP배열로 구현

public class Main {
	
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int w= Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		dp = new long[h+1][w+1];
		
		
		for(int i=1; i<=h; i++) {
			for(int j=1; j<=w; j++) {
//				 DP 초기값 대입
				if (i == 1 || j == 1) {
					dp[i][j] = 1;
				} else {
//					현재 위치 값은 현재의 아래와 현재 왼쪽의 위치의 합과 같음
					dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000007;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int tx = Integer.parseInt(st.nextToken());
		int ty = Integer.parseInt(st.nextToken());
		
		
		
//		 1,1 부터 토스트집까지 가는 경우의 수
		long toToast = dp[ty][tx];
//		 토스트집에서 학교까지 가는 경우의 수
		long toSchool = dp[h-ty+1][w-tx+1];
		System.out.println(toToast*toSchool%1000007);
		
	}// end main
}