package gold4;

import java.io.*;
import java.util.*;

/*
주사위 모양

    뒤
왼 위 오른
    앞
   아래

만약 주사위가
0 1 2 3 4 5 6이면

동쪽으로 굴리기
  2            2
4 1 3   =>   6 4 1
  5            5
  6            3
top -> right, right->bottom, bottom->left, left->top

서쪽으로 굴리기
  2            2
4 1 3   =>   1 3 6 
  5            5
  6            4
top->left, left->bottom, bottom->right, right->top
  
북쪽으로 굴리기
  2             1
4 1 3   =>    4 5 3
  5             6 
  6             2
top->back, back->bottom, bottom->front, front->top

남쪽으로 굴리기
  2             6
4 1 3   =>    4 2 3
  5             1 
  6             5
top->front, front->bottom, bottom->back, back->top

 * */
public class B1449_주사위굴리기 {
	static int[][] map;
	static int[] dice;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());        // 행렬 행
		int M = Integer.parseInt(st.nextToken()); 		 // 행렬 열
		int nowi = Integer.parseInt(st.nextToken());	 // 주사위 행
		int nowj = Integer.parseInt(st.nextToken());	 // 주사위 열
		int K = Integer.parseInt(st.nextToken());		 // 명령 개수
		map = new int[N][M];	// 맵
		int[] commands = new int[K];	// 명령들
		/** 주사위를 인덱스로 표현
		 * [ 0 1 2 3 4 5 6 ]. 0은 쓰지 않을 것
		 * 0 top back right left front below
		 * */
		dice = new int[7];		
		// 변수 선언 끝
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int[] row : map) {
			System.out.println(Arrays.toString(row));
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			commands[i] = Integer.parseInt(st.nextToken());
		} 
		// end input
		
		// 주사위 이동 시작
		for (int c : commands) {
			// ******* 동 ********
			if(c==1) {		  
				int nj = nowj + 1;  // 동쪽으로
				if(nj>=M) continue; // 범위검사. 안되면 continue
				nowj = nj;
				
				System.out.print("동쪽이동, ");
				System.out.println("지금위치: "+nowi+", "+nowj);
				
				// top->right, right->bottom, bottom->left, left->top
				int top = dice[1];  // top
				dice[1] = dice[4];  // top에 left
				dice[4] = dice[6];  // left에 bottom
				dice[6] = dice[3];  // bottom에 right
				dice[3] = top;      // right에 top
				
				moveAndCopy(nowi, nowj); 
			}
			// ******* 서 ********
			else if(c==2) {
				int nj = nowj - 1;  // 서쪽으로
				if(nj<0) continue; // 범위검사. 안되면 continue
				nowj = nj;
				
				System.out.print("서쪽이동, ");
				System.out.println("지금위치: "+nowi+", "+nowj);
				
				// top->left, left->bottom, bottom->right, right->top
				int top = dice[1];  // top
				dice[1] = dice[3];  // top에 right
				dice[3] = dice[6];  // right에 bottom
				dice[6] = dice[4];  // bottom에 left
				dice[4] = top;      // left에 top
				
				moveAndCopy(nowi, nowj); 
			}
			// ******* 북 ********
			else if(c==3) {
				int ni = nowi-1;   // 북으로 이동
				if(ni<0) continue; // 범위검사
				nowi = ni;
				
				System.out.print("북쪽이동, ");
				System.out.println("지금위치: "+nowi+", "+nowj);

				// top->back, back->bottom, bottom->front, front->top
				int top = dice[1];  // top
				dice[1] = dice[5];  // top에 front
				dice[5] = dice[6];  // front에 bottom
				dice[6] = dice[2];  // bottom에 back
				dice[2] = top;      // back에 top
				
				moveAndCopy(nowi, nowj); 
			}
			// ******* 남 ********
			else if(c==4) {
				int ni = nowi+1;    // 남으로 이동
				if(ni>=N) continue; // 범위검사
				nowi = ni;
				
				System.out.println("남쪽이동");
				System.out.println("지금위치: "+nowi+", "+nowj);

				// top->front, front->bottom, bottom->back, back->top
				int top = dice[1];  // top
				dice[1] = dice[2];  // top에 back
				dice[2] = dice[6];  // back에 bottom
				dice[6] = dice[5];  // bottom에 front
				dice[5] = top;      // front에 top
				
				// 주사위, 현재 주사위 위치(i,j), 그리고 바닥면
				moveAndCopy(nowi, nowj); 
			}
			System.out.println(dice[1]); //주사위 이동 후 윗면 출력
		} // 주사위 이동 끝
	}
	
	// 바닥면을 복사, 또는 바닥면에 복사한 후 이동된 주사위를 최종적으로 현재 주사위 상태로 결정함
	static void moveAndCopy(int nowi, int nowj) {
		// 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
		if(map[nowi][nowj]==0) {
			map[nowi][nowj] = dice[6];
		}
		else { // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
			dice[6] = map[nowi][nowj];
			map[nowi][nowj] = 0;
		}
	}
}
