package week2;

import java.util.Scanner;

public class Bj1543_문서검색_S4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String total_word = sc.nextLine();
		String word = sc.nextLine();

		int cnt = 0;
		boolean flag;

		for (int i = 0; i < total_word.length(); i++) {
			flag = true;
			if (total_word.charAt(i) == word.charAt(0) && i + word.length() <= total_word.length()) {
				for (int j = 1; j < word.length(); j++) {
					if (total_word.charAt(i + j) != word.charAt(j)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					i += word.length() - 1;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
