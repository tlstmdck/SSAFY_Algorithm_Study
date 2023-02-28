import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		func(0,0,N);
		System.out.println(sb.toString());
	}
	static void func(int x, int y, int length) {
		if(length == 1) {
			sb.append(arr[x][y]);
			return;
		}
		boolean isT = false;
		for (int i = x; i < x+length; i++) {
			if(isT) break;
			for (int j = y; j < y+length; j++) {
				if(arr[i][j] != arr[x][y]) {
					isT = true;
					break;
				}
			}
		}
		if(!isT) {
			sb.append(arr[x][y]);
		}else {
			sb.append("(");
			func(x,y,length/2);
			func(x,y+length/2,length/2);
			func(x+length/2,y,length/2);
			func(x+length/2,y+length/2,length/2);
			sb.append(")");
		}
	}

}