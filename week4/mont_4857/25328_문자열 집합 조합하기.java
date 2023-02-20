import java.io.*;
import java.util.*;

public class Main {
	static char[][] str;
	static int k;
	static Map<String, Integer>[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = new char[3][];
		map = new HashMap[3];
		for (int i = 0; i < 3; i++) {
			str[i] = br.readLine().toCharArray();
		}
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i <3; i++) {
			map[i] = new HashMap<>();
			func(i,0, 0,"");
		}
		TreeMap<String, Integer> tree = new TreeMap<>();
		for (int i = 0; i < 3; i++) {
			for (String key : map[i].keySet()) {
				tree.put(key,tree.getOrDefault(key, 0)+map[i].get(key));
			}
		}
		StringBuilder sb = new StringBuilder();
		
		for (String string : tree.keySet()) {
			if(tree.get(string) >= 2) {
				sb.append(string+"\n");
			}
		}
		if(sb.length() ==0) {
			sb.append(-1);
		}
		System.out.println(sb.toString());
	}

	static void func(int n, int idx,int cnt, String C) {
		if (cnt == k) {
			map[n].put(C, map[n].getOrDefault(C, 0) + 1);
			return;
		}

		for (int i = idx; i < str[n].length; i++) {
				func(n,i+1, cnt + 1, C + str[n][i]);
		}

	}
}
