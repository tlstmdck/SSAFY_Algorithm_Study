import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 풀이 시간 : 15분
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());    // 듣지 못한 사람 수
		int M = Integer.parseInt(st.nextToken());    // 보지 못한 사람 수
		Set<String> h = new TreeSet<>();             // 들어 보지 못했던 사람들
		Set<String> result = new TreeSet<>();        // 듣보잡 사람들
		for (int i = 0; i < N; i++) {
			h.add(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			String tmp = br.readLine();
			if(h.contains(tmp)) {                    // 들어 보지 못한 사람들 중 있는 경우
				result.add(tmp);
			}
		}
		System.out.println(result.size());
		for (String string : result) {
			System.out.println(string);
		}
	}
}