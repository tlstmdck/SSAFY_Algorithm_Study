import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[][] arr= new long[N+1][M+1];
		long result = -1;
		
		for (int i = 1; i <= N; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= M; j++) {
				arr[i][j] = arr[i-1][j] + arr[i][j-1] + (long)(tmp.charAt(j-1)-'0') - arr[i-1][j-1];
			}
		}
		long sum1 = 0;
		long sum2 = 0;
		long sum3 = 0;
		long sum4 = 0;
		
		long sumA = 0;
		long sumB = 0;
		long sumC = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum1 = arr[i][j];
				sum2 = arr[i][M] - arr[i][j];
				sum3 = arr[N][j] - arr[i][j];
				sum4 = arr[N][M] - sum1 - sum2 - sum3;
			
				// 1+2 / 3 / 4
				result = Math.max(result, (sum1+sum2) * sum3 * sum4);
				// 1+3 / 2 / 4
				result = Math.max(result, (sum1+sum3) * sum2 * sum4);
				// 1 / 2 / 3+4
				result = Math.max(result, (sum3+sum4) * sum1 * sum2);
				// 1/ 2+4 / 3
				result = Math.max(result, (sum2+sum4) * sum1 * sum3);
			}
		}
		for (int i = 1; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				sumA = arr[i][M];
				sumB = arr[j][M] - sumA;
				sumC = arr[N][M] - sumB - sumA;
				result = Math.max(result, sumA * sumB * sumC);
			}
		}
		
		for (int i = 1; i < M-1; i++) {
			for (int j = i+1; j < M; j++) {
				sumA = arr[N][i];
				sumB = arr[N][j] - sumA;
				sumC = arr[N][M] - sumB - sumA;
				result = Math.max(result, sumA * sumB * sumC);
			}
		}
		
		System.out.println(result);
			
	}
}
