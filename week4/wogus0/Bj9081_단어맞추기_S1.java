package wogus0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj9081_단어맞추기_S1 {
	static char[] word;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			String s = br.readLine();
			word = s.toCharArray();

			if (nextPermutation()) {
				for (int i = 0; i < s.length(); i++) {
					sb.append(word[i]);
				}
				sb.append("\n");
			} else
				sb.append(s + "\n");
		}
		System.out.println(sb);
	}

	static boolean nextPermutation() {
		int i = word.length - 1;
		while (i > 0 && word[i - 1] >= word[i])
			i--;
		if (i == 0)
			return false;

		int j = word.length - 1;
		while (word[i - 1] >= word[j])
			j--;

		char temp = word[i - 1];
		word[i - 1] = word[j];
		word[j] = temp;

		int k = word.length - 1;
		while (i < k) {
			temp = word[i];
			word[i] = word[k];
			word[k] = temp;
			i++;
			k--;
		}
		return true;
	}
}