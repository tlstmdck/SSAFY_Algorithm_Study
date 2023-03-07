package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmicpc14890_경사로 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input
		
		int path = 0;
		
		// 가로 행에 대한 횡단 가능 여부 확인
		boolean[][] takePlace = new boolean[N][N];
		for(int i=0; i<N; i++) {
			boolean pathable = true;	// 통과 가능 여부 flag
			
			for(int j=0; j<N-1; j++) {
				int adder = 0;
				int anglePos; // 경사로 위치
				
				adder = arr[i][j] - arr[i][j+1]; // 현재 위치와 바로 오른쪽 위치간 높이 차이
				
				if (adder != 0) { // 0이 아닌경우 높낮이 차가 발생
					if (Math.abs(adder) > 1)  { // 차이가 1 초과이면 즉시 횡단 불가능
						pathable = false; 
						break;
					}
					
					// 경사로를 놓을 위치 지정 (adder가 1이면 현재위치가 높으므로 다음위치에, 아니면 현재 위치에 경사로 세팅)
					anglePos = adder == 1 ? j+1 : j; 
					int pointHeight = arr[i][anglePos]; // 경사로 기준점의 높이
					
					for(int k=0; k<L; k++) { // 요구 길이만큼 길이 평탄한지 확인
						if (0 > anglePos || 	// 배열의 범위를 벗어났거나
								anglePos >= N ||
								arr[i][anglePos] != pointHeight || // 높이가 다르거나
								takePlace[i][anglePos] // 이미 경사로로 사용되고 있으면
						) {
							pathable = false;
							break;
						} else {
							takePlace[i][anglePos] = true;	// 위치 경사로로 사용 여부 등록
						}
						anglePos += adder;
					}
				}
				if (!pathable) break;
			}
			if (pathable) path++;
		}
		
		// 세로 열에 대한 횡단 가능 여부 확인
		takePlace = new boolean[N][N];
		for(int j=0; j<N; j++) {
			boolean pathable = true;
			
			for(int i=0; i<N-1; i++) {
				int adder = 0;
				int anglePos;
				
				adder = arr[i][j] - arr[i+1][j];
				
				if (adder != 0) {
					if (Math.abs(adder) > 1)  {
						pathable = false;
						break;
					}
					
					anglePos = adder == 1 ? i+1 : i;
					int pointHeight = arr[anglePos][j];
					
					for(int k=0; k<L; k++) {
						if (0 > anglePos || 
								anglePos >= N ||
								arr[anglePos][j] != pointHeight ||
								takePlace[anglePos][j]
						) {
							pathable = false;
							break;
						} else {
							takePlace[anglePos][j] = true;
						}
						anglePos += adder;
					}
				}
				if (!pathable) break;
			}
			if (pathable) path++;
		}
		System.out.println(path);
	}
}
