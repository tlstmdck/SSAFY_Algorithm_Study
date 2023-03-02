package codingTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
/*
 * 구현 문제로 확산 - 이동 과정을 T 만큼 반복
 * 먼지 확산 시 모든 먼지에 대해 동시에 확산해야 하므로, 확산하는 먼지와 기존 먼지를 따로 생각해야함
 * -> 두개의 2차원 배열로 해결
 * 
 * 먼지의 이동은 배열돌리기와 같은 방식
 */


public class Solution {
	
	static int R, C, T;
	static int[] airFilter = new int[2];	// 공기청정기의 row값 저장
	static int[][] room, spread;	// room: 실제 먼지값, spread: 먼지 확산시 확산량을 임시로 저장하는 배열
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        room = new int[R][C];
        
        for(int i=0; i<R; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<C; j++) {
        		room[i][j] = Integer.parseInt(st.nextToken());
        		
        		if (room[i][j] == -1) { // 마지막 -1의 위치가 공기청정기의 아래위치
        			airFilter[1] = i;
        			airFilter[0] = i-1;
        		}
        	}
        } // end input
        
        for(int i=0; i<T; i++) {
        	spreadDust();
        	runAirFilter();
        }
        System.out.println(getRemainDust());
        
    }
    
    // 배열 돌리기 함수
    static void runAirFilter() {
    	
    	int dir = 3; // 공기청정기의 위쪽으로 나가는 방향 (북쪽)
    	
    	int nowY = airFilter[0] - 1; // 시작위치를 공기청소기 바로 위로 시작
    	int nowX = 0;
    	
    	while(true) {
    		int nextY = nowY + dr[dir];
    		int nextX = nowX + dc[dir];
    		
    		// 다음 위치가 공기청소기의 흐름 공간 이외를 가리키면 방향을 시계방향으로 전환
    		if (!(0 <= nextY && nextY <= airFilter[0] &&
    			  0 <= nextX && nextX < C)) {
    			dir = (dir + 1) % 4;
    			continue;
    		}
    		
    		// 다음 위치가 -1이면 한바퀴를 돌았다는 뜻 이므로 반복문 종료
    		if (room[nextY][nextX] == -1)  {
    			room[nowY][nowX] = 0;
    			break;
    		}
    		
    		// 다음 위치의 값을 현재 위치로 땡겨오기
    		room[nowY][nowX] = room[nextY][nextX];
    		nowY = nextY;
    		nowX = nextX;
    	}
    	
    	dir = 1; // 공기청소기의 아래쪽으로 나가는 방향 (남쪽)
    	nowY = airFilter[1] + 1; // 시작위치를 공기청소기 바로 아래로 시작
    	nowX = 0;
    	
    	while(true) {
    		int nextY = nowY + dr[dir];
    		int nextX = nowX + dc[dir];
    		
    		// 다음 위치가 공기청소기의 흐름 공간 이외를 가리키면 방향을 반시계방향으로 전환
    		if (!(airFilter[1] <= nextY && nextY < R &&
      			  0 <= nextX && nextX < C)) {
    			dir = dir - 1;
    			if (dir < 0) dir = 3;
    			continue;
    		}
    		
    		if (room[nextY][nextX] == -1) {
    			room[nowY][nowX] = 0;
    			break;
    		}
    		
    		room[nowY][nowX] = room[nextY][nextX];
    		nowY = nextY;
    		nowX = nextX;
    	}
    }
    
    // 먼지 확산 시뮬레이션
    static void spreadDust() {
    	spread = new int[R][C]; // 임시로 먼지 확산값을 저장할 배열 초기화
    	
    	/*
    	 * 모든 배열을 돌면서 규칙에 따라 확산하는 먼지 값을 spread 배열에 저장
    	 */
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			if (room[i][j] > 0) {
    				int spreadDust = room[i][j]/5;
    				
    				for(int k=0; k<4; k++) { // 동서남북으로 퍼질 수 있는지 확인
    					int nr = i+dr[k];
    					int nc = j+dc[k];
    					
    					if (isValid(nr, nc) && room[nr][nc] != -1) {
    						spread[nr][nc] += spreadDust; // spread 배열에 저장
    						room[i][j] -= spreadDust;	// room 배열의 원본 먼지값 감소
    					}
    				}
    			}
    		}
    	}
    	
    	// 모든 먼지를 확산시킨 후, 확산값을 실제 먼지 위치에 적용
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			room[i][j] += spread[i][j];
    		}
    	}
    }
    
    // 모든 먼지값을 가져오는 함수
    static int getRemainDust() {
    	int rst = 2;
    	
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			rst += room[i][j];
    		}
    	}
    	
    	return rst;
    }
    
    static void print() {
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			System.out.print(room[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
    
    static boolean isValid(int r, int c) {
    	return 0 <= r && r < R && 0 <= c && c < C;
    }    
}