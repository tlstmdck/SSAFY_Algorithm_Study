package wogus0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj15724_주지수_S1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];

		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < m; j++) {
			if(j == 0)
				arr[0][0] = Integer.parseInt(st.nextToken());
			else
				arr[0][j] += arr[0][j-1] + Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				if(j == 0) {
					arr[i][j] += arr[i-1][j] + Integer.parseInt(st.nextToken());
				}else {
					arr[i][j] += arr[i][j-1] + arr[i-1][j] - arr[i-1][j-1] + Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int k = Integer.parseInt(br.readLine());

		for (int t = 0; t < k; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			int sum = 0;

			if(x1 == 0 && y1>0)
				sum += arr[x2][y2] - arr[x2][y1-1];
			else if(y1 == 0 && x1>0)
				sum += arr[x2][y2] - arr[x1-1][y2];
			else if(x1 == 0 && y1 == 0)
				sum += arr[x2][y2];
			else
				sum += arr[x2][y2] - arr[x2][y1-1] - arr[x1-1][y2] + arr[x1-1][y1-1];

			System.out.println(sum);
		}

	}

}