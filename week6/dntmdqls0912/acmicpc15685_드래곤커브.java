package codingTest;
/*
     n 세대 커브 = n-1 세대 그래프 + n-1 세대 그래프 90도 회전
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    각 세대는 이전세대까지의 진행 방향을 반시계 방향으로 90도 돌린 방향으로 거꾸로 진행한다
 */


public class Main {

    static int N, endX, endY;   // start# 각 세대의 끝 지점
    static boolean[][] map = new boolean[101][101];
    static ArrayList<Integer> directions;   // 드래곤 커브의 진행 방향을 기록한 배열
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            directions = new ArrayList<>();
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[endY][endX] = true; // 시작 지점 표시

            endX += dx[d];    // 0세대 진행
            endY += dy[d];
            if (isValid(endY, endX)) map[endY][endX] = true;    // 0 세대 끝 지점 표시
            
            directions.add(d); // 0 세대의 진행 방향을 기록

            // 세대 수 만큼 반복실행
            for(int j=0; j<g; j++) {
                dragonCurve();
            }
        }

        int result = 0; // 출력 결과값

        // 모든 배열을 탐색해 4칸에 대한 정점 표시여부 확인
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    static void dragonCurve() {

        // 이전 세대로부터 진행방향을 가져와 뒤에서부터 실행
        for(int i=directions.size()-1; i>=0; i--) {
            int dir = (directions.get(i) + 1) % 4; // 이전 세대의 진행방향을 반시계방향 90도로 회전
            endX += dx[dir];    // 다음 끝 위치 표시
            endY += dy[dir];

            if(isValid(endY, endX)) map[endY][endX] = true; // 정점 기록

            directions.add(dir);    // 이번 진행방향 기록
        }
    }
    
    static boolean isValid(int r, int c) {
        return 0 <= r && r <= 100 && 0 <= c && c <= 100;
    }
}