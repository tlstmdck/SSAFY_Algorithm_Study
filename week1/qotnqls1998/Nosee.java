package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nosee {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		Set<String> nolisten = new HashSet<>();
		List<String> result = new ArrayList<>();
		
		String[] input = bf.readLine().split(" ");
		int n= Integer.parseInt(input[0]);
		int m= Integer.parseInt(input[1]);
		
		for(int i=0;i<n;i++) {
			String tmp = bf.readLine(); 
			nolisten.add(tmp);
		}
		
		for(int j=0;j<m;j++) {
			String tmp = bf.readLine(); 
			if(nolisten.contains(tmp)) {
				result.add(tmp);
			}
		}
		result.sort(String.CASE_INSENSITIVE_ORDER);
		System.out.println(result.size());
		
		for(String a:result)
			System.out.println(a);  
  
	}
}
