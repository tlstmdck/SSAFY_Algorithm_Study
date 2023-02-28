import java.io.*;
import java.util.*;


/*
 * 문제 풀이 구상 :
 * 1. 연구소 내에서 생성할 수 있는 3개의 무작위 벽을 생성 (조합)
 * 2. 바이러스가 이동할 수 있는 모든 점 채우기 (BFS)
 * 3. 연구소 내의 안전 영역 구하기
*/

public class Main {
	static int[][] map;
	static int N, M;
	static int result = -1;
	static ArrayList<Point> list;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 연구소 배열
		map = new int[N][M];
		
		// 바이러스 좌표 리스트
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 해당 좌표 값이 바이러스인 경우 list에 추가
				if (map[i][j] == 2) {
					list.add(new Point(i, j));
				}
			}
		}
		
		// 연구소 내의 무작위로 3개의 벽 새우기.
		makeWall(0, 0);
		System.out.println(result);
	}

	static void makeWall(int idx, int cnt) {
		if (cnt == 3) {
			// 원본 배열을 훼손하지 않게 하기위해 원본의 복사본 생성
			int[][] tmp = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			result = Math.max(result, fillVirus(tmp));
			return;
		}
		/*
		 * 이중 for문으로 구현 시 순서대로 생성되지 않음
		 * 하나의 for문으로 x, y 값 지정
		 *  [  0 |  1 |  2 |  3 ]   =>    [  ( 0/4) + ( 0%4) |  ( 1/4) + ( 1%4) |  ( 2/4) + ( 2%4) |  ( 3/4) + ( 3%4) ]
		 *  [  4 |  5 |  6 |  7 ]   =>    [  ( 4/4) + ( 4%4) |  ( 5/4) + ( 6%4) |  ( 5/4) + ( 6%4) |  ( 7/4) + ( 7%4) ]
		 *  [  8 |  9 | 10 | 11 ]   =>    [  ( 8/4) + ( 8%4) |  ( 9/4) + ( 9%4) |  (10/4) + (10%4) |  (11/4) + (11%4) ]
		 *  [ 12 | 13 | 14 | 15 ]   =>    [  (12/4) + (12%4) |  (13/4) + (13%4) |  (14/4) + (14%4) |  (15/4) + (15%4) ]
 		*/
		for (int i = idx; i < N * M; i++) {
			int x = i / M;
			int y = i % M;
			if (map[x][y] == 0) {
				map[x][y] = 1;
				makeWall(idx + 1, cnt + 1);
				map[x][y] = 0;
			}
		}

	}
	
	// 연구소 내의 바이러스 채우는 메소드
	static int fillVirus(int[][] m) {
		int cnt = 0;
		Queue<Point> q = new LinkedList<>();
		
		// 초기 바이러스의 위치 Queue에 삽입
		for (Point point : list) {
			q.offer(point);
		}
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point tmp = q.poll();
				for (int j = 0; j < 4; j++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || m[nx][ny] != 0)
						continue;
					q.offer(new Point(nx, ny));
					m[nx][ny] = 2;
				}
			}
		}
		
		// 바이러스가 퍼진 후 안전영역 개수 확인
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] == 0)
					cnt++;
			}
		}
		
		// 안전 영역 개수 리턴
		return cnt;
	}
}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
