import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] word = br.readLine().toCharArray();
		char[] searchWord = br.readLine().toCharArray();
		int cnt =0;
		boolean isT = true;
		
		for (int i = 0; i < word.length - searchWord.length +1; i++) {
			isT = true;
			for (int j = 0; j < searchWord.length; j++) {
				if(word[i+j] != searchWord[j]) {
					isT = false;
					break;
				}
			}
			if(isT) {
				cnt++;
				i += searchWord.length - 1;
			}
		}
		
		System.out.println(cnt);
		
		
	}
}