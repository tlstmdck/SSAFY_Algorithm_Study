package silver1;

import java.io.*;
import java.util.*;

/*
N, M, map 입력 받음
map의 각 행에 대하여 누적합을 만들어 새 배열에 넣음(새 배열은 N+1 x M+1)
그리고 직사각형 범위 개수 입력받음
직사각형 범위만큼의 합을 누적합에서 구함
예를 들어 3x3짜리에서 오른쪽 위의 1x1이 궁금하다면: 맨 윗 행에서 맨 오른쪽 누적합 - 맨 오른쪽에서 앞의 누적합
 * */
public class B15724_주지수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); //정답출력용
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] newMap = new int[N][M + 1]; //누적합배열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j+1] = newMap[i][j]+map[i][j]; 
			}
		} // 누적합 배열 만들기 끝
		
		// 만약 1 1 3 2를 입력받으면
		// 시작 행은 0부터 2까지고
		// 시작 열은 1부터 2까지임
		// 그럼 for 0~2 : 누적합[2] - 누적합[1-1]
		int K = Integer.parseInt(br.readLine()); // 직사각형 범위 개수
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine()); 
			int start_i = Integer.parseInt(st.nextToken());
			int start_j = Integer.parseInt(st.nextToken());
			int last_i = Integer.parseInt(st.nextToken());
			int last_j = Integer.parseInt(st.nextToken());
			int sum = 0;
			for (int i = start_i-1; i < last_i; i++) {
				sum += newMap[i][last_j] - newMap[i][start_j-1];
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
