package week1;

import java.util.Scanner;

public class b7568 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		int n = sc.nextInt();
		int person[][]=new int[n][3];
		
		for(int i=0;i<n;i++) 
			for(int j=0;j<2;j++) 
				person[i][j]= sc.nextInt();
		
		for(int i=0;i<n;i++) {
			int cnt=0;
			for(int j=0;j<n;j++) 
				if (person[i][1]< person[j][1] &&  person[i][0]<person[j][0]) {
					cnt++;
				}
			person[i][2]=cnt+1;
		}
		
		for(int i=0;i<n;i++) 
			System.out.print(person[i][2]+" ");
		
	}
}
