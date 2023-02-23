package work;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class _톱니바퀴2 {
	static int num, dir;
	static boolean visit[];
	static ArrayList<LinkedList<Integer>> list = new ArrayList<>(4);

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		// ArrayList<LinkedList<Integer>> list = new ArrayList<>(4);
		// 입력 받는 부분
		
		for (int i = 0; i < 4; i++) {
			LinkedList<Integer> l = new LinkedList<>();
			String str = sc.next();
			for (int j = 0; j < str.length(); j++) {
				l.add(str.charAt(j) - '0');
			}
			list.add(l);
		}

		int k = sc.nextInt();
		for (int i = 0; i < k; i++) {
			visit = new boolean[4]; // 바퀴가 4개이니깐. 
			num = sc.nextInt(); // 돌리려는 바퀴숫자
			dir = sc.nextInt(); // 시계방향인지 반시계인지 구분
			rotate(num - 1, dir); 
		}

		// 조건에 맞춰 바퀴수에 따른 합을 구함.
		for (int i = 0; i < 4; i++) {
			if (list.get(i).get(0) == 1) {
				sum += Math.pow(2, i);
			}

		}

		System.out.println(sum);
	}

	
	public static void rotate(int num, int dir) {
		visit[num] = true;
		// num이 0(제일 왼쪽)보다 커야하며, 넘어갈 왼쪽의 바퀴를 방문하지 않았어야 하고, 서로 가르키는 부분의 극이 달라야함.
		if (num > 0 && !visit[num - 1] && list.get(num).get(6) != list.get(num - 1).get(2)) {
			rotate(num - 1, dir * -1); // 그 다음 왼쪽부분을 확인하기 위해서.
		}
		// 오른쪽 .. 이하 동문..
		if (num < 3 && !visit[num + 1] && list.get(num).get(2) != list.get(num + 1).get(6)) {
			rotate(num + 1, dir * -1);
		}
		// 계속 달고다니는 돌리는 방향이 1 이면 시계방향으로 돌리고
		if (dir == 1) {
			//list.get(num).add(list.get(num).get(0));
			//list.get(num).remove(list.get(num).get(0));
			list.get(num).addFirst(list.get(num).removeLast());

		}
		// 반시계방향으로 돌린다.
		if (dir == -1) {
			//list.get(num).addFirst(list.get(num).get(list.get(num).size() - 1));
			//list.get(num).remove(list.get(num).get(list.get(num).size() - 1));
			list.get(num).addLast(list.get(num).removeFirst());
		}
	}

}
