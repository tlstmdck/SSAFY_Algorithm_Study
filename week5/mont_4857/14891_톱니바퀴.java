import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 톱니바퀴 List 배열 생성
		 * 톱니바퀴는 1부터 4번까지 생성
		 * 톱니바퀴의 0번 인덱스부터 12시 방향
		 * 톱니바퀴의 오른쪽 : 2번 인덱스
		 * 톱니바퀴의 왼쪽 : 6번 인덱스
		 * ===========================================================
		 *  	      0            0            0            0
     			    7   1	 7   1	      7   1	   7   1
  			  6       2    6       2    6       2    6       2
     			    5   3        5   3        5   3        5   3
        		      4            4            4            4
        	==========================================================
		*/
		
		ArrayList<Character>[] saw = new ArrayList[5];
		for (int i = 1; i < saw.length; i++) {
			String str = br.readLine();
			saw[i] = new ArrayList<>();
			for (int j = 0; j < str.length(); j++) {
				saw[i].add(str.charAt(j));
			}
		}
		// 회전 횟수
		int n = Integer.parseInt(br.readLine());
		
		// 회전 횟수만큼 실행
		for (int T = 0; T < n; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 회전해야하는 톱니바퀴 체크하는 배열
			boolean[] change = new boolean[5];
			
			// 톱니바퀴 번호
			int num = Integer.parseInt(st.nextToken());
			
			// 톱니바퀴 방향
			int dir = Integer.parseInt(st.nextToken());
			
			// 제공되는 변호는 무조건 회전을 하니 true로 설정
			change[num] = true;
			
			/* 
			 * 오른쪽 비교
			 * 톱니바퀴가 4개로 고정되므로 오른쪽은 3번까지만 확인
			 * 톱니바퀴의 오른쪽과 오른쪽 톱니바퀴의 왼쪽이 같은 경우 반복문 종료
			*/
			for (int j = num; j < 4; j++) {
				if (saw[j].get(2) == saw[j + 1].get(6)) {
					break;
				}
				change[j + 1] = true;
			}
			
			/* 
			 * 왼쪽 비교
			 * 톱니바퀴가 4개로 고정되므로 왼쪽은 1번까지만 확인
			 * 톱니바퀴의 왼쪽과 왼쪽 톱니바퀴의 오른쪽이 같은 경우 반복문 종료
			*/
			for (int j = num; j > 1; j--) {
				if (saw[j].get(6) == saw[j - 1].get(2)) {
					break;
				}
				change[j - 1] = true;
			}
			
			/*
			 * 톱니바퀴 회전
			 * 1번부터 회전해야되는 톱니바퀴 회전
			 */
			for (int j = 1; j < change.length; j++) {
				if (!change[j])
					continue;
				
				/*
				 * 톱니바퀴의 회전 방향이 처음 톱니바퀴의 회전 방향과 같고, 시계 방향인 경우
				 * 배열의 마지막 값을 배열의 첫 값으로 변경
				 */
				if (num % 2 == j % 2 && dir > 0) {
					saw[j].add(0,saw[j].remove(7));
				}
				/*
				 * 톱니바퀴의 회전 방향이 처음 톱니바퀴의 회전 방향과 같고, 반시계 방향인 경우
				 * 배열의 첫 값을 마지막 값으로 변경
				 */
				else if (num % 2 == j % 2 && dir < 0) {
					saw[j].add(saw[j].remove(0));
				}
				/*
				 * 톱니바퀴의 회전 방향이 처음 톱니바퀴의 회전 방향과 다르고, 시계 방향인 경우
				 * 배열의 첫 값을 마지막 값으로 변경
				 */
				else if (num % 2 != j % 2 && dir > 0) {
					saw[j].add(saw[j].remove(0));
				}
				/*
				 * 톱니바퀴의 회전 방향이 처음 톱니바퀴의 회전 방향과 다르고, 반시계 방향인 경우
				 * 배열의 마지막 값을 배열의 첫 값으로 변경
				 */
				else {
					saw[j].add(0,saw[j].remove(7));
				}
			}
			
		}
		// 점수를 담을 변수
		int result = 0;
		
		/*
		 *  톱니바퀴 12시의 값 == 배열의 0번 인덱스 값
		 */		
		for (int i = 1; i < 5; i++) {
			result += saw[i].get(0) == '1' ? 1<<(i-1) : 0;
		}
		System.out.println(result);

	}
}
