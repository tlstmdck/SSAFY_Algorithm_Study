package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 착실한 구현문제임
 처음엔 톱니바퀴 회전->모양 바뀜->바뀐 모양 반영해서 옆의 톱니바퀴 회전.. 이런 프로세스인줄 알았는데 아님
 한번에 회전방향 구해놓고 돌리면 됨.
 * */
public class B14891_톱니바퀴 {
	static int[][] gears = new int[4][8]; // 톱니바퀴들

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < gears.length; i++) {
			String nums = br.readLine();
			for (int j = 0; j < gears[0].length; j++) {
				gears[i][j] = nums.charAt(j)-'0';
			}
		}
		// end input gear

		// start test case
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gear = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			turn(gear - 1, dir);
		}
		// end test case

		// print res
		int sum = 0;
		for (int i = 0; i < gears.length; i++) {
			if (gears[i][0] == 1) {
				sum += 1 << i; // 1점, 2점, 4점, 8점을 비트연산을 통한 2의 제곱수로 표현
			}
		}
		System.out.println(sum);
	}

	// 기어 회전
	// gear: 기어 인덱스 번호
	// dir : 회전 방향. 1: 시계, -1: 반시계
	static void turn(int gear, int dir) {
		int[] turnDirs = new int[4]; // 각 톱니바퀴 회전방향
		turnDirs[gear] = dir; // 처음 톱니바퀴 회전방향 넣기

		// 왼쪽 기어들 회전방향 정하기
		for (int g = gear; g > 0; g--) {
			if (gears[g - 1][2] != gears[g][6]) { // 맞닿는 극이 같지 않아야 회전
				turnDirs[g - 1] = turnDirs[g]*(-1); // 방향 바꿔 회전
			} else { // 달라서 회전하지 않음
				break;
			}
		}
		// 오른쪽 기어들 회전방향 정하기
		for (int g = gear; g < gears.length - 1; g++) {
			if (gears[g + 1][6] != gears[g][2]) { // 맞닿는 극이 같지 않아야 회전
				turnDirs[g + 1] = turnDirs[g]*(-1); // 방향 바꿔 회전
			} else { // 달라서 회전하지 않음
				break;
			}
		}
		
		// 방향에 맞게 회전
		for (int i = 0; i < turnDirs.length; i++) {
			// 시계회전
			if (turnDirs[i] == 1) { 
				int tmp = gears[i][gears[0].length - 1]; // 현재 기어의 마지막 인덱스 값
				for (int j = gears[0].length - 1; j > 0; j--) {
					gears[i][j] = gears[i][j - 1];
				}
				gears[i][0] = tmp;
			} 
			// 반시계 회전
			else if (turnDirs[i] == -1) { 
				int tmp = gears[i][0]; // 현재 기어의 마지막 인덱스 값

				for (int j = 0; j < gears[0].length-1; j++) {
					gears[i][j] = gears[i][j + 1];
				}
				gears[i][gears[0].length-1] = tmp;
			}
		}
	}
}
