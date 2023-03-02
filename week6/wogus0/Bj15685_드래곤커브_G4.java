package study230301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bj15685_드래곤커브_G4 {

	static boolean visit[][] = new boolean[101][101];
	static int dxy[][] = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } }; // 0 : 오른쪽, 1 : 위쪽, 2 : 왼쪽, 3 : 아랫쪽

	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int ans = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			check(y, x, d, g);
		}

		// 배열에서 4칸 모두 visit이 true이면 ans에 1씩 더해준다.
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (visit[i][j] && visit[i + 1][j] && visit[i][j + 1] && visit[i + 1][j + 1]) {
					ans++;
				}
			}
		}

		System.out.println(ans);

	}

	
	static void check(int x, int y, int d, int g) {
		Point now = new Point(x, y);
		ArrayList<Integer> list = new ArrayList<>();
		list.add(d);
		visit[x][y] = true;

		for (int i = 0; i < g + 1; i++) {
			for (int j = list.size() / 2; j < list.size(); j++) {
				now.x = now.x + dxy[list.get(j)][0];
				now.y = now.y + dxy[list.get(j)][1];
				visit[now.x][now.y] = true;
			}

			ArrayList<Integer> temp = new ArrayList<>();
			
			for (int j = list.size() - 1; j > -1; j--) {
				temp.add((list.get(j) + 1) % 4);
			}

			for (int j : temp) {
				list.add(j);
			}
		}

	}

}