import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] road = new long[N];
		long[] oil = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < road.length; i++) {
			road[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < oil.length; i++) {
			oil[i] = Integer.parseInt(st.nextToken());
		}
		long sum = 0;
		long sumRoad = 0;
		int from = 0;
		int to = 1;
		
		while(true) {
			if(to >= N) {
				sum += sumRoad * oil[from];
				break;
			}

			sumRoad += road[to];
			if(oil[from]<oil[to]) {
				to += 1;
			}else {
				sum+= sumRoad*oil[from];
				sumRoad = 0;
				from = to++;
			}
		}
		System.out.println(sum);
	}

}