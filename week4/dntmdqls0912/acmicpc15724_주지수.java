package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][M+1];
		
//		 2차원 누적합 생성 
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				arr[i][j] = arr[i][j-1] + arr[i-1][j] + Integer.parseInt(st.nextToken()) - arr[i-1][j-1];
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int ly = Integer.parseInt(st.nextToken());
			int lx = Integer.parseInt(st.nextToken());
			int ry = Integer.parseInt(st.nextToken());
			int rx = Integer.parseInt(st.nextToken());

			int rst = arr[ry][rx] - arr[ly-1][rx] - arr[ry][lx-1] + arr[ly-1][lx-1];
			sb.append(rst).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}// end main
}