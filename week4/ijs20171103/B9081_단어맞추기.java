package silver1;

import java.util.*;
import java.io.*;

/*
단어 -> charArray -> 정렬
정렬된 리스트에 대해 다음 순열 찾기 실행
// 예) 12543 -> 13542 -> 13245
 * */
public class B9081_단어맞추기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb =new StringBuilder();
				
		int T = sc.nextInt();
		sc.nextLine();
		for (int t = 1; t <= T; t++) {
			char[] str = sc.nextLine().toCharArray();
			sb.append(String.valueOf(np(str))).append('\n');
		}
		System.out.println(sb);
	}
	static char[] np(char[] str) { //다음 순열 알고리즘
		for (int i = str.length-1; i >= 1; i--) {
			if(str[i]>str[i-1]) { //꼭대기 지점(i) 찾기
				System.out.println("i: "+str[i]+" i-1: "+str[i-1]);
				for (int j = str.length-1; j >= 1; j--) { 
					if(str[j]>str[i-1]) { // i-1에 있는 것보다 더 큰 문자가 있으면
						System.out.println("j: "+str[j]+" i-1: "+str[i-1]);
						char temp = str[i-1];
						str[i-1] = str[j];
						str[j] = temp; 
						// end swap
						System.out.println("end swap: "+Arrays.toString(str));
						
						str = swapLast(str, i);
						System.out.println("end swap last: "+Arrays.toString(str));
						return str;
					}
				}
			}
		}
		return str;
	}
	static char[] swapLast(char[] str, int i) {
		int lastIdx = str.length-1;
		for (int j = i; j <= (i+str.length-1)/2; j++) {
			char temp = str[j];
			str[j] = str[lastIdx];
			str[lastIdx--] = temp;
		}
		return str;
	}
}
