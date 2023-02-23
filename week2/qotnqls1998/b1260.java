package week2;

import java.util.Arrays;
import java.util.Scanner;

public class b1260 {
	static boolean visit[] = {false,};
	static Scanner sc = new Scanner(System.in);
	   
	static int n = sc.nextInt();
	static int m = sc.nextInt();
	static int v = sc.nextInt();

	static int[][] graph = new int[m][];
	
	public static void main(String[] args) {	
		for(int i=0;i<m;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			graph[x][y]=1;
		}
		dfs(v);
	}
    public static void dfs(int i) {
		visit[i] = true;
		System.out.print(i + " ");
		
		for(int j=1; j< m+1; j++) {
			if(graph[i][j] == 1 && visit[j] == false) {
				dfs(j);
			}
		}
	}

}



