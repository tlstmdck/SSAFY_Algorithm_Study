package week2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _1260 {

	static boolean visit[];
	static int graph[][];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		int v = sc.nextInt();

		graph = new int[n + 1][n + 1];
		for (int g = 0; g < m; g++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			graph[i][j] = graph[j][i] = 1;

		}
		visit = new boolean[n + 1];
		dfs(v);
		System.out.println();
		visit = new boolean[n + 1];
		bfs(v);
	}

	public static void dfs(int ver) {

		visit[ver] = true;

		System.out.print(ver + " ");

		for (int i = 1; i < graph.length; i++) {
			if (graph[ver][i] == 1 && visit[i] == false) {
				dfs(i);
			}
		}

	}

	public static void bfs(int ver) {

		Queue<Integer> que = new LinkedList<>();
		que.offer(ver);
		visit[ver] = true;

		while (!que.isEmpty()) {
			ver = que.poll();

			System.out.print(ver + " ");
			for (int i = 1; i < graph.length; i++) {
				if (graph[ver][i] == 1 && visit[i] == false) {

					que.offer(i);
					visit[i] = true;
				}
			}
		}

	}
}
