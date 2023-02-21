package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B4358_생태학 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> map = new HashMap<>(); //나무와 빈도
		int cnt = 0; //나무 총 개수
		String name = ""; //나무 이름
		
		// 이름 입력
		while ((name=br.readLine())!=null) {
			try{
				//처음 보는 나무면 빈도 0
				map.putIfAbsent(name, 0);
				// 빈도 증가 후 삽입
				int value = map.get(name) + 1;
				map.put(name, value);
				// 이름 입력과 함께 총 나무 수+1
				cnt++;
			}catch(Exception e) {
//				System.out.println("무한 입력 오류");
				break;
			}
		}
		// 엔트리들을 리스트로 만들기 (정렬하기위해)
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		//이름 순으로 정렬
		list.sort((s1, s2)->s1.getKey().compareTo(s2.getKey()));
		
		// 맵 순회하며 각 나무의 비율 구해서 출력
		for (Map.Entry<String, Integer> e : list) {
			System.out.printf("%s %.4f\n", e.getKey(), (double)e.getValue()/cnt*100.0);
		}
	}
}
