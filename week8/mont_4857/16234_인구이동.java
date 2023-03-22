import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int N,L,R;
	static int[][] map;
	static int[][] visit;
	static Map<Integer, Integer> m,mCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
    
    // 같은 연합의 합을 저장하는 map
		m = new HashMap<>();
    // 같은 연합의 수를 저장하는 map
		mCnt = new HashMap<>();
		int result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    
    // 연합 개수를 담는 변수
		int cnt;
		while(true) {
			cnt =0;
			visit = new int[N][N];
      
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
			    // 방문하지 않은 나라인 경우
          if(visit[i][j] == 0) {
            // 연합 합과 수를 담는 map을 0을 넣어 초기화
						m.put(++cnt, 0);
						mCnt.put(cnt, 0);
            // 인접한 나라 dfs로 탐색            
						dfs(cnt,i,j);
					}
				}
			}
      
      // 연합의 개수가 N*N의 땅크기와 같은 경우 while문 탈출
			if(cnt == N*N) break;
			
      // 인구 이동 1회
			result++;
      
      // 인구 이동
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 해당 땅에 연합의 인구수 / 연합 내의 나라 수
          map[i][j] = m.get(visit[i][j])/mCnt.get(visit[i][j]);
				}
			}
      
      // 연합이 1개인 경우에는 더 이상 인구 이동이 없으므로 while문 탈출
			if(cnt == 1) break;
		}
		System.out.println(result);
	}
  
	static void dfs(int idx, int x, int y) {
    // 해당 나라 방문을 연합의 숫자로 지정
		visit[x][y] = idx;
    
    // 해당 연합의 인구 수와 나라 수를 증가
		m.put(idx, m.get(idx)+map[x][y]);
		mCnt.put(idx, mCnt.get(idx)+1);
    
    // 4방향 탐색 
    for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny <0 || nx >= N || ny >= N 
					|| visit[nx][ny] != 0 || Math.abs(map[x][y]-map[nx][ny])<L
					|| Math.abs(map[x][y]-map[nx][ny])>R ) continue;
			dfs(idx,nx,ny);
		}
	}
}
