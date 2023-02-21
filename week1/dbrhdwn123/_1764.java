package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import java.util.HashSet;

import java.util.StringTokenizer;

public class _1764 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		HashSet<String> check = new HashSet<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int nolisten = Integer.parseInt(st.nextToken());
		int nolook = Integer.parseInt(st.nextToken());

		ArrayList<String> arr = new ArrayList<>(check);
		for (int i = 0; i < nolisten; i++) {
			check.add(br.readLine());
		}

		for (int i = 0; i < nolook; i++) {
			String tmp = br.readLine();
			if (check.contains(tmp)) {
				arr.add(tmp);
			}
		}

		Collections.sort(arr);

		System.out.println(arr.size());
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}

	}

}