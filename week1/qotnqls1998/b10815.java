package week1;

import java.util.HashSet;
import java.util.Scanner;

public class b10815 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder result = new StringBuilder();
		int n,m;
		int tmp;
		
		n=sc.nextInt();
		HashSet<Integer> card = new HashSet<>();
	
		for(int i=0;i<n;i++)
			card.add(sc.nextInt());
		
		m=sc.nextInt();
		for(int j=0;j<m;j++) {
			tmp = sc.nextInt();
			if(card.contains(tmp))
				result.append(1).append(" ");
			else result.append(0).append(" ");
		}
		System.out.println(result);
	}
}
