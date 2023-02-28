import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static Map<Integer, ArrayList<Integer>> arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		arr = new HashMap<>();

		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int other = Integer.parseInt(st.nextToken());

			if (arr.get(one) != null) {
				arr.get(one).add(other);
			} else {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(other);
				arr.put(one, tmp);
			}

			if (arr.get(other) != null) {
				arr.get(other).add(one);
			} else {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(one);
				arr.put(other, tmp);
			}
		}
		parent[1] =-1;
		BFS(1, 0);
		for (int i = 2;i<=N ; i++) {
			System.out.println(parent[i]);
		}
	}
	static void BFS(int child, int p) {
		ArrayList<Integer> t;
		if((t=arr.get(child)) != null) {
			for (int i = 0; i < t.size(); i++) {
				if(parent[t.get(i)] == 0) {
					parent[t.get(i)] = child;
					BFS(t.get(i),child);
				}
			}
		}
		
	}
}