package study_230213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1012유기농배추_S2 {

	static int[][] dist;
	static boolean[][] visited;
	static int m;
	static int n;
	static int k;

	public static void dfs(int x, int y) {
		visited[x][y] = true;

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int n_x = x + dx[i];
			int n_y = y + dy[i];

			if (n_x < 0 || n_y < 0 || n_x >= m || n_y >= n)
				continue;
			if (visited[n_x][n_y] || dist[n_x][n_y] == 0)
				continue;
			dfs(n_x, n_y);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());

		for (int T = 0; T < t; T++) {
			int cnt = 0;
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 가로
			n = Integer.parseInt(st.nextToken()); // 세로
			k = Integer.parseInt(st.nextToken()); // 배추 수

			dist = new int[m][n];
			visited = new boolean[m][n];

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				dist[x][y] = 1;
			}

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}

	}

}