import java.io.*;
import java.util.*;

public class Main {
	static int R, C, T;
	static int[][] map;
	static int[][] tmp;
	static Point[] airCleaner;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
    
    // 지도 배열
		map = new int[R][C];
    
    // 공기 청정기 위치 배열
		airCleaner = new Point[2];
    
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airCleaner[0] = new Point(i - 1, j);
					airCleaner[1] = new Point(i, j);
				}
			}
		}
    
    // T초 동안 실시
		for (int t = 0; t < T; t++) {
      // 미세먼지가 확산된 정도를 담아 놓는 임시 배열
			tmp = new int[R][C];
			Queue<Point> q = new ArrayDeque<>();

      // 미세먼지가 5보다 작은 경우 확산이 되지 않기 때문에 Queue에 넣지 않는다.
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 4)
						q.offer(new Point(i, j));
				}
			}
      
      // Queue에 있는 미세 먼지 들을 확산
	    while(!q.isEmpty()){
				Point p = q.poll();
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == -1)
						continue;
					cnt++;
          
          // 임시 배열에 확산된 양만 더해준다.
					tmp[nx][ny] += map[p.x][p.y] / 5;
				}
        // 4방향을 확산을 모두 확인하고 지도 배열에 있는 미세 먼지 양을 확산된 방향에 따라 감소시켜준다.
				map[p.x][p.y] -= ((map[p.x][p.y] / 5) * cnt);
			} 
  
      // 상하좌우로 확산된 양이 담겨있는 임시 배열의 값을 지도 배열에 더해준다.
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					map[j][k] += tmp[j][k];
				}
			}
			
			// 공기 청정기 (위쪽)
			int x = airCleaner[0].x;
			int y = airCleaner[0].y;
      // 초기 방향
			int dir = 1;

      /*
       * map[bx][by] -> map[x][y]
      */
			while (true) {
				int bx = x + dx[dir];
				int by = y + dy[dir];
        
        // 범위가 벗어난 경우에는 방향만 바꾸기
				if (bx < 0 || by < 0 || bx > airCleaner[0].x || by >= C) {
					dir = (dir + 3) % 4;
					continue;
				}
        
        // 바람에 밀려서 도착한 곳이 공기 청정기인 경우 점만 바꾸기
				if (map[x][y] == -1) {
					x = bx;
					y = by;
					continue;
				}
        
        // 한바퀴 돈 경우 공기청정기 옆을 0으로 설정 후 종료
				if (map[bx][by] == -1) {
					map[x][y] = 0;
					break;
				}
				map[x][y] = map[bx][by];
				
        x = bx;
				y = by;
			}

			// 공기청정기 (아래쪽)
			x = airCleaner[1].x;
			y = airCleaner[1].y;
			dir = 1;

			while (true) {
				int bx = x + dx[dir];
				int by = y + dy[dir];
				if (bx < airCleaner[1].x || by < 0 || bx >=R || by >= C) {
					dir = (dir + 1) % 4;
					continue;
				}
				if (map[x][y] == -1) {
					x = bx;
					y = by;
					continue;
				}
				if (map[bx][by] == -1) {
					map[x][y] = 0;
					break;
				}
				map[x][y] = map[bx][by];
				x = bx;
				y = by;
			}
		} // T 중료

    // 미세먼지 값 더하기
		int result = 0;
		for (int j = 0; j < R; j++) {
			for (int k = 0; k < C; k++) {
				if(map[j][k] >0 ) result += map[j][k];
			}
		}
		System.out.println(result);
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
