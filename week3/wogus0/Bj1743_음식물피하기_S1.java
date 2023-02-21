package study_230207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1743_음식물피하기_S1 {

	static int n, m, k, cnt=0, area=0;
	static int[][] arr;
	static boolean[][] visited;

	static void dfs(int x, int y) {
		visited[x][y] = true;
		cnt++;
		if (area <= cnt) {
			area = cnt;
		}
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int n_x = x + dx[i];
			int n_y = y + dy[i];

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
		k = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];
		
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x-1][y-1] = 1;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					cnt = 0;
					dfs(i, j);
				}

			}
		}
		System.out.println(area);

	}

}
