import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        boolean[] set = new boolean[21];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            switch (com) {
                case "add":
                    set[Integer.parseInt(st.nextToken())] = true;
                    break;
                case "remove":
                    set[Integer.parseInt(st.nextToken())] = false;
                    break;
                case "check":
                    // StringBuilder 안쓰면 시간초과 발생
                    sb.append(set[Integer.parseInt(st.nextToken())] ? 1 : 0).append("\n");
                    break;
                case "toggle":
                    set[Integer.parseInt(st.nextToken())] ^= true;
                    break;
                case "all":
                    Arrays.fill(set, true);
                    break;
                default:
                    set = new boolean[21];
            }
        }
        System.out.println(sb);
    }
}