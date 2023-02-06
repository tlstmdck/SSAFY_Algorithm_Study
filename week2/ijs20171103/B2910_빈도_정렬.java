package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B2910_빈도_정렬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //N입력
		int C = Integer.parseInt(st.nextToken()); //C입력
		
		st = new StringTokenizer(br.readLine()); //배열 입력
		
		Map<Integer, int[]> map = new HashMap<>(); //숫자 key, 빈도와 등장순서value가 있는 map
		int order = 1;
		while(st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			
			if (!map.containsKey(n)) { //n이 나온적 없는 숫자면
				int[] arr = new int[2];
				arr[0] = 1; arr[1] = order++; //빈도는 1, 순서 지정
				map.put(n, arr); //첫등장하는 숫자일 시 
			}
			else { //나온 적 있는 숫자면
				int[] arr = map.get(n); //n의 빈도, 등장순서 가져오기
				arr[0]+=1;  //빈도 늘리기
				map.put(n, arr); //넣기
			}
		}
		///////////////////////입력완료//////////////////////////
		List<Map.Entry<Integer, int[]>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, (o1, o2)-> {
			int[] o1_value = o1.getValue(); //어떤 숫자의 빈도와, 등장순서
			int[] o2_value = o2.getValue(); //다른 숫자의 빈도와, 등장순서
			
			if(o1_value[0] == o2_value[0]) { //빈도 같으면?
				return o1_value[1]-o2_value[1]; //등장순서가 더 낮은 숫자가 앞으로
				// o1[1]이 더 낮은 숫자여야 음수가 나와 교환하지 않음
			}else { //빈도 다르면?
				return o2_value[0]-o1_value[0]; //빈도가 더 큰 숫자가 앞으로
				// o1[0]이 더 큰 숫자여야 양수가 나와 교환됨
			}
		});
		//////////////////////정렬완료/////////////////////////
		StringBuilder res = new StringBuilder(); //결과출력용
		for (Map.Entry<Integer, int[]> entry : list) {
			for (int i = 0; i < entry.getValue()[0]; i++) { //빈도만큼 숫자출력
				res.append(entry.getKey()+" "); 
			}
		}
		System.out.println(res);
	}
}