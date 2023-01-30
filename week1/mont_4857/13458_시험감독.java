import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(br.readLine());
		long[] student = new long[N];
		long BCnt = 0;
		long CCnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			student[i] =Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		long B = Long.parseLong(st.nextToken());  // 총 시험
		long C =Long.parseLong(st.nextToken());  // 부 시험
		for (int i = 0; i < N; i++) {
			BCnt += 1;
			if(student[i]-B <=0) continue;
			CCnt += (student[i]-B)/C;
			if((student[i]-B)%C != 0) CCnt += 1; 
		}
		System.out.println(BCnt + CCnt);
	}
}