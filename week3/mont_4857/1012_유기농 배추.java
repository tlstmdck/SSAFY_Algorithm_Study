import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int M;
	static int N;
	static int cnt;
	static boolean[][] ground;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			cnt =0;
			ground = new boolean[N][M];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int a  =Integer.parseInt(st.nextToken());
				int b  =Integer.parseInt(st.nextToken());
				ground[b][a] = true;
			}
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(ground[j][k]) {
						find(j,k);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	static void find(int x, int y) {
		ground[x][y] = false;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=N || nx<0 ||ny >=M || ny<0)continue;
			if(ground[nx][ny]) {
				find(nx,ny);
			}
		}
		
	}

}