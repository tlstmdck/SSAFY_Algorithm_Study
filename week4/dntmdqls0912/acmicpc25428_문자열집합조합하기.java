import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int K;
    static char[] buffer;
    static SortedMap<String, Integer> map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] words = new String[3];
        words[0] = br.readLine();
        words[1] = br.readLine();
        words[2] = br.readLine();
        K = Integer.parseInt(br.readLine());
        buffer = new char[K];

        map = new TreeMap<>();
//       각 단어에 대하여 독립적으로 조합을 시행
        comb(words[0], 0, 0);
        comb(words[1], 0, 0);
        comb(words[2], 0, 0);

//        map의 각 원소에 대하여
        map.forEach((key, value) -> {
//           count value가 1 보다 클 경우 출력
            if (value > 1) {
                sb.append(key).append("\n");
            }
        });

//       출력할 내용이 아무것도 없을 경우 -1 출력        
        if (sb.length() == 0) {
            sb.append(-1);
        }
        System.out.println(sb);
    }

//   각 단어에 대한 조합 생성
    public static void comb(String word, int idx, int cnt) {
        if (cnt == K) {
//           조합을 String으로 만들어 map에 저장
            String str = String.valueOf(buffer);
            map.put(str, map.getOrDefault(str, 0)+1);
            return;
        }

        for(int i=idx; i<word.length(); i++) {
            buffer[cnt] = word.charAt(i);
            comb(word, i+1, cnt+1);
        }
    }

}