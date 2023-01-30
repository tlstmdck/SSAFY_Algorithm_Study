import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = new char[26];				   // 기본형인 char형 배열 생성 시 공백('')으로 초기화
		boolean[] visit;                                                         // 해당 알파벳에 왔었는지 확인하는 배열
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			arr[str.charAt(0)-'a'] = str.charAt(str.length()-1);   // str.charAt(0)-'a' ==> "a is b".charAt(0) - 'a' ==> 'a' -'a' ==> 0, arr[0] = b( a is b의 경우);
,		}						// 해당 문자열의 첫 문자를 배열의 인덱스, 마지막 인덱스를 배열의 값으로 설정
		int M = Integer.parseInt(br.readLine());
		boolean isT;                                                           // 목적지에 도착 여부를 나타내는 변수
		for (int i = 0; i < M; i++) {
			isT = false;				 // 매 논제에서 목표 도착 여부 변수 초기화
			String tmp = br.readLine();			 // 알고 싶은 논제
			visit = new boolean[26];                                // 매 논제에서 방문여부를 초기화
			int to = tmp.charAt(0) - 'a';                            // 변수타입 확인 -> int(배열의 인덱스로 사용하기 위함)
			char end = tmp.charAt(tmp.length()-1);             // 변수타입 확인 -> char(목표 알파벳)
			visit[to] = true;
			while(true) {                                              // A is B -> B is C -> C is D  ==> A is D
				if(arr[to] == end) {                          // 목표에 도착 시 while문 종료
					isT = true;
					break;
				}
				if(arr[to]!='\0' && !visit[arr[to]-'a'] ) {  // 배열이 공백이 아니고 방문하지 않은 경우
					to = arr[to] - 'a';               // to에 해당 알파벳의 인덱스를 대입
				}else {                                        
					break;                            // 배열이 공백(해당 알파벳으로 시작하는 논제가 없는 경우) or 방문한 경우
				}				// 도착이 불가능하므로 break문 추가
				
			}
			if(isT) {                                                   // while문 돌면서 목표에 도착 유무
				System.out.println("T");
			}else {
				System.out.println("F");
				
			}
		}
	}
}
