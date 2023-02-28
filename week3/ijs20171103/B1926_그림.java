package silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1926_그림 {
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static int Row;
	static int Col;
	static int[][] map;
	static int[] results;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		Row = Integer.parseInt(st.nextToken()); // 도화지 가로크기
		Col = Integer.parseInt(st.nextToken()); // 도화지 세로크기

		map = new int[Row][Col];
		// 맵 입력
		for (int i = 0; i < Row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < Col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 맵 탐색
		int cnt = 0; // 그림개수
		int max = 0; // 제일 큰 그림

		for (int i = 0; i < Row; i++) {
			for (int j = 0; j < Col; j++) {
				if (map[i][j] == 1) { // 그림 있는 곳 탐색
					int size = DFS(i, j, 1);
					max = size > max ? size : max;
					cnt++; //DFS를 최초 한번 실행할때만 사이즈+1
				}
			}
		}
		System.out.printf("%d\n%d", cnt, max);
	}

	static int DFS(int i, int j, int size) { // i, j: 좌표. size: 현재 그림 크기
		map[i][j]=0; //방문
		for (int n = 0; n < 4; n++) {
			int ni = i + di[n];
			int nj = j + dj[n];
			
			if (ni<0 || ni>=Row || nj<0 || nj>=Col) continue; //범위 밖이면 방향 바꾸기
			if (map[ni][nj] == 0) continue; // 그림 아니면 방향 바꾸기;
			
			size = DFS(ni, nj, size+1);
		}
		// 기저조건: 사방탐색을 해도 아무것도 없을 때
		return size;
	}
}
