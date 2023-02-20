import java.io.*;
import java.util.*;

public class Main {
	static int w;
	static int h;
	static int toastX;
	static int toastY;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken()); // 3
		h = Integer.parseInt(st.nextToken()); // 2
		st = new StringTokenizer(br.readLine());
		toastY = Integer.parseInt(st.nextToken()); // 2
		toastX = Integer.parseInt(st.nextToken()); // 2
		long goToast = goTo(1, 1, toastX, toastY);  // 토스트까지 거리
		long goSchool = goTo(toastX, toastY,h,w);  // 학교까지 거리
		System.out.println(String.valueOf(( goToast * goSchool ) % 1000007));
	}

	static long goTo(int fromX, int fromY, int toX, int toY) {
		long [][] arr = new long[toY - fromY + 1][toX - fromX + 1];
		for (int i = 0; i < arr[0].length; i++) {
			arr[0][i] = 1;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i][0] = 1;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				arr[i][j] = (arr[i-1][j] + arr[i][j-1]) % 1000007;
			}
		}
		return arr[arr.length-1][arr[0].length-1];
	}
}
