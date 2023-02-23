package week1;

import java.util.Scanner;

public class B13458 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] student = new int[n];
		long cnt =0;
		
		for(int i=0;i<n;i++) {
			student[i]=sc.nextInt();
		}
		int b = sc.nextInt();
		double c = sc.nextInt();	
		
		for(int i=0;i<n;i++) {
			student[i]-=b;
			cnt++;

			if (student[i]>0) {
				cnt+= student[i]/c ;
				if(student[i]%c!=0) cnt++;
			}
		}
		System.out.println(cnt);
	}
}
