import java.io.*;
import java.util.*;

public class Main {
	static int leaf;
	static int root;
	static Map<Integer, HashSet<Integer>> arr;
	static int delete;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		parent = new int[N];
		delete = Integer.parseInt(br.readLine());
		leaf = 0;
		root = -1;
		arr = new HashMap<>();
		for (int i = 0; i < N; i++) {
			// token : 부모
			// i : 자식
			int n = Integer.parseInt(st.nextToken());
			if (n == -1) {
				root = i;
				parent[root] = -1;
				continue;
			} else {
				parent[i] = n;
			}
			if (arr.get(n) == null)
				arr.put(n, new HashSet<Integer>());
			arr.get(n).add(i);
		}
		
		if (parent[delete] != -1) {
			arr.get(parent[delete]).remove(delete);
			if(arr.get(parent[delete]).isEmpty()) {
				arr.put(parent[delete], null);
			}
			find(root);
		}
		System.out.println(leaf);

	}

	static void find(int n) {
		if (arr.get(n) == null) {
			leaf++;
			return;
		}
		for (Integer integer : arr.get(n)) {
			find(integer);
		}
	}

}