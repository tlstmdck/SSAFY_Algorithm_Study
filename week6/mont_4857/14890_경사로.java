import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Main {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	static int N, L, runway;
	static int[][] map;
	static ArrayList<ArrayList<Floor>> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		runway = 0;
		
    // 가로 방향
    // 같은 층에 있는 길이를 하나의 객체로 담아 list에 넣기
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int tmp = 0;
			list.add(new ArrayList<>());
			for (int j = 1; j < N; j++) {
				if(map[i][tmp] != map[i][j]) {
					list.get(i).add(new Floor(tmp, j, map[i][tmp]));
					tmp = j;
				}
			}
			list.get(i).add(new Floor(tmp, N, map[i][tmp]));
		}
    
    // 숫자 세기
		count();
		list.clear();
		
    // 세로 방향
    for (int i = 0; i < N; i++) {
			int tmp = 0;
			list.add(new ArrayList<>());
			for (int j = 1; j < N; j++) {
				if(map[tmp][i] != map[j][i]) {
					list.get(i).add(new Floor(tmp, j, map[tmp][i]));
					tmp = j;
				}
			}
			list.get(i).add(new Floor(tmp, N, map[tmp][i]));
		}
		
		count();
		
		System.out.println(runway);
	}
	
  // 
	static void count() {
		for (int s = 0; s < N; s++) {
      // 가능한지를 나타내는 변수
			boolean isT = false;
			for (int i = 0; i < list.get(s).size()-1; i++) {
				Floor o1 = list.get(s).get(i);
				Floor o2 = list.get(s).get(i+1);
				
        // 두 객체(층)의 크기가 2이상 차이나는 경우 불가능
				if(Math.abs(o1.value- o2.value)>1) {
					isT = true;
					return;
				}
				
        // 아래 층의 길이가 경사로의 길이보다 긴 경우 불가능
				Floor target = o1.value > o2.value ? o2 : o1;
				if (target.length < L) {
					isT = true;
					return;
				}
        
        // 아래 층의 길이에서 L만큼 다음 비교 때 사용불가
				target.length -= L;
			}
      
			if(!isT) {
				runway++;
			}
		}
		
	}
	
	static class Floor{
		int length;
		int value;
		public Floor(int start, int end, int value) {
			super();
			this.length = end- start;
			this.value = value;
		}
	}
}
