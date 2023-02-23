package week2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj1260_DFS와BFS_S2 {

	static int n, m, v;
	static int[][] arr;
	static boolean[] visited;

	public static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");

		for (int i = 1; i <= n; i++) {
			if (arr[v][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}

	public static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(v);
		visited[v] = true;
		System.out.print(v + " ");

		while (!q.isEmpty()) {
			int tmp = q.poll();

			for (int i = 1; i <= n; i++) {
				if (arr[tmp][i] == 1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
					System.out.print(i + " ");
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		v = sc.nextInt();

		arr = new int[1001][1001];
		visited = new boolean[1001];

		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			arr[x][y] = 1;
			arr[y][x] = 1;
		}

		dfs(v);

		visited = new boolean[1001];
		System.out.println();

		bfs();

	}

}
