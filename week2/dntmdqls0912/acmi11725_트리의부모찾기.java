import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 리프가 몇개인지 모르는 트리 문제이므로 map<Integer, Set<Integer>>를 이용해 그래프 그리기
// treeMap의 특성을 이용해 각 노드의 부모를 저장하고 순서대로 출력하기
// 부모 탐색을 위한 BFS 완전 탐색 사용

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
//        부모를 저장하며 동시에 visited의 역할 수행
        SortedMap<Integer, Integer> map = new TreeMap<>();
        Queue<Integer> q = new LinkedList<>();

//        각 노드들의 연결 관계를 그래프에 삽입
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int dot1 = Integer.parseInt(st.nextToken());
            int dot2 = Integer.parseInt(st.nextToken());

            Set<Integer> dot1Set = graph.getOrDefault(dot1, new HashSet<Integer>());
            Set<Integer> dot2Set = graph.getOrDefault(dot2, new HashSet<Integer>());

            dot1Set.add(dot2);
            dot2Set.add(dot1);

            graph.put(dot1, dot1Set);
            graph.put(dot2, dot2Set);
        }

//        BFS 수행 시작지점 (루트)
        q.add(1);
        map.put(1, 1);
        while(!q.isEmpty()) {
            int parent = q.poll();
            Set<Integer> set = graph.get(parent);
            
//            현재 노드의 자손들 중 아직 방문하지 않은 자손들을 큐에 삽입
            for(int child : set) {
                if (map.get(child) == null) {
//                    부모 정보 저장 및 visit 체크
                    map.put(child, parent);
                    q.add(child);
                }
            }
        }
//       2부터 출력해야 하므로 1 제거
        map.remove(1);
//        람다식 출력
        map.forEach((key, value) -> System.out.println(value));
    }
}