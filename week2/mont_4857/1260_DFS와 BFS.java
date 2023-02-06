import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N+1];
		boolean[][] graph = new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x][y] = true;
			graph[y][x] = true;
		}
		DFS(graph, N, V, 0);
		System.out.println();
		visit = new boolean[N+1];
		BFS(graph,V);
	}
	static void DFS(boolean arr[][],int n, int start, int level) {
		if(n <= level) {
			return;
		}
		System.out.print(start + " ");
		visit[start] = true;
		for (int i = 1; i < arr[start].length; i++) {
			if(arr[start][i] == true && !visit[i]) {
				DFS(arr,n,i,level+1);
			}
		}
	}
	static void BFS(boolean[][] arr, int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visit[start] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			int tmp = q.poll();
			System.out.print(tmp +" ");
			for (int i = 0; i < size; i++) {
				for (int j = 1; j < arr[tmp].length; j++) {
					if(arr[tmp][j] == true && !visit[j]) {
						visit[j] = true;
						q.offer(j);
					}
				}
			}
		}
		
	}
}
