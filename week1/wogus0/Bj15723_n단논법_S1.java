package wogus0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj15723_n단논법_S1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N, M, cnt = 0;
		char a, b;
		int[] parent;

		N = Integer.parseInt(br.readLine());
		parent = new int[27];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			a = st.nextToken().charAt(0);
			st.nextToken();
			b = st.nextToken().charAt(0);
			parent[a - 'a' + 1] = b - 'a' + 1;
		}

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = st.nextToken().charAt(0);
			st.nextToken();
			b = st.nextToken().charAt(0);
			a -= ('a' - 1);
			b -= ('a' - 1);

			while (a != b && parent[a] != 0) {
				a = (char) parent[a];
			}
			if (a == b) {
				sb.append("T\n");
			} else {
				sb.append("F\n");
			}

		}
		System.out.println(sb);
	}

}
