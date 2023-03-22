import java.io.*;
import java.util.*;

public class Main {
	static int N, day;
	static Pair target;
	static int[] fish;
	static int[][] map;
	static boolean[][] visit;
	static Shark shark;

	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
    
    // 물고기의 크기 별 개수를 가지는 배열
		fish = new int[7];
		day = 0;

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
        
        // 상어를 만난 경우
        // 해당 칸을 0으로 변경 후 상어 좌표를 저장
				if (map[i][j] == 9) {
					map[i][j] = 0;
					shark = new Shark(i, j, 2, 0);
					continue;
				}
        
        // 물고기 배열에 값 추가
				if (map[i][j] > 0)
					fish[map[i][j]]++;
			}
		}
		
		while(true) {
      // 물고기가 먹을 수 있는 크기 i
			int i = shark.size-1;
      
      // 물고기가 자신이 먹을 수 있는 숫자
			int sum = sum(1,i);
      
      // 자신이 먹을 수 있는 물고기가 없는 경우 종료
			if(sum == 0) break;
      
      // 상어가 이동하여 물고기 1개 먹기
      // 자신이 먹을 수 있는 물고기가 있지만 갈 수 없는 경우 종료
			if(bfs()) break;
		}
		System.out.println(day);
	}
	
  // 물고기 개수를 세는 메소드
	static int sum(int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += fish[i];
		}
		return sum;
	}
  
  // 상어 이동 메소드
	static boolean bfs() {
    // 상어가 물고기를 먹는 동안 걸리는 시간
		int tmpDay = 1;
    
    // 목표 물고기 : 초기화를 오른쪽 아래로 두어 어떤 좌표든 후순위로 가게 설정
		target = new Pair(N+1,N+1);
    
		Queue<Pair> q = new LinkedList<Pair>();
		// 상어가 이동을 시작할 때 visit 배열 초기화
    visit = new boolean[N][N];
    
    // 현재 상어 위치 입력
		q.offer(new Pair(shark.x,shark.y));
		visit[shark.x][shark.y] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
      // for문이 1번 돌 때 1일 증가
			for (int i = 0; i < size; i++) {
				Pair now = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N 
							|| map[nx][ny] > shark.size || visit[nx][ny]) continue;
					
          // 타겟 물고기가 되는 경우
          // 1. 물고기가 상어 크기보다 작고, 0이 아닌 경우
					// 2. 위쪽에 있거나 같은 높이인 경우 왼쪽인 경우
          if (map[nx][ny] < shark.size && map[nx][ny] != 0) {
						if(nx < target.x || (nx == target.x && ny < target.y)){
							target = new Pair(nx,ny);
						}
					}
					q.offer(new Pair(nx,ny));
					visit[nx][ny] = true;
				}
			}  // end (size)
			// 타겟의 x가 초기화한 값이 아닌 경우 먹을 수 있는 물고기가 존재
      // 상어 몸집 키우기
      // 해당 물고기에 맞는 숫자 1 감소
      // 상어 위치 변경
      // 날짜 = 물고기 먹는데 걸리는 시간(tmpDay) + 물고기 먹기 시작한 날짜
			if(target.x != N+1) {
				shark.eat();
				fish[map[target.x][target.y]]--;
				map[target.x][target.y] = 0;
				
				shark.x = target.x;
				shark.y = target.y;
				day += tmpDay;
				return false;
			}
      // 먹을 수 있는 물고기가 없는 경우 1증가
			tmpDay++;
		}
		return true;
	}

	static class Pair{
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Shark {
		int x;
		int y;
		int size;
		int exp;

		public Shark(int x, int y, int size, int exp) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.exp = exp;
		}

		private void eat() {
			exp++;
			if (exp == size) {
				if(size == 7) return;
				size++;
				exp = 0;
			}
		}
	}
}
