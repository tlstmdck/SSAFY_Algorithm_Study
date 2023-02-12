import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[M][N];
		boolean visit[][] = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		int Wmax = 0;
		int Bmax = 0;
		Queue<Integer> qX = new LinkedList<>();
		Queue<Integer> qY = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(visit[i][j]) continue;
				if (arr[i][j] == 'W') {
					qX.offer(i);
					qY.offer(j);
					visit[i][j] = true;
					int tmp = 1;
					while (!qX.isEmpty()) {
						int x = qX.poll();
						int y = qY.poll();
						for (int k = 0; k < 4; k++) {
							int nx = x +dx[k];
							int ny = y +dy[k];
							if(nx >= M || nx < 0 || ny >= N || ny < 0 || visit[nx][ny]) continue;
							if (arr[nx][ny] == 'W') {
								visit[nx][ny] = true;
								qX.offer(nx);
								qY.offer(ny);
								tmp++;
							}
						}
					}
					Wmax += Math.pow(tmp, 2);
				} else if (arr[i][j] == 'B') {
					qX.offer(i);
					qY.offer(j);
					visit[i][j] = true;
					int tmp = 1;
					while (!qX.isEmpty()) {
						int x = qX.poll();
						int y = qY.poll();
						for (int k = 0; k < 4; k++) {
							int nx = x +dx[k];
							int ny = y +dy[k];
							if(nx >= M || nx < 0 || ny >= N || ny < 0 || visit[nx][ny]) continue;
							if (arr[nx][ny] == 'B') {
								visit[nx][ny] = true;
								qX.offer(nx);
								qY.offer(ny);
								tmp++;
							}
						}
					}
					Bmax += Math.pow(tmp, 2);
				}
			}
		}

		System.out.println(Wmax + " " + Bmax);

	}

}