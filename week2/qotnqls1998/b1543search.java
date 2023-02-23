package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1543search {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String file = bf.readLine();
		String word = bf.readLine();
		int cnt=0;
		
		int i=0;
		while(i<file.length()) {
			int check =0;
			if( (file.length()-(i)) <word.length()) break;
			
			for(int j=0;j<word.length();j++) {
				//System.out.println( "#"+(j+1)+file.charAt(i+j) +" : "+word.charAt(j) );
				if (file.charAt(i+j) == word.charAt(j)) {
					check++;
				}
			}
			//System.out.println("chk: "+ check);
			if(check==word.length()) {
				cnt++; //찾은 단어수 
				i+=word.length();
			}
			else {
				i++;
			}
			//System.out.println(file.length()-(i) + ","+ word.length());
			
		}
		//System.out.println();
		System.out.println(cnt);
	}
}
