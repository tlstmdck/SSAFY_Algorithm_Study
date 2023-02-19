package boj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Boj25328 {
    static Map<String, Integer> word;
    static String[] str; // x, y, z를 받을 배열
    static boolean[] visited;
    static int k;

    static void combi(int start, int dept, String s, int targetStrNum) {
        if (dept == k) {
            if (word.containsKey(s)) {
                int cnt = word.get(s);
                word.put(s, cnt + 1);
            } else {
                word.put(s, 1);
            }
            return;
        }

        for (int i = start; i < str[targetStrNum].length(); i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            combi(i + 1, dept + 1, s + str[targetStrNum].charAt(i), targetStrNum);
            visited[i] = false;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        str = new String[3];
        for (int i = 0; i < 3; i++) {
            str[i] = sc.nextLine();
        }

        k = sc.nextInt();

        // end input

        word = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            visited = new boolean[str[i].length()];
            combi(0, 0, "", i);
        }

        // key 순으로 map 정렬
        Object[] mapkey = word.keySet().toArray();
        Arrays.sort(mapkey);
        boolean toggle = false;
        StringBuilder sb = new StringBuilder();

        // print
        for (int i = 0; i < mapkey.length; i++) {
            if(word.get(mapkey[i]) >= 2) {
                sb.append(mapkey[i] + "\n");
                toggle = true;
            }
        }

        if(!toggle)
            System.out.println("-1");
        else
            System.out.println(sb.toString());


    }


}
