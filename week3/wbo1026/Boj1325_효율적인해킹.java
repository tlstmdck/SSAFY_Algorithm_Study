package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1325 {
    static int N, M, cnt;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static Queue<Integer> q;
    static int[] rnt;

    static void bfs(int start) {

        q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int selectPC = q.poll();
            for (int infection : list[selectPC]) {
                if(!visited[infection]) {
                    visited[infection] = true;
                    cnt++;
                    q.add(infection);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 컴퓨터 갯수
        M = Integer.parseInt(st.nextToken()); // 관계 수
        list = new ArrayList[N + 1];
        rnt = new int[N + 1]; // idx = PC번호, value = 전염 개수

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            list[B].add(A);
        }

        // end input

        int max = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            cnt = 0;
            bfs(i);
            rnt[i] = cnt;
            max = Math.max(cnt, max);
        }

        for (int i = 1; i <= N; i++) {
            if(max == rnt[i])
                sb.append(i + " ");
        }

        System.out.println(sb.toString());
    }
}
