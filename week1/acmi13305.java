package algorithm.acmi.study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmi13305 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] map = new int[N];     //주유소 배열
        int[] street = new int[N-1];    //각 도로의 길이
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++){
            street[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        long res = 0;
        for(int i=0; i<N; i++){
            int next = 0;
            for(int j=i+1; j<N; j++){   //자신의 리터값보다 더 적은 리터값을 가진 주유소 반환
                if(map[i] >= map[j]){
                    next = j;
                    break;
                }
            }

            if(next == 0){      //만약 리터값이 더 적은 곳이 없을 경우
                for(int j=i; j<N-1; j++){
                    res += (long)street[j] * (long)map[i];
                }
                break;
            }

            for(int j=i; j<next; j++){      //더 적은 리터값이 존재하는 주유소 까지 계산
                res += (long)street[j] * (long)map[i]; 
            }

            if(next == N-1){    //최종 목적지에 도착하면 종료
                break;
            }

            i = next-1;     //계산이 끝나면 해당 지점에서 다시 시작
            
        }
        System.out.println(res);
    }
}