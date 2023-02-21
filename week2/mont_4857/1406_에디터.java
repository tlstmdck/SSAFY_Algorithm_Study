import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] str= br.readLine().toCharArray();
		int N = Integer.parseInt(br.readLine());
		Stack<Character> arrLeft = new Stack<>();
		Stack<Character> arrRight = new Stack<>();
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			arrLeft.add(str[i]);
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if(cmd.equals("P")) {
				String s = st.nextToken();
				arrLeft.add(s.charAt(0));
			}else if(cmd.equals("L")) {
				if(!arrLeft.isEmpty())
					arrRight.add(arrLeft.pop());
			}else if(cmd.equals("D")) {
				if(!arrRight.isEmpty())
					arrLeft.add(arrRight.pop());
			}else {
				if(!arrLeft.isEmpty())
					arrLeft.pop();	
			}
		}
		for (int i = 0; i < arrLeft.size(); i++) {
			sb.append(arrLeft.get(i));
		}
		
		while(!arrRight.isEmpty()) {
			sb.append(arrRight.pop());
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}