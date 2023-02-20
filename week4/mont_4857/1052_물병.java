import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int result = 0;
		
		// bitCount 사용O
		while(Integer.bitCount(N)>K) {
			N += 1;
			result += 1;
		}
		System.out.println(result);
		
//		// bitCount 사용X
//		while(true) {
//			// 2진수로 바꾼 문자열
//			String str= Integer.toBinaryString(N);
//			int cnt = 0;
//			for (int i = 0; i < str.length(); i++) {
//				// 문자열의 '1' 개수 찾기
//				if(str.charAt(i) == '1') {
//					cnt++;
//				}
//			}
//			// 개수 비교
//			if(cnt <= K) {
//				break;
//			}else {
//				result++;
//				N += 1;
//			}
//		}
//		System.out.println(result);
	}
}
