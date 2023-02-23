import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static ArrayList<ArrayList<Integer>> m;
	static int[] cnt;
	static boolean[] visit;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		m = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			m.add(new ArrayList<>());
		}
		StringBuilder sb = new StringBuilder();
		cnt = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int other = Integer.parseInt(st.nextToken());
			m.get(one).add(other);
		}
		max= 0;
		for(int i = 1; i<=N; i++) {
			visit = new boolean[N+1];
			find(i);
		}
		for (int i = 1; i <= N; i++) {
			if(cnt[i] >= max)
				max = cnt[i];
		}
		for (int i = 1; i <= N; i++) {
			if(max == cnt[i])
				sb.append(i + " ");
		}
		System.out.println(sb.toString());
	}
	static void find(int cur) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(cur);
		visit[cur] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
            ArrayList<Integer> tmp = m.get(x);
			for (int i=0;i<tmp.size();i++) {
				if(!visit[tmp.get(i)]) {
					visit[tmp.get(i)] = true;
					q.add(tmp.get(i));
					cnt[tmp.get(i)]++;
				}
			}
		}
	}
}