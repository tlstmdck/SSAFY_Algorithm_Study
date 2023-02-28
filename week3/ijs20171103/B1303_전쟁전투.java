package silver1;

import java.util.*;
import java.io.*;

public class B1303_전쟁전투 {
	static int N; //전쟁터 열 크기
	static int M; //전쟁터 행 크기
	static char[][] map; //전쟁터
	static boolean[][] visited; // 방문결과
	static int[] di = {0, 1, 0, -1}; 
	static int[] dj = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[M][N];
		visited = new boolean[M][N];
		
		for (int i = 0; i < M; i++) { //맵 입력 시작
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		} //맵 입력 끝
		
		int WCnt=0, Wres=0; //W병사 수와 위력
		int BCnt=0, Bres=0; //B병사 수와 위력
		for (int i = 0; i < M; i++) { //탐색 시작
			for (int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j]=='W') { //W만나면 탐색시작
					WCnt = BFS(i, j, 'W');
					System.out.println("W: "+WCnt);
					Wres += WCnt*WCnt; //W병사 총력에 현재 위력을 더함
				}
				else if(!visited[i][j] && map[i][j]=='B') { //B만나면 탐색시작
					BCnt = BFS(i, j, 'B');
					System.out.println("B: "+BCnt);
					Bres += BCnt*BCnt; 
				}
			}
		}  // 탐색 끝
		System.out.println(Wres+" "+Bres);
	}

	static int BFS(int i, int j, char c) {
		Queue<Pair> queue = new LinkedList<>(); //BFS용 큐
		int cnt=1; //병사 수
		char whoIs = c; //어떤 병사느냐에 따라 탐색 경로가 달라짐
		
		queue.add(new Pair(i, j)); //초기좌표 push
		visited[i][j] = true; //스택에 넣을 때 방문
		
		while(!queue.isEmpty()) { //BFS시작
			Pair pair = queue.poll(); //좌표 꺼내기
			i = pair.getI();
			j = pair.getJ(); //i와 j는 현재 위치
			
			for (int n = 0; n < 4; n++) { //현재좌표에서 사방탐색
				int ni = i + di[n]; 
				int nj = j + dj[n];
				
				if(ni<0 || ni>=M || nj<0 || nj>=N) continue;
				if(map[ni][nj]!=whoIs || visited[ni][nj]) continue; //같은 병사 아니면 안됨
				
				// if문에 걸리지 않고 다음 스텝으로 갈 수 있을 때 
				queue.add(new Pair(ni, nj));
				visited[ni][nj] = true; //방문처리
				cnt++; //병사 만났으므로 +1
			}
		} //BFS끝
		return cnt;
	}
}
class Pair {
	int i;
	int j;
	public Pair(int i, int j) {
		this.i = i;
		this.j = j;
	}
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
}
