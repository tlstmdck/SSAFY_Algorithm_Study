package algorithm.acmi.study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmi13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());   
        line = br.readLine();
        st = new StringTokenizer(line);

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        line = br.readLine();
        st = new StringTokenizer(line);
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        long res = 0;
        res += arr.length;  // 총감독관 ++
        for(int i=0; i<N; i++){
            arr[i] = arr[i] - B;    // 각 배열에서 총 감독관 제거
            if(arr[i] <= 0)     // 관리할 필요가 없는 경우
                continue;

            // 부 감독관 ++
            res += (arr[i] / C);    
            if(arr[i] % C != 0){    
                res++;
            }
        }
        System.out.println(res);
    }
}
