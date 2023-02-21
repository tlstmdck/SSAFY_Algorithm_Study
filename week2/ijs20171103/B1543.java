package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1543 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		String command = br.readLine();
		StringBuilder sb = new StringBuilder(); // str에서 한글자씩 읽어들이며 command가 포함돼있는지 검사하기 위한 용도
		int res = 0;

		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i)); //sb에 한글자씩 넣기
			if (sb.toString().contains(command)) { //넣으면서 command를 포함했는지 검사
				res++;
				sb.setLength(0); // command가 포함되었으므로 sb초기화
			}
		}
		System.out.println(res);
	}

}
