package wogus0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj11723_집합_S1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		List<Integer> list = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = st.countTokens();
			String command = st.nextToken();
			String tmp = "";
			if(temp == 2) {
				tmp = st.nextToken();
			}
			switch (command) {
			case "add": //추가
				if (!list.contains(Integer.parseInt(tmp))) {
					list.add(Integer.parseInt(tmp));
				}
				break;

			case "remove": //제거
				if (list.contains(Integer.parseInt(tmp))) {
					list.remove(new Integer(tmp));
				}
				break;

			case "check": //검사
				if (list.contains(Integer.parseInt(tmp))) {
					sb.append("1\n");
					break;
				} else {
					sb.append("0\n");
				}
				break;

			case "toggle": // 바꿈
				if (list.contains(Integer.parseInt(tmp))) {
					list.remove(new Integer(tmp));
				} else {
					list.add(Integer.parseInt(tmp));
				}
				break;

			case "all": // 다 바꿈
				list = new ArrayList<Integer>();
				for (int j = 1; j <= 20; j++) {
					list.add(j);
				}
				break;

			case "empty": // 지워
				list = new ArrayList<Integer>();
				break;
			}

		}
		System.out.println(sb);
	}

}