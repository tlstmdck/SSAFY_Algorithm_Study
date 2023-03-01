import java.io.*;
import java.util.*;

public class Main {
	static List<Integer> list;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
    
		for (int i = 0; i < N; i++) {
			list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
      // 선분을 그리는 방향을 list에 추가
			list.add(dir);
			
      /*
       * list의 마지막부터 방향을 90도 돌리면서 리스트에 추가
       * → 방향일 때 회전한 선분은 ↑
       * ↑ 방향일 때 회전한 선분은 ←
       * ← 방향일 때 회전한 선분은 ↓
       * ↓ 방향일 때 회전한 선분은 →
      */
			for (int j = 0; j < g; j++) {
        // list에 add하기 때문에 시작 할 때의 크기를 담아 놓고 써야함
				int size = list.size();
				for (int k = size -1; k >= 0; k--) {
					list.add((list.get(k)+1)%4);
				}
			}
      
      /*
       * 처음 제공되는 점을 표시
       * list에 있는 선분의 방향대로 움직이면서 움직이는 점에 표시
      */
			map[y][x] = true;
			for (int d : list) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				map[ny][nx] = true;
				
				x = nx;
				y = ny;
			}
		}
    
    // 사각형의 꼭짓점이 모두 드래곤 커브 위에 있는 지 확인
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(!map[i][j] || !map[i+1][j] || !map[i][j+1] || !map[i+1][j+1]) continue; 
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
