import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean arr[][] = new boolean[N+1][M+1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		int max = -1;
		Queue<Integer> qX = new LinkedList<>();
		Queue<Integer> qY = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(arr[i][j]) {
					qX.offer(i);
					qY.offer(j);
					arr[i][j] = false;
					int tmp =0;
					while(!qX.isEmpty()) {
						int size = qX.size();
						int x = qX.poll();
						int y = qY.poll();
						tmp++;
						for (int k = 0; k < size; k++) {
							if(x+1<=N && arr[x+1][y]) {
								arr[x+1][y] = false;
								qX.offer(x+1);
								qY.offer(y);
							}
							if(x-1>=1 && arr[x-1][y]) {
								arr[x-1][y] = false;
								qX.offer(x-1);
								qY.offer(y);
							}
							if(y+1<=M && arr[x][y+1]) {
								arr[x][y+1] = false;
								qX.offer(x);
								qY.offer(y+1);
							}
							if(y-1>=1 && arr[x][y-1]) {
								arr[x][y-1] = false;
								qX.offer(x);
								qY.offer(y-1);
							}
						}
					}
					
					max = max>tmp ? max : tmp;
				}
			}
		}
		
		System.out.println(max);
		
	}

}