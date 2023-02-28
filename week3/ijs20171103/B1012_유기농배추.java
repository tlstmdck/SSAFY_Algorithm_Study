package silver2;

import java.io.*;
import java.util.*;

public class B1012_유기농배추 {
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static int M;
	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수

		for (int t = 1; t <= T; t++) {
			int res = 0; //tc별 결과
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			int[][] lettuce = new int[K][2];

			for (int k = 0; k < K; k++) { // 배추 입력
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				lettuce[k][0] = y; lettuce[k][1] = x; //배추좌표입력
				map[y][x] = 1; //배추가 있는 곳에1
			}

			//배추 위치에서 탐색
			for (int[] l : lettuce) {
				int i = l[0], j = l[1]; //y,x
				if(map[i][j] == 1) { //배추가 있을 때만 탐색시작
					DFS(i, j);
					res++;
				}
			}
			System.out.println(res);
		} // 테스트케이스 끝
	}
	static void DFS(int i, int j) {
		map[i][j] = 0; //방문
		
		for (int n = 0; n < 4; n++) { //사방탐색 시작
			int ni = i + di[n];
			int nj = j + dj[n];
			
			if(ni>=0 && ni<N && nj>=0 && nj<M && map[ni][nj]==1) { 
				DFS(ni, nj);
			}
			//한방향으로 전진했는데 길 막히고 1도 없음! => 다음 for문으로 방향전환
		}
		//현재 위치에서 사방탐색이 전부 끝났음
		return;
	}
}
