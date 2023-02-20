import java.io.*;

import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str;
		char[] strArr;
		int start = -1;
		char[] arr1 = null;
		char[] arr2 = null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			boolean isT = false;
			str = br.readLine();
			strArr = str.toCharArray();
			if(strArr.length == 1) {
				sb.append(str +"\n");
				continue;
			}
			
			int firstIdx= 0;
			for (int j = strArr.length -1 ; j >= 1; j--){
				if(strArr[j - 1] < strArr[j]) {
					firstIdx = j -1;
					isT = false;
					break;
				}
				isT =true;
			}
			if(isT) {
				sb.append(str + "\n");
				continue;
			}
			for (int j = strArr.length -1 ; j > firstIdx; j--) {
				if(strArr[firstIdx] < strArr[j]) {
					char tmp = strArr[firstIdx];
					strArr[firstIdx] = strArr[j];
					strArr[j] = tmp;
					arr1 = Arrays.copyOfRange(strArr, 0, firstIdx+1);
					arr2 = Arrays.copyOfRange(strArr, firstIdx+1, strArr.length);
					Arrays.sort(arr2);
					break;
				}
			}
			for (char c : arr1) {
				sb.append(c);
			}
			for (char c : arr2) {
				sb.append(c);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
