package wogus0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Bj1764_듣보잡_S4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		HashSet<String> arr = new HashSet<>();
		for (int i = 0; i < a; i++) {
			arr.add(br.readLine());
		}

		ArrayList<String> arr1 = new ArrayList<>();
		for (int i = 0; i < a; i++) {
			String tmp = br.readLine();
			if (arr.contains(tmp)) {
				arr1.add(tmp);
			}
		}

		Collections.sort(arr1);
		
		System.out.println(arr1.size());
		for (String name : arr1) {
			System.out.println(name);
		}
	}

}