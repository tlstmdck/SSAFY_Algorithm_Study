import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int arr[][] = new int[n][m];
		boolean visit[][] = new boolean[n][m];
		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		int cnt =0;
		Queue<Integer> X = new LinkedList<>();
		Queue<Integer> Y = new LinkedList<>();
		
		for (int i = 0; i < visit.length; i++) {
			for (int j = 0; j < visit[i].length; j++) {
				if(arr[i][j] == 1 && !visit[i][j]) {
					int tmp = 1;
					cnt++;
					X.offer(i);
					Y.offer(j);
					while(!X.isEmpty()) {
						int size = X.size();
						int x = X.poll();
						int y = Y.poll();
						visit[x][y] = true;
						for (int k = 0; k < size; k++) {
							if(x+1 < n && arr[x+1][y] == 1 && !visit[x+1][y]) {
								visit[x+1][y] = true;
								X.offer(x+1);
								Y.offer(y);
								tmp++;
							}
							
							if(x-1 >=0 && arr[x-1][y] == 1 && !visit[x-1][y]) {
								visit[x-1][y] = true;
								X.offer(x-1);
								Y.offer(y);
								tmp++;
							}
							if(y+1 < m && arr[x][y+1] == 1 && !visit[x][y+1]) {
								visit[x][y+1] = true;
								X.offer(x);
								Y.offer(y+1);
								tmp++;
							}
							if(y-1 >= 0 && arr[x][y-1] == 1 && !visit[x][y-1]) {
								visit[x][y-1] = true;
								X.offer(x);
								Y.offer(y-1);
								tmp++;
							}
						}
					}
					result = result>tmp ?result:tmp;
				}
			}
		}
		System.out.println(cnt);
		System.out.println(result);
		
	}

}