package algorithm.acmi.study.week5.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 1. map = 벨트로 한번 회전함
 * 2. robot은 Queue로 구현하여 순서를 구성해주고 하나씩 꺼내서 이동가능여부
 * 체크 하고 만약 이동가능하면 한칸 증가 그런데 N에 도착하면 큐에 다시 add 안함
 * 3. idx 1에 내구도 확인 후 로봇 Queue에 추가
 * 4. 내구도 검사 후 반복하거나 종료
 */
public class acmi20055_컨베이어벨트로봇 {
    static int N,K;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[2][N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            map[0][i] = Integer.parseInt(st.nextToken());
        }
        for(int i=N-1; i>=0; i--){
            map[1][i] = Integer.parseInt(st.nextToken());
        }
        int zero_cnt = 0;
        Queue<Integer> robot = new ArrayDeque<>();
        int res = 0;

        while(zero_cnt < K){
            res++;  // 단계의 갯수(답)
            rotate();   // 벨트만 회전 로봇 제외
            int size = robot.size();
            
            // cur 앞에 존재하는 로봇의 idx
            // 맨 앞 로봇은 앞에 아무것도 없기에 0으로 초기화
            int next = 0;
            
            // 로봇 큐에 모든 요소를 꺼내서 검사
            for(int i=0; i<size; i++){

                // 회전 하였기 때문에 무조건 1칸은 이동
                int cur = robot.poll()+1;

                // 그런데 N에 도달하면 해당 로봇은 제거
                if(cur == N-1)
                    continue;

                // 만약 로봇이 갈 곳의 내구도가 0이 아니고
                // 갈 곳에 다음 로봇이 없을 떄
                if(map[0][cur+1] != 0 && cur+1 != next){

                    // 내구도 감소
                    map[0][cur+1]--;
                    
                    // 이동 후 N에 도달한 상태가 아니면 큐에 삽입
                    if(cur+1 != N-1)
                        robot.add(cur+1); 
                    
                    // 다음 큐의 로봇한테는 cur이 next임    
                    next = cur+1;
                }
                else{
                    // 로봇은 움직이지 않기 때문에
                    // 다시 큐에 삽입 및 next 변경
                    robot.add(cur);
                    next = cur;
                }
                
            }

            // 3번 동작 
            if(map[0][0] != 0){
                robot.add(0);
                map[0][0]--;
            }

            // 4번 동작
            int sum = 0;
            for(int[] list : map){
                for(int num : list){
                    if(num == 0){
                        sum++;
                    }
                }
            }
            zero_cnt = sum;

        }
        System.out.println(res);
    }
    /*
     * 회전 방식
     * 1 2 3 4  
     * 8 7 6 5
     * 
     * Queue 삽입 -> 1 2 3 4 5 6 7 8
     * 
     * Queue poll을 두번째 인덱스부터
     * x 1 2 3
     * 7 6 5 4
     * 
     * x부분에 남은 8 삽입
     * 8 1 2 3
     * 7 6 5 4
     */
    public static void rotate(){
        Queue<Integer> qu = new ArrayDeque<>();
        for(int i=0; i<N; i++){
            qu.add(map[0][i]);
        }
        for(int i=N-1; i>=0; i--){
            qu.add(map[1][i]);
        }
        for(int i=1; i<N; i++){
            map[0][i] = qu.poll();
        }
        for(int i=N-1; i>=0; i--){
            map[1][i] = qu.poll();
        }
        map[0][0] = qu.poll();
    }
}
