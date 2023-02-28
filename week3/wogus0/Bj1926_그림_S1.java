package study_230207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1926_그림_S1 {

	static int[][] arr;
	static boolean[][] visited;
	static int n, m, max_area = 0, area_cnt = 0, n_x, n_y;

	static void dfs(int x, int y) {
		visited[x][y] = true;
		area_cnt++;
		if (max_area <= area_cnt) {
			max_area = area_cnt;
		}
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			n_x = x + dx[i];
			n_y = y + dy[i];

			if (n_x < 0 || n_y < 0 || n_x >= n || n_y >= m)
				continue;
			if (visited[n_x][n_y] || arr[n_x][n_y] == 0)
				continue;

			dfs(n_x, n_y);

		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					area_cnt = 0;
					dfs(i, j);
					cnt++;
				}

			}
		}

		System.out.println(cnt);
		System.out.println(max_area);

	}

}
