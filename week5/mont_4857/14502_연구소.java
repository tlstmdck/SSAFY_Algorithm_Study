import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N, M;
	static int result = -1;
	static ArrayList<Point_> list;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Point_(i, j));
				}
			}
		}

		comb(0, 0);
		System.out.println(result);
	}

	static void comb(int idx, int cnt) {
		if (cnt == 3) {
			int[][] tmp = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			result = Math.max(result, bfs(tmp));
			return;
		}
		for (int i = idx; i < N * M; i++) {
			int x = i / M;
			int y = i % M;
			if (map[x][y] == 0) {
				map[x][y] = 1;
				comb(idx + 1, cnt + 1);
				map[x][y] = 0;
			}
		}

	}

	static int bfs(int[][] m) {
		int cnt = 0;
		Queue<Point_> q = new LinkedList<>();
		for (Point_ point : list) {
			q.offer(point);
		}
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point_ tmp = q.poll();
				for (int j = 0; j < 4; j++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || m[nx][ny] != 0)
						continue;
					q.offer(new Point_(nx, ny));
					m[nx][ny] = 2;
				}
			}
		}
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class Point_ {
	int x;
	int y;

	Point_(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
