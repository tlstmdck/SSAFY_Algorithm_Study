package week2;

import java.io.*;
import java.util.*;

public class Bj4358_생태학_S2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		TreeMap<String, Integer> map = new TreeMap<>();
		
		while (true) {
			String name = br.readLine();
			if(name == null || name.equals(""))
				break;
			
			cnt++;
			if (map.containsKey(name)) {
				map.put(name, map.get(name)+1);
			} else {
				map.put(name, 1);
			}
		}
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			String word = (String) it.next();
			String result = String.format("%.4f", (double)(map.get(word)*100.0)/cnt);
			sb.append(word+ " " + result + "\n");
		}
		
		System.out.println(sb);

	}// main

}// class
