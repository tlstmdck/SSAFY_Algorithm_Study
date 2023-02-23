package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11723 {
    static boolean[] nums;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        nums = new boolean[21];
        int M = Integer.parseInt(br.readLine());
        String type = "";
        int x = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            type = st.nextToken();

            if (!(type.equals("all") || type.equals("empty")))
                x = Integer.parseInt(st.nextToken());

            switch (type) {
                case "add":
                    add(x);
                    break;
                case "remove":
                    remove(x);
                    break;
                case "check":
                    check(x);
                    break;
                case "toggle":
                    toggle(x);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
            }
        }

        System.out.println(sb);
    }

    static void add(int x) {
        nums[x] = true;
    }

    static void remove(int x) {
        nums[x] = false;
    }

    static void check(int x) {
        if (!nums[x])
            sb.append("0\n");
        else
            sb.append("1\n");
    }

    static void all() {
        for (int i = 1; i < 21; i++) {
            nums[i] = true;
        }
    }

    static void empty() {
        nums = new boolean[21];
    }

    static void toggle(int x) {
        nums[x] = !nums[x];
    }
}
