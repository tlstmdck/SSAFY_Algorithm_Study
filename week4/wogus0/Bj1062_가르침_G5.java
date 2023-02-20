package wogus0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1062_가르침_G5 {
	static int n, k, max;
	static String[] word;
	static boolean visit[] = new boolean[26];

	private static void comb(int start, int cnt) {
		if (cnt == k) {
			int num = 0;
			for (int i = 0; i < word.length; i++) {
				boolean flag = true;
				for (int j = 0; j < word[i].length(); j++) {
					if (!visit[word[i].charAt(j) - 97]) {
						flag = false;
						break;
					}
				}
				if (flag)
					num++;
			}
			max = Math.max(max, num);
			return;
		}

		for (int i = start; i < 26; i++) {
			if (!visit[i]) {
				visit[i] = true;
				comb(i, cnt + 1);
				visit[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		word = new String[n];

		if (k < 5) {
			System.out.println(0);
			return;
		} else if (k == 26) {
			System.out.println(n);
			return;
		} else {
			k -= 5;
		}

		// a, c, t, i, n;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			word[i] = s.substring(4, s.length() - 4);
		}

		visit['a' - 97] = true;
		visit['c' - 97] = true;
		visit['t' - 97] = true;
		visit['i' - 97] = true;
		visit['n' - 97] = true;

		comb(0, 0);
		System.out.println(max);

	}

}