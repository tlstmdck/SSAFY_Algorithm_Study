package week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class b2910_frequency {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int c = sc.nextInt();
		
		HashMap<Integer, Integer> map = new HashMap<>(c);
		
		int[] cnt = new int[c+1];
		
		for(int i=0;i<n;i++) {
			int tmp = sc.nextInt();
			map.put(tmp,++cnt[tmp]);
		}
		
		for(int i=0;i<n;i++) {
			if(map.get(i) != null)
				for(int j=0;j<map.get(i);j++)
					System.out.print(i+" ");
		}
		
	}
}
