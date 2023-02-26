import java.io.*;

import java.util.*;
public class Main {
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		
		Dice dice = new Dice(0);
		while(st.hasMoreTokens()) {
			boolean isT = false;
			int dir = Integer.parseInt(st.nextToken());
			switch(dir) {
			case 1:
				if(y+1 != map[x].length) {y++;}
				else {isT = true;}
				break;
			case 2:
				if(y>0)y--;
				else {isT = true;}
				break;
			case 3:
				if(x>0)x--;
				else {isT = true;}
				break;
			case 4:
				if(x+1 != map.length) x++;
				else {isT = true;}
				break;
			}
			int result = 0;
			if(!isT) {
				result = dice.move(dir);
				if(map[x][y] ==0) {
					map[x][y] = dice.getValue();
				}else {
					dice.setValue(map[x][y]);
					map[x][y] = 0;
				}
				bw.write(result+"\n");
			}
			
		}
		
		
		
		bw.flush();
		br.close();
	}
	static class Dice{
		int value;
		int north;
		int south;
		int east;
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		int west;
		int opp;
		


		public Dice(int value) {
			this.value = value;
			this.north = 0;
			this.south = 0;
			this.east = 0;
			this.west = 0;
			this.opp = 0;
		}

		public int move(int direction) {
			int tmp =0;
			switch (direction) {
			case 1:
				tmp = value;
				value = east;
				east = opp;
				opp = west;
				west = tmp;
				break;
			case 2:
				tmp = value;
				value = west;
				west = opp;
				opp = east;
				east = tmp;
				break;
			case 3:
				tmp = value;
				value = north;
				north = opp;
				opp = south;
				south = tmp;
				break;
			case 4:
				tmp = value;
				value = south;
				south = opp;
				opp= north;
				north = tmp;
				break;
			}
			
			return opp;
		}

	}
}

