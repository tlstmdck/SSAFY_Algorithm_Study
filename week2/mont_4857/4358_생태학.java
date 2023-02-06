import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String,Integer> map = new TreeMap<>();
		
		int size = 0;
		String line = br.readLine();
		while(true) {
			map.put(line,map.getOrDefault(line,0)+1);
			if((line = br.readLine()) == null || line.length() ==0) {
				break;
			}
			size++;
		}
		size+=1;
		for (String string : map.keySet()) {
			System.out.printf("%s %.4f\n",string,(map.get(string)*100.0)/size);
		}
		br.close();
	}
}