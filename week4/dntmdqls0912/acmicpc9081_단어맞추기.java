package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			char[] word = br.readLine().toCharArray();
			System.out.println(nextPermutation(word));
		}
		
		
	}// end main
	
	public static String nextPermutation(char[] input) {
		int n = input.length;
		
		int i = n - 1;
		while(0 < i && input[i-1] >= input[i]) i--;
		
		if (i == 0) return String.copyValueOf(input);
		
		int j = n - 1;
		while(input[i-1] >= input[j]) j--;
		
		swap(input, i-1, j);
		
		int k = n - 1;
		while(i < k) swap(input, i++, k--);
		
		return String.copyValueOf(input);
	}
	
	public static void swap(char[] input, int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
}