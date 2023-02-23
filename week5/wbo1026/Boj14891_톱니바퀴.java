package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 시계 방향: 1, 반시계 방향: -1
 * N극 0, S극 1
 */
public class Boj14891_톱니바퀴 {

    static List<List<Integer>> t;
    static boolean[] visited;

    static void compareLR(int select, int way) {

        visited[select] = true; // 방문 체크

        // 왼쪽 비교
        if (select - 1 >= 0 && !visited[select - 1]
                && t.get(select).get(6) != t.get(select - 1).get(2)) {
            compareLR(select - 1, -1 * way);
        }

        // 오른쪽 비교
        if (select + 1 <= 3 && !visited[select + 1]
                && t.get(select).get(2) != t.get(select + 1).get(6)) {
            compareLR(select + 1, -1 * way);
        }

        rotate(select, way); // 왼쪽 오른쪽을 비교했다면 원본훼손 가능

    }

    static void rotate(int select, int way) {

        int trade = 0;

        if (way == 1) {// 시계
            trade = t.get(select).get(7);
            t.get(select).remove(7);
            t.get(select).add(0, trade);
        } else { // 반시계
            trade = t.get(select).get(0);
            t.get(select).remove(0);
            t.get(select).add(trade);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            t.add(new LinkedList<>());
            for (int j = 0; j < str.length(); j++) {
                t.get(i).add(str.charAt(j) - '0');
            }
        }

        // end input

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < K; i++) { // 회전 횟수
            visited = new boolean[4];
            st = new StringTokenizer(br.readLine());
            int select = Integer.parseInt(st.nextToken()) - 1; // 1번 톱니 = 0번
            int way = Integer.parseInt(st.nextToken()); // 방향

            compareLR(select, way);

        }

        int sum = 0;
        int cnt = 0;
        for (List<Integer> ans : t) {
            if(ans.get(0) == 1)
                sum += Math.pow(2, cnt);
            cnt++;
        }

        System.out.println(sum);
    }
}

