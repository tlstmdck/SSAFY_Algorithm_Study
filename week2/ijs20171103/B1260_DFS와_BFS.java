package silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1260_DFS와_BFS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(st.nextToken());
		Graph g = new Graph(N);

		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int N1 = Integer.parseInt(st.nextToken());
			int N2 = Integer.parseInt(st.nextToken());

			g.makeVertex(N1, N2);
		}
		g.DFS(startNode);
		g.BFS(startNode);
	}
}

class Graph {
	public Map<Integer, ArrayList<Integer>> adjList = new HashMap<>(); // 인접리스트(인접노드들)
	boolean[] visited;

	public Graph(int N) {
		this.visited = new boolean[N + 1]; // 방문검사용 배열
	}

	// N1과 N2간의 Vertex추가
	public void makeVertex(int N1, int N2) {
		// N1 노드가 value로 가지고있는 리스트에 N2추가
		ArrayList<Integer> temp = adjList.get(N1); // n1의 인접노드들

		if (temp == null) // 만약에 비었으면 새로만들기
			temp = new ArrayList<Integer>();

		// 간선만들기
		temp.add(N2);
		adjList.put(N1, temp);
		// ==========================================
		// N2노드가 value로 가지고있는 리스트에 N1추가
		temp = adjList.get(N2); // n1의 인접노드들

		if (temp == null) // 만약에 비었으면 새로만들기
			temp = new ArrayList<Integer>();

		// 간선만들기
		temp.add(N1);
		adjList.put(N2, temp);
	}

	public void DFS(int startNode) {
		// visited초기화 전처리
		Arrays.fill(visited, false);

		Stack<Integer> stack = new Stack<>(); // DFS용 스택

		// 시작 정점 결정
		stack.add(startNode);

		// ================노드 순회 시작=====================
		while (stack.size() != 0) {
			// 스택의 최상단 확인
			int popedNode = stack.pop();
			
			// 미방문 노드일시 방문처리 및 결과에 입력
			if (!visited[popedNode]) {
				visited[popedNode]=true;
				System.out.print(popedNode+" ");
			}

			// 방문 노드의 인접 노드들 검사
			ArrayList<Integer> popedList = adjList.get(popedNode);

			if (popedList != null) {
				Collections.sort(popedList);
				// 방문노드에 인접한 노드들의 리스트를 내림차순 정렬해서 add해야
				// stack에서 오름차순으로 pop할 수 있음
				Collections.reverse(popedList);

				// 인접한 노드 하나 검사
				for (Integer node : popedList) {
					// 방문하지 않은 정점 존재할 시
					if (!visited[node])
						stack.add(node);
				} // 인접한 노드 하나 검사 끝
			} //현재 방문노드의 인접노드 여부검사 끝
		} // 노드 순회 끝 ==================================
		System.out.println();
	} // DFS 끝 ==========================================

	public void BFS(int startNode) {
		// visited초기화 전처리
		Arrays.fill(visited, false);

		Queue<Integer> queue = new LinkedList<>();  //BFS용 큐

		// 시작 정점 결정
		queue.add(startNode);
		visited[startNode] = true;

		// ================노드 순회 시작=====================
		while (queue.size() != 0) {

			// 큐의 맨앞 노드 확인
			int polledNode = queue.poll();
			System.out.print(polledNode+" ");

			// 현재 방문노드의 인접노드가 있는지 검사
			ArrayList<Integer> polledList = adjList.get(polledNode);

			if (polledList != null) {
				// 오름차순으로 노드에 방문해야해서 인접노드 정렬
				Collections.sort(polledList);
				
				// 인접노드 순회
				for (Integer node : polledList) {
					if (!visited[node]) { //방문하지 않은 노드만 enqueue
						queue.add(node);
						visited[node]=true;
					}
				} //인접노드 순회 끝
			} // 방문노드의 인접노드 유무 검사
		} // ================노드 순회 끝 ===================
		System.out.println();
	}//BFS끝
}