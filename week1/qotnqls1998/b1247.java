package week1;

import java.util.Scanner;

import java.util.*;
import java.math.*;

public class b1247 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		BigInteger m ;
		BigInteger sum = BigInteger.valueOf(0);
		char[] sign = new char[3];
		
		for(int i=0;i<3;i++) {
			n =sc.nextInt();
			sum  = BigInteger.valueOf(0);
			for(int j=0;j<n;j++) {
				m= sc.nextBigInteger();
				sum = sum.add(m);
			}
		
			if( sum.compareTo(BigInteger.ZERO ) ==1 )sign[i]='+';
			
			else if(sum.compareTo(BigInteger.ZERO ) == -1)sign[i]='-';
			else if(sum.compareTo(BigInteger.ZERO ) == 0)sign[i]='0';
		}
		for(char c:sign) {
			System.out.println(c);
		}
	}
}
