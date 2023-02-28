package study_230208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1303_전쟁_전투_S1 {

	static int n, m, mypower = 0, yourpower = 0, cnt = 0;
	static char[][] area;
	static boolean[][] visited;

	static void dfs(int x, int y) {
		visited[x][y] = true;
		char tmp = area[x][y];
		cnt++;
		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int n_x = x + dx[i];
			int n_y = y + dy[i];

			if (n_x < 0 || n_y < 0 || n_x >= m || n_y >= n)
				continue;
			if (area[n_x][n_y] != tmp || visited[n_x][n_y])
				continue;
			dfs(n_x, n_y);

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		area = new char[m][n];
		visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			String word = br.readLine();
			for (int j = 0; j < n; j++) {
				area[i][j] = word.charAt(j);
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					if (area[i][j] == 'W') {
						dfs(i, j);
						mypower += cnt * cnt;
					} else if (area[i][j] == 'B') {
						dfs(i, j);
						yourpower += cnt * cnt;
					}
					cnt = 0;
				}
			}
		}

		System.out.println(mypower + " " + yourpower);
	}

}
