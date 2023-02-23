package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class b20154 {
	public static void main(String[] args) throws IOException {
		int[] stroke = 
				{3,	2,	1,
				2,	3,	3,
				3,	3,	1,
				1,	3,	1,
				3,	3,	1,
				2,	2,	2,
				1,	2,	1,
				1,	2,	2,
				2,	1};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		int[] arr = new int[s.length()];
		
		//s의 길이만큼 parse
		for (int i=0;i<s.length();i++)
			arr[i]=stroke[s.charAt(i)-'A'];
		
		int sum=0;
		//앞에서 두개씩 계속 더하기
		for (int i=0;i<s.length();i++)
			sum+=arr[i];
			
		//마지막 남은 1의자리가 홀수면 이김s
		// 아니라면 짐
		while(true) {
			if(sum<10)
				break;
			else {
				sum=sum%10;
			}
		}
		if(sum%2==0)
			System.out.println("You're the winner?");
		else System.out.println("I'm a winner!");
	}
}
