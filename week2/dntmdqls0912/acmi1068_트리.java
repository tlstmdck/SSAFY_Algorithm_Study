import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


// 노드 클래스를 정의하여 각 부모 - 자식 관계 정보를 저장
// 맵을 이용해 Linkedlist의 색인을 O(1)로 만듬

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, Node> map = new HashMap<>();

        Node root = null;
        int sum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        // map 테이블 초기화
        for(int i=0; i<n; i++) {
            map.put(i, new Node(i));
        }

        // 부모 - 자식 관계 입력
        for(int i=0; i<n; i++) {
            // 현재 인덱스 : 자식, 현재 입력값 : 부모
            int parent = Integer.parseInt(st.nextToken());
            Node child = map.get(i);

            // root일 경우
            if (parent == -1) {
                root = child;

            } else {
                Node parentNode = map.get(parent);
                // 자식은 부모를 지정, 부모에게는 자식을 추가
                child.parent = parentNode;
                parentNode.addChilds(child);
            }
        }

        int del = Integer.parseInt(br.readLine());
        // 만약 root가 삭제되면 바로 0을 출력하고 리턴
        if (root.data == del) {
            System.out.println(0);
            return ;
        }

        // BFS를 이용해 삭제노드와 그 자식들을 삭제
        Queue<Node> q = new LinkedList<>();
        Node delNode = map.get(del);
        q.add(delNode);


        Node delParent = delNode.parent;
        delParent.childs.remove(delNode); // 삭제 노드의 부모에게서 삭제노드를 제거

        while(!q.isEmpty()) {
            Node node = q.poll();
            node.alive = false;

            for(Node child : node.childs) {
                q.add(child);
            }
        }

        // map에서 각 노드를 검사하여 노드가 살아있고, 자식이 없으면 leaf노드로 판단하여 계산
        for(Node node : map.values()) {
            if (node.alive && node.childs.size() == 0) {
                sum++;
            }
        }

        System.out.println(sum);
    }
}

class Node {
    // 노드 삭제 여부 (false = 삭제됨)
    boolean alive = true;
    // 노드 인덱스
    int data;
    // 부모 노드
    Node parent = null;
    // 자식 노드들
    List<Node> childs = new ArrayList<>();

    public Node (int data) {
        this.data = data;
    }

    public void addChilds(Node child) {
        this.childs.add(child);
    }
}