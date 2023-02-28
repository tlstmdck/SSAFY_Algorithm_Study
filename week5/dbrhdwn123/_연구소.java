
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class room {
	int x;
	int y;

	room(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class _연구소 {
	static int di[] = { 1, 0, -1, 0 };
	static int dj[] = { 0, 1, 0, -1 };
	static int area[][];
	static ArrayList<room> list = new ArrayList<>();
	static boolean visit[][];
	static boolean check[];
	static int result[] = new int[3];
	static int n, m, cnt;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		area = new int[n][m];
		visit = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				if (area[i][j] == 0)
					list.add(new room(i, j));
			}
		}

		check = new boolean[list.size()];
		recur(0, 0);
		System.out.println((m * n) - min);

	}

	public static void recur(int idx, int start) {
		if (idx == 3) {
			// System.out.println("3만들어짐.");
			cnt = 0;
			visit = new boolean[n][m];
			for (int i = 0; i < 3; i++) {

				visit[list.get(result[i]).x][list.get(result[i]).y] = true;
				cnt++;
				// System.out.print(list.get(result[i]).x + " ");
				// System.out.println(list.get(result[i]).y);
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (area[i][j] == 2 && !visit[i][j]) {
						visit[i][j] = true;
						cnt++;

						dfs(i, j);
					}
					if (area[i][j] == 1 && !visit[i][j]) {
						visit[i][j] = true;
						cnt++;
					}
				}

			}
			// System.out.println(cnt);
			min = Math.min(min, cnt);
			return;
		}

		for (int i = start; i < list.size(); i++) {

			if (check[i])
				continue;
			check[i] = true;
			result[idx] = i;
			// System.out.println(idx);
			recur(idx + 1, i + 1);
			check[i] = false;
		}

	}

	public static void dfs(int i, int j) {
		for (int c = 0; c < 4; c++) {
			int nexti = i + di[c];
			int nextj = j + dj[c];

			if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < m && !visit[nexti][nextj] && area[nexti][nextj] != 1) {
				visit[nexti][nextj] = true;
				cnt++;
				dfs(nexti, nextj);
			}
		}
	}

}
