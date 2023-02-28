package silver2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * X Y Z 입력
 * 부분문자열 길이 입력
 * XYZ에 대해 부분문자열 조합 생성.
 * 생성된 부분문자열에 대한 빈도를  나타내는 map 생성
 * 그 map에 부분문자열의 빈도 추가
 * 마지막에 map순회하며 빈도가 2 이상인 것 있으면 출력하고 없으면 -1 (flag이용)
 * 출력할 때는 오름차순 정렬하여 해야 함. 
 * */
public class B25328_문자열집합조합하기 {
	static int k;
	static Map<String, Integer>map = new HashMap<String, Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String X = sc.nextLine();
		String Y = sc.nextLine();
		String Z = sc.nextLine();
		k = sc.nextInt();
		
		// X, Y, Z에 대한 부분문자열 생성하고 빈도 검사
		combi(X.toCharArray(), new char[k], 0, 0);
		combi(Y.toCharArray(), new char[k], 0, 0);
		combi(Z.toCharArray(), new char[k], 0, 0);
		
		// map의 String에 대해 오름차순 정렬
		// 정렬하려면 map의 엔트리셋을 전부 넣은 리스트를 만든 후 거기서 엔트리의 key기반으로 정렬해야함
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort((e1, e2)->e1.getKey().compareTo(e2.getKey()));
		
		// 출력부분
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (Map.Entry<String, Integer> e : list) {
			if(e.getValue() >= 2) { 
				sb.append(e.getKey()).append('\n');
				flag = true;
			}
		}
		if(flag) System.out.print(sb);
		else System.out.println(-1);
	}
	
	// 문자열로부터  부분 문자열들의 개수를 구하는 조합 시작. 
	// cards[] : 각 문자열의 문자들(조합용 재료)
	// result[] : 조합 완성된 부분문자열. 길이는 k
	// idx는 result를 가리킬 인덱스
	// start는 cards를 가리킬 인덱스
	static void combi(char[] cards, char[] result, int idx, int start) {
		// 기저조건: 부분문자열 생성 완료
		if(idx == k) {
//			System.out.print(Arrays.toString(result)+": ");
			String res = String.valueOf(result); //완성된 부분문자열을 string으로 변경
//			System.out.println(res);
			map.putIfAbsent(res, 0); //처음 나온 부분문자열이면 빈도 1
			map.put(res, map.get(res)+1); //더 나왔을 때 빈도 늘리기
			
			return;
		}
		for (int i = start; i < cards.length; i++) {
			result[idx] = cards[i];
			combi(cards, result, idx+1, i+1);
		}
	}

}
