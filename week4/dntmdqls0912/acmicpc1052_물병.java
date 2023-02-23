package codingTest;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		int answer = 0;
		
		
//		 처음 최대한 줄였을 때의 물병의 수 -> N의 비트카운트
//		 N에 1씩 더하면 비트가 합쳐지며 문제조건의 연산이 자동적으로 이루어짐
		while(Integer.bitCount(N) > K) {
			answer++;
			N++;
		}
		
		System.out.println(answer);
		

	}// end main
}