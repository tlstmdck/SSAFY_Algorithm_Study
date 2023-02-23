package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1543 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String n = br.readLine();
		String m = br.readLine();

		int j = 0;
		int cnt = 0;
		int res = 0;

		// System.out.println(n.length());
		// System.out.println(m.length());
		//j <= n.length() - m.length()
		while (j <= n.length() - m.length()) {
			cnt = 0;

			for (int i = 0; i < m.length(); i++) {
				// System.out.println(j);

				if (m.charAt(i) == (n.charAt(i + j))) {

					cnt++;
				}
				// System.out.println(j);

			}
			// System.out.println(cnt);
			if (cnt == m.length()) {
				j += m.length();
				res++;
				
			} else if (cnt != m.length()) {
				j++;
				
			}
			
//			if (j > n.length() - m.length())
//				// System.out.println("stop"+j);
//				break;
//			System.out.println(j);
//			
		}

		System.out.println(res);

	}

}
