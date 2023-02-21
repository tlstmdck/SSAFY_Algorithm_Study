import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 각 단어들의 필요 알파벳을 단위로 나누어 set에 저장,
// 필요한 알파벳들의 일부에 대한 조합을 생성해 가장 많은 단어를 만들 수 있는 경우를 찾음 (Brutal Force)

// 기본 필요 알파벳들을 미리 조합에 넣어두어 경우의 수를 최적화

public class Main {

    static int N, K, maxWordCount = 0;
    static List<Character> reqCharsList; // 필요한 char를 담은 리스트
    static List<Character> select; // char 조합
    static char[] defaultChars = {'a', 'n', 't', 'i', 'c'}; // anta, tica 구성 char
    static List<Set<Character>> list; // 각각의 단어들의 필요 char 모음 리스트

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        anta, tica를 만들 최소조건을 만족하지 못함
        if (K < 5) {
            System.out.println(0);
            return;
        }

        list = new ArrayList<>();
        select = new ArrayList<>(Arrays.asList(new Character[K]));
        Set<Character> reqChars = new HashSet<>();

//        필요 char 셋과 조합의 일부에 필수 char를 미리 입력
        for(int i=0; i<5; i++) {
            reqChars.add(defaultChars[i]);
            select.set(i, defaultChars[i]);
        }


//        각 단어들을 받아서 anta, tica를 뺀 나머지 스펠링들을 reqChar와 list의 각각의 set에 넣기
        for(int i=0; i<N; i++) {
            Set<Character> word = new HashSet<>();
            String str = br.readLine();
            for (char c : str.substring(4, str.length() - 4).toCharArray()) {
                word.add(c);
                reqChars.add(c);
            }
            list.add(word);
        }
        
//        모든 단어를 만들수 있음
        if (reqChars.size() < K) {
            System.out.println(N);
            return;
        }

//        default chars 제외
        for(char c : defaultChars) {
            reqChars.remove(c);
        }
//        set을 list로 만들어줌
        reqCharsList = new ArrayList<>(reqChars);
        
//       이미 기본 알파벳 5개가 들어가 있으므로, cnt를 5부터 시작
        comb(0, 5);
        
        System.out.println(maxWordCount);
    }

    public static void comb(int idx, int cnt) {
        if (cnt == K) {
            int wordCount = 0;
//           각 단어들의 필요 최소 알파벳 셋들을 순회
            for(Set<Character> set : list) {
                boolean charExist = true;
                
//                단어의 각 알파벳이 조합에 존재하는지 확인
                for(char c : set) {
                    charExist = select.contains(c);
                    if (!charExist) break;
                }
                if (charExist) {
                    wordCount++;
                }
            }
            maxWordCount = Math.max(maxWordCount, wordCount);
            return;
        }

//        조합 생성
        for(int i=idx; i<reqCharsList.size(); i++) {
            select.set(cnt, reqCharsList.get(i));
            comb(i+1, cnt+1);
        }
    }
}