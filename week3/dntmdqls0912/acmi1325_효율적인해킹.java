import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
//    최대 해킹수를 저장하는 dp, 매번 탐색 여부를 확인하는 visited
    static int[] dp = new int[10001], visited;
    static int mx, n, m;
//    간선 정보를 저장할 2차원 ArrayList
    static List<List<Integer>> v = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

//        2차원 배열 초기화
        for(int i=0; i<=n; i++) {
            v.add(new ArrayList<>());
        }


        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
//            자신을 신뢰하는 컴퓨터 목록 저장
            v.get(b).add(a);
        }

        for(int i=1; i<=n; i++) {
            visited = new int[10001];
            dp[i] = dfs(i);
            mx = Math.max(dp[i], mx);
        }

        for(int i=1; i<=n; i++) {
            if (mx == dp[i]) {
                System.out.print(i+" ");
            }
        }

    }

//    자신으로부터 자식을의 모든 노드들을 dfs로 탐색
//    탐색한 노드의 수를 반환
    public static int dfs(int here) {
        visited[here] = 1;
        int ret = 1;
        for(int there : v.get(here)) {
            if(visited[there] == 1) continue;
            ret += dfs(there);
        }
        return ret;
    }
}