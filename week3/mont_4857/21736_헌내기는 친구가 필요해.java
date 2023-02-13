import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static char[][] classes;
	static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		classes = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			classes[i] = br.readLine().toCharArray();
		}
		int dX = -1;
		int dY = -1;
		for (int i = 0; i < N; i++) {
			if(dX != -1) break;
			for (int j = 0; j < M; j++) {
				if(classes[i][j] == 'I') {
					dX = i; dY = j;
					break;
				}
			}
		}
		int cnt = 0;
		Queue<Integer> qX = new LinkedList<Integer>();
		Queue<Integer> qY = new LinkedList<Integer>();
		qX.offer(dX);
		qY.offer(dY);
		visit[dX][dY] = true;
		while(!qX.isEmpty() && !qY.isEmpty()) {
			int size = qX.size();
			for (int i = 0; i < size; i++) {
				int x = qX.poll();
				int y = qY.poll();
				
				for (int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if(nx<0 || nx >=N || ny<0 ||ny>=M || visit[nx][ny] || classes[nx][ny] == 'X') continue;
					if(classes[nx][ny] == 'P') {
						classes[nx][ny]= 'O';
						cnt++;
					}
						qX.offer(nx);
						qY.offer(ny);
						visit[nx][ny] = true;
				}
			}
		}
		if(cnt==0) {
			System.out.println("TT");
		}else {
			System.out.println(cnt);
		}
	}
}