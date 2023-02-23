package algorithm.acmi.study.week5.tlstmdck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 1. 톱니는 4개로 고정
 * 2. visited를 이용하여 해당 톱니바퀴를 돌릴경우 오른쪽 전부검사 하고 왼쪽 전부검사
 * ex) 1 2 3 4 에서 3 을 돌리면 4 검사 후 2 1 순서로 검사
 * 3. 검사를 진행하면서 시계방향은 1 반시계방향은 -1 로 visited에 저장
 * 4. 저장된 visited를 이용하여 값이 0이 아닌 각 톱니바퀴 회전
 * 5. 계산 끝
 */
public class acmi14891_톱니바퀴 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 회전의 편의성을 위해 LinekedList 로 톱니바퀴 표현
        // 4개의 톱니바퀴이기 때문에 ArrayList에 4개의 LinkedList 넣었음
        ArrayList<LinkedList<Integer>> list = new ArrayList<>();
        for(int i=0; i<5; i++){
            list.add(new LinkedList<Integer>());
        }   
        for(int i=1; i<=4; i++){
            String Line = br.readLine();
            for(int j=0; j<Line.length(); j++){
                list.get(i).add(Line.charAt(j) - '0');
            }
        }
        int K = Integer.parseInt(br.readLine());
        int sum = 0;
        for(int k=0; k<K; k++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());   //움직일 톱니바퀴 번호
            int R = Integer.parseInt(st.nextToken());   //D의 회전 방향
            int[] visited = new int[5];
            visited[D] = R;

            // D의 오른쪽 검사
            for(int i=D; i<4; i++){     

                // 본인이 회전 안하였다면 오른쪽 톱니도 회전X
                // 본인과 오른쪽 톱니가 같은 극이면 회전X 
                if(visited[i] != 0 && list.get(i).get(2) != list.get(i+1).get(6)){
                    
                    //항상 본인과 반대로 회전
                    visited[i+1] = visited[i] * -1; 
                }
            }

            // D의 왼쪽 검사
            for(int i=D; i>1; i--){

                // 본인이 회전 안하였다면 왼쪽 톱니도 회전X
                // 본인과 왼쪽 톱니가 같은 극이면 회전X 
                if(visited[i] != 0 && list.get(i).get(6) != list.get(i-1).get(2)){

                    //항상 본인과 반대로 회전
                    visited[i-1] = visited[i] * -1;
                }   
            }

            // 완성된 visited로 각 톱니바퀴 회전
            for(int i=1; i<=4; i++){
                if(visited[i] == -1){
                    list.get(i).addLast(list.get(i).removeFirst());
                }
                else if(visited[i] == 1){
                    list.get(i).addFirst(list.get(i).removeLast());
                }
            }
        }
        // 계산
        for(int i=1; i<=4; i++){
            sum += list.get(i).get(0) * (int)Math.pow(2, (i-1));
        }
        System.out.println(sum);
    }
}
