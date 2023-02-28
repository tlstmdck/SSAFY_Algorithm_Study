import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		
		// 주사위 생성
		Dice dice = new Dice(0);
		
		// 주사위 이동
		while(st.hasMoreTokens()) {
			// 움직일 수 있는 지 확인하는 변수
			boolean isT = false;
			
			// 방향 변수
			int dir = Integer.parseInt(st.nextToken());
			
			// 명령대로 움직인 좌표
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 움직인 좌표가 지도 밖인 경우 다음 명령 실행
			if(nx < 0 || ny < 0 || nx >= N || ny >=M) continue;
			
			// 주사위를 움직인 후 주사위의 상단(top) 값을 가져온다.
			int result = dice.move(dir);
			
			/*
			 * 지도의 값과 주사위 하단을 비교하여 변경
			 * 지도의 값 == 0 인 경우
			 * 지도의 값을 주사위의 하단으로 변경
			*/
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice.bottom;
			}
			
			/*
			 * 지도의 값이 0이 아닌 경우
			 * 주사위의 하단을 지도의 수로 변경
			 * 지도의 를 0으로 변경
			*/
			else {
				dice.bottom = map[nx][ny];
				map[nx][ny] = 0;
			}
			sb.append(result+"\n");
			
			// 주사위 좌표 변경
			x = nx;
			y = ny;
		}
		System.out.println(sb.toString());
	}
	static class Dice{
		/*
		 * top : 주사위의 상단(하늘 방향)
		 * north : 주사위의 북 방향 (주사위의 상단을 본 상태)
		 * south : 주사위의 남 방향 (주사위의 상단을 본 상태)
 		 * east : 주사위의 동 방향 (주사위의 상단을 본 상태)
	 	 * west : 주사위의 서 방향 (주사위의 상단을 본 상태)
		 * bottom : 주사위의 하단 (땅 방향)
		*/
		int top;
		int north;
		int south;
		int east;
		int west;
		int bottom;
		
		public Dice(int bottom) {
			this.bottom = bottom;
			this.north = 0;
			this.south = 0;
			this.east = 0;
			this.west = 0;
			this.top = 0;
		}
		
		/*
		 * 주사위를 방향에 따라 회전한다.
		*/
		public int move(int direction) {
			int tmp =0;
			switch (direction) {
			/*
			 * 동쪽 방향으로 굴리는 경우
			 * 하단 <= 동
			 * 동 <= 상단
			 * 상단 <= 서
			 * 서  하단
			*/
			case 1:
				tmp = bottom;
				bottom = east;
				east = top;
				top = west;
				west = tmp;
				break;
					
			/*
			 * 서쪽 방향으로 굴리는 경우
			 * 하단 <= 서
			 * 서 <= 상단
			 * 상단 <= 동
			 * 동 <= 하단
			*/
			case 2:
				tmp = bottom;
				bottom = west;
				west = top;
				top = east;
				east = tmp;
				break;
			
			/*
			 * 북쪽 방향으로 굴리는 경우
			 * 하단 <= 북
			 * 북 <= 상단
			 * 상단 <= 남
			 * 남 <= 하단
			*/
			case 3:
				tmp = bottom;
				bottom = north;
				north = top;
				top = south;
				south = tmp;
				break;
			
			/*
			 * 남쪽으로 굴리는 경우
			 * 하단 <= 남
			 * 남 <= 상단
			 * 상단 <= 북
			 * 북 <= 하단
			*/
			case 4:
				tmp = bottom;
				bottom = south;
				south = top;
				top= north;
				north = tmp;
				break;
			}
			
			// 움직인 후 주사위의 상단의 값을 리턴
			return top;
		}

	}
}
