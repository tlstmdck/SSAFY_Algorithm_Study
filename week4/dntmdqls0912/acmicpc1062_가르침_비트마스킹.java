import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main2 {

    static int N, K, maxWordCount = 0, charSet;
    static int[] words;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        if (K < 5) {
            System.out.println(0);
            return;
        }

        words = new int[N];

        charSet |= 1 << 'a' - 'a';
        charSet |= 1 << 'n' - 'a';
        charSet |= 1 << 't' - 'a';
        charSet |= 1 << 'i' - 'a';
        charSet |= 1 << 'c' - 'a';

        /*
         * 단어 조합에 반드시 들어가야하는 a, n, t, i, c 다섯개를 select에 미리 포함시켜둔다
         */
        int select = charSet;



        for(int i=0; i<N; i++) {
            char[] word = br.readLine().toCharArray();
            for(char c : word) {
                words[i] |= 1 << c - 'a';
                charSet |= 1 << c - 'a';
            }
        }

//        모든 단어를 만들수 있음
        if (Integer.bitCount(charSet) <= K) {
            System.out.println(N);
            return;
        }

//       이미 기본 알파벳 5개가 들어가 있으므로, cnt를 5부터 시작
        comb(0, 5, select);

        System.out.println(maxWordCount);
    }

    public static void comb(int idx, int cnt, int select) {
        if (cnt == K) {
            int wordCount = 0;

            for(int word : words) {
                if ((select & word) == word) wordCount++;
            }

            maxWordCount = Math.max(maxWordCount, wordCount);
            return;
        }

//        조합 생성
        for(int i=idx; i<26; i++) {
            /*
             * 문자가 이미 선택된 것 이거나, charSet에 존재하지 않는다면 continue
             */
            if ((select & (1 << i)) == 1 || (charSet & (1 << i)) == 0) continue;

            comb(i+1, cnt+1, select | (1 << i));
        }
    }
}