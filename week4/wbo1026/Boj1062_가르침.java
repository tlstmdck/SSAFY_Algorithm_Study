package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1062 {

    static int n, k, max;
    static boolean[] visited;
    static String[] word;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        word = new String[n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            word[i] = str.substring(4, str.length() - 4); // 양 쪽에 붙는 문자들은 빼고 배열에 담음
        }

        if (k == 26)  // 전체 알파벳을 배우면
            System.out.println(n);
        else if (k < 5)  // 이미 읽을 수 있는 알파벳 수가 없음
            System.out.println("0");
        else {
            visited = new boolean[26]; // 알파벳을 담을 배열
            visited['a' - 'a'] = visited['n' - 'a'] = visited['t' - 'a'] = visited['i' - 'a'] = visited['c' - 'a'] = true;
            max = Integer.MIN_VALUE;

            check(0, 0);
            System.out.println(max);
        }
    }

    public static void check(int start, int depth) {
        if (depth == k - 5) {
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                boolean isRead = true;
                int len = word[i].length();

                for (int j = 0; j < len; j++) {
                    if (!visited[word[i].charAt(j) - 'a']) {
                        isRead = false;
                        break;
                    }
                }
                if (isRead)
                    cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (visited[i] == false) { // 조합
                visited[i] = true;
                check(i, depth + 1);
                visited[i] = false;
            }
        }
    }
}

