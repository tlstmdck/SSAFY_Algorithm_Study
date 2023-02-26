package gold4;

import java.io.*;
import java.util.*;

/*
 * NxM개의 배열 중 특정 위치에 1을 세번 놓는 경우를 모두 구함
 * 최악의 경우 64x63x62 = 249,984
 * 1을 세번 놓고나면 2에 대해 bfs해서 2 채우기
 * 2 채우면서 0의 총 개수에서 1씩 빼주면 됨
 * =============
 * 조합할 때는 result에 좌표를 넣되 map[i][j]가 0일 때만 넣어야 함
 * */
public class B14502_연구소 {
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int N, M; // 행, 열
	static int[][] map;
	static int[][] result = new int[3][2]; // 1놓는 좌표들의 조합을 넣을 배열
	static int res = Integer.MIN_VALUE; // 결과
	static int zeroCnt; // 현재 맵에서 0의 개수
	static ArrayList<int[]> twos = new ArrayList<int[]>(); // 2 좌표 넣을것

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		zeroCnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 2) // 2인곳 좌표 추가
					twos.add(new int[] { i, j });
				else if (map[i][j] == 0)
					zeroCnt++;
			}
		} // end input


		combi(0, 0);
		System.out.println(res);
	}

	// 1 세우는 조합
	static void combi(int idx, int start) {
		if (idx == 3) {
			int[][] newMap = new int[N][M];
			// 2차원배열 깊은복사
			for (int i = 0; i < N; i++) {
				newMap[i] = map[i].clone();
			}
			// 1채우기
			for (int[] r : result) {
				newMap[r[0]][r[1]] = 1;
			}
			int tmp = fillTwo(newMap, zeroCnt - 3); // 2를채우는 과정 후 0의 개수 반환
			res = Math.max(res, tmp);
			return;
		}
		// 2차원배열 조합: https://dino-rudy.tistory.com/21
		for (int i = start; i < N * M; i++) {
			int r = i / M;
			int c = i % M;
			if (map[r][c] == 0) {
				result[idx][0] = r;
				result[idx][1] = c;
				combi(idx + 1, i + 1);
			}
		}
	}

	// 1을 세운 조합에서 2를 채우기 후 0개수 반환
	static int fillTwo(int[][] newMap, int zeroCnt) {
		boolean[][] visit = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();

		for (int[] pair : twos) {
			// start bfs
			if (!visit[pair[0]][pair[1]]) {
				q.offer(pair);
				visit[pair[0]][pair[1]] = true;
				while (!q.isEmpty()) {
					int[] tmp = q.poll();
					int i = tmp[0];
					int j = tmp[1];
					for (int n = 0; n < 4; n++) {
						int ni = i + di[n];
						int nj = j + dj[n];

						// 범위검사
						if (ni < 0 || ni >= N || nj < 0 || nj >= M)
							continue;
						// 0이 아니거나 방문했으면 안됨
						if (newMap[ni][nj] != 0 || visit[ni][nj])
							continue;

						q.offer(new int[] { ni, nj });
						visit[ni][nj] = true;
						newMap[ni][nj] = 2;
						zeroCnt -= 1; // 2를 만났으므로 줄임
					}
				}
			}
			// end bfs
		}
		return zeroCnt;
	}
}
