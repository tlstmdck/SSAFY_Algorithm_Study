package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 지난 로봇청소기 문제와 비슷하게 톱니바퀴와 그 동작 메커니즘을 객체로 구현하여 문제를 해결하였다
 * 
 * 특히, 기어의 연쇄적인 움직임의 경우 재귀함수의 함수 실행 예약 특성을 이용하여 먼저 자신의 양쪽 기어가 움직일지의 여부를 확인 한 후
 * 자신을 회전시키므로써 기어 회전후 왼쪽, 오른쪽 극성의 값이 바뀌어 양쪽과 극성 비교가 불가능한 상황을 해결하였다
 */

public class Main {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	Gear[] gears = new Gear[4];
    	
    	/*
    	 * 4개의 기어를 초기화하고, 각 기어의 왼쪽, 오른쪽 기어를 연결해준다.
    	 */
    	for(int i=0; i<4; i++) {
    		gears[i] = new Gear(br.readLine());	
    	}
    	
    	for(int i=0; i<3; i++) {
    		gears[i].rightGear = gears[i+1];
    	}
    	
    	for(int i=1; i<4; i++) {
    		gears[i].leftGear = gears[i-1];
    	}
    	
    	/*
    	 * 명령어에 따라 특정 기어를 회전시켜준다
    	 */
    	int N = Integer.parseInt(br.readLine());
    	
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int idx = Integer.parseInt(st.nextToken()) - 1;
    		
    		gears[idx].checkRotate(Integer.parseInt(st.nextToken()) == 1 ? true : false);
    	}
    	
    	/*
    	 * 각 기어의 12시 방향 위치를 확인하여 결과값에 더한다
    	 */
    	int sum = 0;
    	int mul = 1;
    	for(int i=0; i<4; i++) {
    		sum += gears[i].getHead() * mul;
    		mul *= 2;
    	}
    	System.out.println(sum);
    	
    }
}

class Gear {
	int[] magnetics;	// 톱니의 자성
	int head;			// 12시 방향 인덱스
	int left;			// 3시 방향 인덱스
	int right;			// 9시 방향 인덱스
	Gear leftGear;		// 왼쪽 기어
	Gear rightGear;		// 오른쪽 기어
	boolean moved = false;	// 기어가 움직였는지 체크
	
	public Gear(String str) {
		magnetics = new int[8];
		head = 0;
		left = 6;
		right = 2;
		leftGear = null;
		rightGear = null;
		
		for(int i=0; i<8; i++) {
			magnetics[i] = str.charAt(i) - '0';
		}
	}
	
	/*
	 * 기어를 움직이기 전, 왼쪽과 오른쪽 기어가 움직이는지 여부를 확인하는 메소드
	 * 재귀의 특성 - 함수 실행 예약을 이용해 선 탐색 후 동작을 시행
	 * isClock에 따라 시계/반시계임을 결정한다
	 */
	
	public void checkRotate(boolean isClock) {
		// 이 기어는 움직일것을 양쪽 기어에 미리 알림
		this.moved = true;
		
		// 왼쪽 기어가 움직이지 않았고 극성이 다르면 왼쪽기어에 대해 재귀 실행
		if (leftGear != null && !leftGear.moved && getLeft() != leftGear.getRight()) {
			leftGear.checkRotate(!isClock);
		}
		
		// 오른쪽 기어가 움직이지 않았고 극성이 다르면 오른쪽기어에 대해 재귀 실행
		if (rightGear != null && !rightGear.moved && getRight() != rightGear.getLeft()) {
			rightGear.checkRotate(!isClock);
		}
		
		// 양 방향에 대해 움직일지 여부를 미리 확인 한 후, 현재 기어를 회전시킴
		rotate(isClock);
	}
	
	/*
	 * 회전에 따라 head, left, right의 인덱스를 조정한다
	 * 
	 * 기어가 시계방향으로 움직이면, 인덱스들은 왼쪽으로 1씩 이동한다
	 * 반대로 기어가 반시계방향으로 움직이면, 인덱스들은 오른쪽으로 1씩 이동한다
	 */
	private void rotate(boolean isClock) {
		if (isClock) {
			head = rotateIndex(head-1);
			left = rotateIndex(left-1);
			right = rotateIndex(right-1);

		} else {
			head = rotateIndex(head+1);
			left = rotateIndex(left+1);
			right = rotateIndex(right+1);
		}
		// 기어가 실제로 움직이고 난 후, 다음 동작을 위해 기어의 움직임 여부 초기화
		this.moved = false;
	}
	
	/*
	 * head, left, right는 magnetics 배열에 대한 인덱스를 뜻하므로
	 * get 메소드를 통해 실제 극성 값을 가져온다
	 */
	public int getHead() {
		return magnetics[head];
	}
	
	public int getLeft() {
		return magnetics[left];
	}
	
	public int getRight() {
		return magnetics[right];
	}
	
	/*
	 * 0~7 에 대한 순환 인덱스 구현
	 */
	private int rotateIndex(int n) {
		if (n < 0) {
			return 8+n;
		}
		
		return n%8;
	}
}