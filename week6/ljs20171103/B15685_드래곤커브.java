package gold4;

import java.io.*;
import java.util.*;

/* 0: 오른쪽
 * 1: 위로
 * 2: 왼쪽
 * 3: 아래로
 * ===========
 * 0을 90도 뒤집으면 1
 * 1을 90도 뒤집으면 2
 * 2를 90도 뒤집으면 3
 * 3을 90도 뒤집으면 0
 * => """0뒤집을 때 가장 가까운 선부터 뒤집으며 순환"""
 */
/*
 * ================알고리즘===============
 * stpes = {오른쪽, 위, 왼쪽, 아래}; // 0 1 2 3
 * N 입력
 * N번동안 커브입력:
 * 	stringtokenizer st  = 입력 
 * 	new Curve (토큰, 토큰, 토큰, 토큰)
 * 커브입력 끝 그다음 모든 커브에 대하여 커브만들기
 * foreach curve in curves:
 * 	new deque = curve
 * 	deque = makeCurve(curve.g, 0, dq);
 * 	
 * 	만들어진 deque에 대하여 뒤에서부터 poll하며 드래곤커브 정보를 map에 저장
 * 	정보 전부 저장 후 네 꼭지점이 1인 개수 세기
 * =========================================
 * makeCurve(finalGen, nowGen, dq): 재귀 
 * 	if depth == finalGen+1:
 * 		return dq
 * 
 * 	new deque2
 * 	for g 1 <= 2^(nowGen-1) g++: //1세대면 0세대 pop+0세대 기반으로 만들어진 방향 push =>1번 함
 *		// 2세대면 1세대 pop, 2세대 push를 두번 거침 
 *		int d = dq.pollFirst
 * 		dq2.addFirst((d+1)%4)
 * 		dq2.addLast(d);
 * 	return dq = makeCurve(finalGen, nowGen+1, dq2)
 * */
public class B15685_드래곤커브 {
	static boolean[][] map = new boolean[101][101];
	static int[][] steps = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } }; // 0 1 2 3: 오 위 왼 아

	static class Curve {
		int j, i, d, g;

		public Curve(int j, int i, int d, int g) {
			super();
			this.j = j;
			this.i = i;
			this.d = d;
			this.g = g;
		}

		@Override
		public String toString() {
			return "Curve [j=" + j + ", i=" + i + ", d=" + d + ", g=" + g + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Curve curves[] = new Curve[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			curves[i] = new Curve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (Curve curve : curves) { // 각 커브정보에 대한 드래곤커브 방향 만들것임
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			dq.offer(curve.d);
			dq = makeCurve(curve.g, 1, dq); // 드래곤커브 방향 만듬
			
			// 방향대로 맵에다 커브 만들기
			int i = curve.i;
			int j = curve.j;
			map[i][j]=true;
			
			while(!dq.isEmpty()) {
				int d = dq.pollLast();
//				System.out.println("i: "+i+", j: "+j);
				int ni = i + steps[d][0];
				int nj = j + steps[d][1];
				map[ni][nj] = true;
//				System.out.println("ni: "+ni+", nj: "+nj);
				i = ni; j = nj;
			}
		}
//		for (int[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}
		
		// 네 꼭지점이 1인 개수
		int res = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] && map[i][j+1] &&
				   map[i+1][j] && map[i+1][j+1])
					res++;
			}
		}
		System.out.println(res);
	}

	static ArrayDeque<Integer> makeCurve(int finalGen, int nowGen, ArrayDeque<Integer> dq) {
		if (nowGen == finalGen + 1) { //그리기 시작
			return dq;
		}

//		System.out.println("=========nowGen: " + nowGen+"==========");
		ArrayDeque<Integer> dq2 = new ArrayDeque<>();
		for (int g = 1; g <= 1 << (nowGen - 1); g++) {// 1세대면 0세대 pop+0세대 기반으로 만들어진 방향 push =>1번 함
			// 2세대면 1세대 pop, 2세대 push를 두번 거침
			int d = dq.pollFirst();
//			System.out.println("polled dir: " + d);
			dq2.addFirst((d+1)%4);
			dq2.addLast(d);
//			System.out.println("dq2: " + dq2);
		}
		return dq = makeCurve(finalGen, nowGen + 1, dq2);
	}
}
