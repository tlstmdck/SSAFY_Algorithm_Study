import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 숫자가 낮은 정점부터 먼저 방문해야 하므로
// sortedSet을 가진 Map으로 그래프를 구현한 후
// 그래프를 각각 dfs와 bfs로 탐색하여 출력

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

//         그래프를 담을 map과 방문여부 visit 정의
        Map<Integer, SortedSet<Integer>> map = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        
//         시작점이 완전히 고립된(isolated) 정점일 수 있으므로, 시작점도 그래프에 초기화
        map.put(s, new TreeSet<Integer>());
        visited.put(s, false);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

//             양방향 그래프이므로 start와 end 두 정점 모두의 set을 가져옴
            SortedSet<Integer> startSet = map.getOrDefault(start, new TreeSet<Integer>());
            SortedSet<Integer> endSet = map.getOrDefault(end, new TreeSet<Integer>());

//             각각의 정점에 반대쪽 정점을 추가
            startSet.add(end);
            endSet.add(start);

//             그래프 업데이트
            map.put(start, startSet);
            map.put(end, endSet);
            
//             visit 맵 생성
            visited.put(start, false);
            visited.put(end, false);
        }
        
//         시작지점 설정 후 DFS 실행
        DFS(map, visited, s);
        
//         visit 맵 초기화
        visited.replaceAll((i, v) -> false);
        System.out.println();
        BFS(map, visited, s);

    }

    public static void DFS(Map<Integer, SortedSet<Integer>> map, Map<Integer, Boolean> visited, int enter) {
//         방문한 정점 체크
        visited.put(enter, true);
        System.out.print(enter + " ");
//         방문하지 않은 정점 DFS 실행
        for(int i : map.get(enter)) {
            if (!visited.get(i)) {
                DFS(map, visited, i);
            }
        }
    }

    public static void BFS(Map<Integer, SortedSet<Integer>> map, Map<Integer, Boolean> visited, int enter) {
        Queue<Integer> q = new LinkedList<>();
//         시작점 설정
        q.add(enter);
        visited.put(enter, true);
//         큐가 빌때까지 BFS 실행
        while(!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
//             연결된 모든 정점 중 방문하지 않은 정점을 큐에 삽입
            for(int i : map.get(node)) {
                if (!visited.get(i)) {
                    visited.put(i, true);
                    q.add(i);
                }
            }
        }
    }

}
