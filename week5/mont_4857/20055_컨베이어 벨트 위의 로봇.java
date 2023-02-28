import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		boolean[] belt = new boolean[2 * N];
		int[] dur = new int[2 * N];
		
		// 회전 수
		int result = 0;
		
		// 컨베이어 벨트 시작점
		int head = 0;     
		
		// 컨베이어 벨트 내리는 점
		int tail = N - 1;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < dur.length; i++) {
			dur[i] = Integer.parseInt(st.nextToken());
		}
		
		// 컨베이어 벨트 실행
		while (true) {
			// 벨트 이동 1회 증가
			result++;
			
			// 벨트를 돌렸을 때 시작점(head - 1)
			head = (head + (2 * N - 1)) % (2 * N);

			// 벨트를 돌렸을 때 내리는 점 (tail - 1)
			tail = (tail + (2 * N - 1)) % (2 * N);
			
			// 내리는 점의 로봇 제거
			belt[tail] = false;
			
			// (tail - 1) -> (head) 까지 순차적으로 로봇 이동 가능 시 이동
			for (int i = 0; i < (2 * N - 2); i++) {
				int idx = (tail + (2 * N - 1) - i) % (2 * N);
				int nextIdx = (idx + 1) % (2 * N);
				
				// 현재 벨트 위에 로봇이 없는 경우
				// 내구도가 0이하인 경우
				// 다음 벨트 위에 로봇이 존재하는 경우
				if (!belt[idx] || dur[nextIdx] <= 0 || belt[nextIdx])
					continue;
				
				// 현재 로봇을 한 칸 이동 후 다음 벨트 내구도 감소
				belt[nextIdx] =true;
				belt[idx]= false;
				dur[nextIdx]--;
			}
			
			// 로봇의 내리는 곳 로봇 제거
			belt[tail] = false;
			
			// 로봇을 올리는 벨트의 내구도가 0 이상인 경우 로봇 올리기
			if (dur[head] > 0) {
				belt[head] = true;
				dur[head]--;
			}
			
			// 내구도가 K개 이상인지 확인
			if (dur_zero(dur, K)) {
				break;
			}

		}

		System.out.println(result);
	}
	
	// 
	static boolean dur_zero(int[] arr, int k) {
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0)
				cnt++;
		}
		return cnt >= k;
	}
}
