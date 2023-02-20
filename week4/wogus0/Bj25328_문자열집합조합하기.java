package wogus0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bj25328_문자열집합조합하기 {
	static int k;
	static String x, y, z;
	static Map<String, Integer> map = new HashMap<>();
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		x = br.readLine();
		y = br.readLine();
		z = br.readLine();
		k = Integer.parseInt(br.readLine());

		comb(x, new char[k], 0, 0);
		comb(y, new char[k], 0, 0);
		comb(z, new char[k], 0, 0);

		map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
			if (entry.getValue() >= 2) {
				sb.append(entry.getKey() + "\n");
				flag = true;
			}
		});

		if (!flag) {
			System.out.println(-1);
		} else {
			System.out.println(sb.toString());
		}
	}

	private static void comb(String s, char[] result, int idx, int start) {

		if (idx == k) {
			String tmp = String.valueOf(result);
			if (map.containsKey(tmp)) {
				map.put(tmp, map.get(tmp) + 1);
			} else {
				map.put(tmp, 1);
			}

			return;
		}

		for (int i = start; i < s.length(); i++) {
			result[idx] = s.charAt(i);
			comb(s, result, idx + 1, i + 1);
		}
	}

}