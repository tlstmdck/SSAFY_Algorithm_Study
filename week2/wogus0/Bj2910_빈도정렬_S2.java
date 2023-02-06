package week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Bj2910_빈도정렬_S2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[1001];
		
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		Map<Integer, Integer> m = new LinkedHashMap<>();
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			m.put(arr[i], m.getOrDefault(arr[i], 0)+1);
		}
		
		List<Integer> arr1 = new ArrayList<>(m.keySet());
		Collections.sort(arr1, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return m.get(o2)- m.get(o1);
			}
		});
		for(int key : arr1) {
			for(int i=0;i<m.get(key);i++) {
				sb.append(key+" ");
			}
		}
		
		System.out.println(sb);

	}

}