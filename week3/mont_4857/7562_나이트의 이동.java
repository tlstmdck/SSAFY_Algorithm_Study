import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static boolean[][] board;
	static int I;
	static int endX;
	static int endY;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			I = Integer.parseInt(br.readLine());
			board = new boolean[I][I];
			max = -1;

			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int cnt = 0;
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(startX, startY));
			while (!q.isEmpty()) {
				int size = q.size();
				for (int j = 0; j < size; j++) {
					Point tmp = q.poll();
					int x = tmp.getX();
					int y = tmp.getY();
					board[x][y] = true;
					if (x == endX && y == endY) {
						max = max > cnt ? max : cnt;
						break;
					}
					for (int k = 0; k < 8; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						if (nx < 0 || nx >= I || ny < 0 || ny >= I || board[nx][ny])
							continue;
						q.offer(new Point(nx, ny));
						board[nx][ny] = true;
					}
				}
				cnt++;
			}
			System.out.println(max);
		}
	}
}

class Point {
	private int x;
	private int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}