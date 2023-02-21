package week1;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class b1764 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		List<String> nolisten = new ArrayList<>();
		List<String> noSee = new ArrayList<>();

		
		String[] input = bf.readLine().split(" ");
		int n= Integer.parseInt(input[0]);
		int m= Integer.parseInt(input[1]);
		int cnt=0;
		
		for(int i=0;i<n;i++) {
			String tmp = bf.readLine(); 
			nolisten.add(tmp);
		}
		for(int j=0;j<m;j++) {
			String tmp2 = bf.readLine(); 
			noSee.add(tmp2);
		}
		
		nolisten.sort(String.CASE_INSENSITIVE_ORDER);
		nolisten.sort(String.CASE_INSENSITIVE_ORDER);
		
		StringBuilder sb = new StringBuilder();
		for(String name:nolisten) {
			for(String name2:noSee) {
				if (name.equals(name2)) {
					cnt++;
					sb.append(name).append("\n");
					break;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb);
		
	}
}
