import java.io.*;
import java.util.*;

public class Main {
	static boolean[] alpha;
	static char[][] words;
	static int N;
	static int K;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		boolean[] a = new boolean[26];
		words = new char[N][8];
		alpha = new boolean[26];
		
		for (int i = 0; i < N; i++) {
			String str =br.readLine();
			words[i] = str.substring(4,str.length()-4).toCharArray();
		}
		if(K<5) {
			result = 0;
		}else if(K == 26){
			result = N;
		}else {
			alpha[0] = true;
			alpha[2] = true;
			alpha[8] = true;
			alpha[13] = true;
			alpha[19] = true;
			comb(0,0);			
		}
		
		System.out.println(result);
		
		
	}
	static void comb(int R,int idx) {
		if(R == K-5) {
			int tmp = 0;
			boolean isT;
			for (int i = 0; i < N; i++) {
				isT = false;
				for (int j = 0; j < words[i].length; j++) {
					if(!alpha[words[i][j] - 'a']) {
						isT = true;
						break;
					}
				}
				if(!isT) tmp++;
			}
			result = Math.max(tmp, result);
			return;
		}
		
		for (int i = idx; i < alpha.length; i++) {
			if(!alpha[i]) {
				alpha[i] = true;
				comb(R+1, i+1);
				alpha[i] = false;
			}
		}
		
		
	}
}
