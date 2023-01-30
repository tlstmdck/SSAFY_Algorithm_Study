import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 문제 해결 시간 : 약 1시간
// a는 b이면서 c일 수는 없다 -> 하나의 전제는 하나의 결과만을 가짐

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        // 전제 - 결과 쌍의 맵
        Map<String, String> nodes = new HashMap<>();
        
        // 전제 - 결과 쌍 입력
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	String a = st.nextToken();
        	st.nextToken(); // is 날리기
        	String b = st.nextToken();
        	
        	nodes.put(a, b); // a 전제에 b 결과를 넣음
        	nodes.put(b, nodes.getOrDefault(b, null)); // map에서 결과를 검색하므로 결과도 추가
        }
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        
        // n단 논법 결과 확인
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	String premise = st.nextToken();
        	st.nextToken();
        	String result = st.nextToken();
        	
        	// 초기 전제 설정
        	String next = nodes.get(premise);
        	boolean found = false;
        	
        	// 전제 조건의 링크가 끊길 때 까지 계속 탐색
        	while(next != null) {
        		// 현재 전제에 찾는 결과가 있다면
        		if (next.equals(result)) {
        			found = true;
        			break;
        		}
        		next = nodes.get(next);
        	}
        	
        	if (found)
        		System.out.println("T");
        	else
        		System.out.println("F");
        }
    }
}