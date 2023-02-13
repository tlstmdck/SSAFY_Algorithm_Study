package study_230209;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	int x;
	int y;
	int cnt;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
		cnt = 0;
	}

	public Pair(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

}

public class Bj7562_나이트의이동_S1 {

	static int[][] area;
	static boolean[][] visited;
	static int l;
	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static int bfs(Pair[] pair) {
		Queue<Pair> que = new LinkedList<>();

		que.offer(pair[0]);
		visited[pair[0].x][pair[0].y] = true;

		while (!que.isEmpty()) {
			Pair p = que.poll();

			if (p.x == pair[1].x && p.y == pair[1].y) {
				return p.cnt;
			}

			for (int i = 0; i < 8; i++) {
				int n_x = p.x + dx[i];
				int n_y = p.y + dy[i];

				if (n_x < 0 || n_y < 0 || n_x >= l || n_y >= l) {
					continue;
				}

				if (!visited[n_x][n_y]) {
					visited[n_x][n_y] = true;
					que.offer(new Pair(n_x, n_y, p.cnt + 1));
				}
			}
		}
		return -1;
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int T = 0; T < t; T++) {
			l = sc.nextInt();
			area = new int[l][l];
			visited = new boolean[l][l];
			Pair[] pair = new Pair[2];
			
			for (int i = 0; i < 2; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				pair[i] = new Pair(x, y);

			}
			System.out.println(bfs(pair));

		}

	}

}
