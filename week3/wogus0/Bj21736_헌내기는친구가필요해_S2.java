package study_230208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj21736_헌내기는친구가필요해_S2 {

	static int n, m, cnt = 0, p_x, p_y;
	static char[][] campus;
	static boolean[][] visited;

	static void dfs(int x, int y) {
		visited[x][y] = true;
		campus[x][y] = 'O';

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		for (int i = 0; i < 4; i++) {
			int n_x = x + dx[i];
			int n_y = y + dy[i];

			if (n_x < 0 || n_y < 0 || n_x >= n || n_y >= m)
				continue;
			if (campus[n_x][n_y] == 'P') {
				cnt++;
				dfs(n_x, n_y);
			}else if(campus[n_x][n_y] == 'O'&&!visited[n_x][n_y])
				dfs(n_x, n_y);

		}

	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		campus = new char[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String word = br.readLine();
			for (int j = 0; j < m; j++) {
				char tmp = word.charAt(j);
				campus[i][j] = tmp;
				if (tmp == 'I') {
					p_x = i;
					p_y = j;
				}
			}
		}

		dfs(p_x, p_y);

		if (cnt == 0)
			System.out.println("TT");
		else
			System.out.println(cnt);
	}

}
