package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj10845_큐_S4 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			if (tmp.equals("push")) {
				arr[idx] = Integer.parseInt(st.nextToken());
				idx++;
			} else {
				if (tmp.equals("pop")) {
					if (idx == 0) {
						System.out.println(-1);
					} else {
						System.out.println(arr[0]);
						for (int j = 1; j < idx; j++) {
							arr[j - 1] = arr[j];
						}
						arr[idx] = 0;
						idx--;
					}
				} else if (tmp.equals("size")) {
					System.out.println(idx);
				} else if (tmp.equals("empty")) {
					if (idx == 0) {
						System.out.println(1);
					} else {
						System.out.println(0);
					}
				} else if (tmp.equals("front")) {
					if (idx == 0) {
						System.out.println(-1);
					} else {
						System.out.println(arr[0]);
					}
				} else if (tmp.equals("back")) {
					if (idx == 0) {
						System.out.println(-1);
					} else {
						System.out.println(arr[idx - 1]);
					}
				}

			}
		}
	}
}