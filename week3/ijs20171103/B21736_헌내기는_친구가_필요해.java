package silver2;

import java.io.*;
import java.util.*;

public class B21736_헌내기는_친구가_필요해 {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N  = Integer.parseInt(st.nextToken());
		int M  = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M]; 
		boolean[][] visited = new boolean[N][M];
		int res = 0; //결과 변수
		
		int[] doyeon = new int[2]; //도연이 좌표
		for (int i = 0; i < N; i++) { //맵 입력
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = ch[j]; 
				if(map[i][j]=='I') {
					doyeon[0] = i;
					doyeon[1] = j;
				}
			}
		}
		
		Queue<int[]>queue = new LinkedList<>();
		queue.add(doyeon); //도연이
		while(!queue.isEmpty()) {
			doyeon = queue.poll();
			int i = doyeon[0];
			int j = doyeon[1]; //위치 가져오기
			
			if(map[i][j]=='P') res++; //사람 있으면 결과에 더하기
			
			for (int n = 0; n < 4; n++) {
				int ni = i+di[n];
				int nj = j+dj[n];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue; //범위검사
				if(map[ni][nj]=='X' || visited[ni][nj]) continue; //벽이면 안됨
				
				doyeon = new int[]{ni, nj};
				queue.add(doyeon);
				visited[ni][nj] = true; //방문처리
			}
		} //더이상 갈곳이 없어 끝남
		if(res!=0)
			System.out.println(res);
		else
			System.out.println("TT");
	}
}