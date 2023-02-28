package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14502_연구소 {

	static int N, M, max; // 세로, 가로, 최대값 저장
	static int[][] map, copyMap; // 지도
	static int[] my = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] mx = { 0, 0, -1, 1 };

	static void cleanZone() { // map에서 0인 부분을 찾는 메서드
		int sum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0)
					sum++;
			}
		}

		max = Math.max(max, sum); // 최대값 저장
	}

	static void createWall(int wallCnt) { // 벽설치
		if (wallCnt == 0) { // 벽을 3개만 설치할 수 있으므로 0이 되면 바이러스를 퍼뜨리고 깨끗한 부분을 찾기
			spreadVirus();
			cleanZone();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0) // 이미 방문한 지역이면
					continue;
				
				map[i][j] = 1; // 벽 세우고
				createWall(wallCnt - 1); // 넘기기
				map[i][j] = 0; // 이후 다시 돌려놓기
				
			}
		}
	}

	static void spreadVirus() {
		Queue<Integer> q = new LinkedList<>(); // y, x 순으로 q에 넣고 한번에 2개씩 빼기
		boolean[][] check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j]; // 원본을 훼손을 막기 위해 깊은 복사
				if (map[i][j] == 2) { // 2이면 그 부분부터 4방향으로 퍼지므로 초기 감염지역 q에 넣기
					q.add(i);
					q.add(j);
					check[i][j] = true;
				}
			}
		}

		// 주변지역 퍼뜨리기
		while (!q.isEmpty()) {
			int selectY = q.poll();
			int selectX = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = selectY + my[i];
				int nx = selectX + mx[i];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] != 0 || check[ny][nx])
					continue;

				copyMap[ny][nx] = 2;
				check[ny][nx] = true;
				q.add(ny);
				q.add(nx);

			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copyMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// end input


		// logic
		createWall(3);
		

		// print
		System.out.println(max);
	}
}