package basic1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//1406 에디터
/* 배열, 문자열 방식으로 처리하면 삭제와 삽입에 O(n)만큼 듬(문자열 길이만큼)
 * 커서 기준 왼쪽 스택과 오른쪽 스택으로 나눈다
 * 그럼 push와 pop할 때 O(1)만큼만 할 수 있음
 * */
public class B1406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		int N = Integer.parseInt(br.readLine());
		
		/* 커서 기준 왼쪽 스택과 오른쪽 스택으로 나눈다 */
		Stack<Character>left = new Stack<>();
		Stack<Character>right = new Stack<>();
		
		/* 커서가 맨 뒤에 있으므로 왼쪽 스택에 전부 넣기 */
		for(int i=0; i<str.length(); i++) {
			left.push(str.charAt(i));
		}
		
		while(N-->0) {
			//command를 띄어쓰기로 분리
			String command = br.readLine();
			
			//명령어에 따른 수행
			switch (command.charAt(0)) {
			case 'L':
				if(!left.empty()) //커서가 문장 맨 앞일 시 무시 
					right.push(left.pop()); //커서를 왼쪽으로 옮김으로써 문자 이동
				break;
			case 'D':
				if(!right.empty()) //커서가 문장 맨 뒤일 시 무시
					left.push(right.pop());
				break;
			case 'B':
				if(!left.empty()) //커서가 문장 맨 앞일 시 무시
					left.pop(); //커서 왼쪽 문자 삭제
				break;
			case 'P':
				left.push(command.charAt(2));
			default:
				break;
			}
		}
		/* 왼쪽 스택을 전부 오른쪽으로 옮기면 최종 문장 완성 */
		while(!left.empty())
			right.push(left.pop());
		
		/* 오른쪽 스택에서 전부 pop해서 문장 만들기 */
		while(!right.empty())
			sb.append(right.pop());
		
		System.out.println(sb);
	}
}
