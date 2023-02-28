package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 아이디어 : 벨트를 직접 돌리는 대신, 벨트의 올리는위치, 내리는 위치를 가리키는 인덱스를 옮긴다
 * 
 */

public class Main {
	
	static int N, K, UP, DOWN;	// UP = 올리는 위치, DOWN = 내리는 위치
	static int[] belt;			// 각 벨트 부분의 내구도
	static boolean[] robot;		// 로봇의 유무여부
	static int zeroCount;		// 모두 닳아버린 벨트의 수
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	UP = 0;
    	DOWN = N-1;
    	belt = new int[2*N];
    	robot = new boolean[2*N];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<2*N; i++) {
    		belt[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	/*
    	 * 내구도가 0이 되는 컨베이어 벨트를 세어 K개 이상이 되면 반복을 멈춘다
    	 * 벨트 회전 -> 로봇 전진 -> 새 로봇 배치 순으로 반복한다 
    	 */
    	int step = 0;	// 현재 스텝 표시
    	while(zeroCount < K) {
    		step++;
    		rotateBelt();	// 벨트 회전
    		quitRobot();	// 맨 끝에 로봇이 있으면 하차시킨다
    		
    		/*
    		 * 로봇 전진
    		 * DOWN 바로 앞 인덱스부터, UP 바로 뒤 인덱스까지 역순환하며 로봇을 이동시킨다
    		 */
    		for(int i=rotateIdx(DOWN - 1); i != UP; i=rotateIdx(i-1)) {
    			int nextIdx = rotateIdx(i+1);
    			if (robot[i] && belt[nextIdx] > 0 && !robot[nextIdx]) {
    				robot[i] = false;
    				robot[nextIdx] = true;
    				belt[nextIdx]--;
    				
    				if (belt[nextIdx] == 0) {
    					zeroCount++;
    				}
    			}
    		}
    		quitRobot();
    		
    		/*
    		 * 새 로봇 배치
    		 */
    		if (belt[UP] > 0) setRobot();
    	}
    	
    	System.out.println(step);
    }
    
    /*
     * UP의 내구도가 1 이상일때 로봇을 내려놓고 내구도를 1 감소시킨다 
     */
    public static void setRobot() {
    	if (belt[UP] > 0) {
    		belt[UP]--;
    		robot[UP] = true;
    		
    		if (belt[UP] == 0) zeroCount++;
    	}
    }
    
    /*
     * DOWN인덱스에 로봇이 있으면 하차시킨다
     */
    public static void quitRobot() {
    	if (robot[DOWN]) {
    		robot[DOWN] = false;
    	}
    }
    
    /*
     * 컨베이어 벨트의 UP과 DOWM 인덱스를 하나씩 뒤로 옮긴다
     */
    public static void rotateBelt() {
    	UP = rotateIdx(UP - 1);
    	DOWN = rotateIdx(DOWN - 1);
    }
    
    
    /*
     * 0 ~ 2N-1 사이로 인덱스 순환 메소드
     */
    public static int rotateIdx(int n) {
    	if (n < 0) {
    		return (N << 1)+n;
    	}
    	else return n%(N << 1);
    }
}