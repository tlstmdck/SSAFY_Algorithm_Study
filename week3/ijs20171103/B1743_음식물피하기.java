package silver1;

import java.util.*;
import java.io.*;

public class B1743_음식물피하기 {
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static int Row;
	static int Col;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 통로 크기, 쓰레기 개수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		Row = Integer.parseInt(st.nextToken());
		Col = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[Row][Col]; //통로
		int[][] food = new int[K][2];
		
		// 음식물 입력
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			food[k][0] =r-1; food[k][1]=c-1; //음식물 좌표 넣기
			map[r-1][c-1] = 1; //음식물 생성
		}
		
		//제일 큰 음식물 구하기
		int max = 0;
		for (int[] f : food) { //각 음식물이 존재하는 곳으로 가서 그 구역의 음식물 크기 구함
			int i = f[0], j=f[1]; 
			int result = DFS(i, j, 1);
			max = result>max? result: max;
		}
		System.out.println(max);
	}
	
	static int DFS(int i, int j, int size) {
		map[i][j] = 0; //방문
		
		for (int n = 0; n < 4; n++) { //사방탐색 시작
			int ni = i + di[n];
			int nj = j + dj[n];
			
			if(ni>=0 && ni<Row && nj>=0 && nj<Col && map[ni][nj]==1) { //한방향으로 계속 이동 
				size = DFS(ni, nj, size+1); //다음 음식물로 이동하므로 size up
				// 결국 한방향의 끝에 도달했을 때  그 방향
			}
			//한방향으로 전진했는데 길 막히고 1도 없음! => 다음 for문으로 방향전환
		}
		// """""현재 위치에서 사방탐색이 전부 끝났음"""""
		return size;
	}
}
