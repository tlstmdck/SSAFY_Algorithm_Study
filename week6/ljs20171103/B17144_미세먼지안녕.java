package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 매커니즘
 * 1. 미세먼지 확산: 미세먼지가 있는 곳에서 '네 방향'으로 확산되나
 * 인접한 방향에 공청기가 있거나 칸이 없으면 확산 안됨
 * 또한 확산되는 양은 나누기 5이며 소수점은 버림. 확산시키고 남은 양은
 * => 그곳 미세먼지 - (미세먼지/5)*(확산된 방향 개수)

 * 2. 공청기 작동: 위쪽에서는 반시계 방향으로 바람이 순환하고 아래쪽에서는 시계방향으로 순환
 * 바람이 불면 미세먼지가 바람 방향대로 한 칸씩 이동
 * 공기청정기로 들어간 미세먼지는 사라짐.
 * */
public class B17144_미세먼지안녕 {
	static class Dust{ //원래 미세먼지가 있었던 곳의 정보만을 저장
		int r, c, value;

		public Dust(int r, int c, int value) {
			super();
			this.r = r;
			this.c = c;
			this.value = value;
		}
		
	}
	static int R, C, T;
	static int[][] map;
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int AP = -1; // 공기청정기 행 좦

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        
        for (int i = 0; i < R; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]==-1 && AP==-1) { // 공기청정기 좌표를 안넣은 경우에만 좌표넣음
					AP = i;
				}
			}
		} // end input
        for (int i = 0; i < T; i++) {
			diffuseDusts(); // 먼지확산
			
			runAP(); //공기청정기 가동
		}
        
        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == -1) 
                	continue;
                res += map[i][j];
            }
        }
        System.out.println(res);
    }

	// 모든 미세먼지를 확산
	static void diffuseDusts() {
		ArrayList<Dust>dusts = new ArrayList<Dust>(); 

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] < 5) // 미세먼지가 5보다 작거나, 없거나, 공기청정기인 경우
					continue; // 확산 안됨
				dusts.add(new Dust(i, j, map[i][j])); //미세먼지 정보 저장
			}
		}
		for (Dust dust : dusts) {
			diffuse(dust);
		}
	}

	// 매개변수로 받은 dust 확산
	static void diffuse(Dust dust) {
		int value = dust.value / 5; // 확산될 먼지의 양
		int cnt=0; //확산 횟수\s
		System.out.println("현재 위치와 값:"+dust.r+" "+dust.c+": "+dust.value);
		System.out.println("확산시킬 양: "+value);
		for (int n = 0; n < 4; n++) {
			int ni = dust.r + di[n];
			int nj = dust.c + dj[n];

			// 범위검사
			if (ni < 0 || ni >= R || nj < 0 || nj >= C)
				continue;
			// 공기청정기 검사
			if(map[ni][nj]==-1)
				continue;

			map[ni][nj] += value; // 먼지를 확산시켰음
			System.out.println(ni+", "+nj+"에 확산시켰음. "+map[ni][nj]);
			cnt++;
		}
		map[dust.r][dust.c] -=  (value*cnt);
		System.out.println("value: "+value+", cnt: "+cnt);
		System.out.println("확산후 양: "+map[dust.r][dust.c]);
		for (int[] is : map) {
			System.out.println(Arrays.toString(is));
		}
	}

	// 공기청정기 가동
	static void runAP() {
		int top = AP; //공기청정기 윗부분
		int down = AP+1; //공기청정기 아랫부분
		
		// 위쪽 공기청정기에서 반시계방향 순환
		// 윗공기 아래로 
		for (int i = top-1; i > 0; i--) {
			map[i][0] = map[i-1][0];
		}
		// 오른쪽 공기 왼쪽으로
		for (int j = 0; j < C-1; j++) {
			map[0][j] = map[0][j+1];
		}
		// 아랫공기 위로
		for (int i = 0; i < top; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		// 왼쪽 공기 오른쪽으로
		for (int j = C-1; j > 1; j--) {
			map[top][j] = map[top][j-1];
		}
		// 공기청정기에서 -1이 날아왔으니 0으로 제거
		map[top][1] = 0;
		
		// 아래쪽 공기청정기에서 시계방향 순환
		// 아래에서 위로
		for (int i = down+1; i < R-1 ; i++) {
			map[i][0] = map[i+1][0];
		}
		// 오른쪽에서 왼쪽으로
		for (int j = 0; j < C-1; j++) {
			map[R-1][j] = map[R-1][j+1];
		}
		// 위에서 아래로
		for (int i = R-1; i > down; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		// 왼쪽에서 오른쪽으로
		for (int j = C-1; j > 1; j--) {
			map[down][j] = map[down][j-1];
		}
		map[down][1] = 0;
	}

}
