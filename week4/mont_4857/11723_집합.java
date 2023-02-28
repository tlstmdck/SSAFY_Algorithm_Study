import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		boolean[] S = new boolean[21];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int X = 0;
			switch(str) {
			case "add":
				X = Integer.parseInt(st.nextToken());
				S[X] = true;
				break;
			case "remove":
				X = Integer.parseInt(st.nextToken());
				S[X] = false;
				break;
			case "check":
				X = Integer.parseInt(st.nextToken());
				if(S[X]) { 
					sb.append(1+"\n");
				}else {
					sb.append(0+"\n");
				}
				break;
			case "toggle":
				X = Integer.parseInt(st.nextToken());
				S[X] = !S[X];
				break;
			case "all":
				Arrays.fill(S, true);
				break;
			case "empty":
				S = new boolean[21];
				break;
			}
		}
		System.out.println(sb.toString());
		
	}
}
