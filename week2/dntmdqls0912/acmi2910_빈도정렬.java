import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// 순서를 기억하는 LinkedHashMap과,
// 정렬 시 같은 우선순위는 기존 순서를 유지하는 Stable Sort를 이용하여 풀이
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new LinkedHashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
//        stream 사용
        map.entrySet()  // map의 key, value쌍 
                .stream()   // stream 화
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())) // key의 수가 많은 기준으로 정렬
                .forEach(o -> { // 각각의 key를 value만큼 출력
                    for(int i=0; i<o.getValue(); i++) {
                        System.out.print(o.getKey() + " ");
                    }
                });
    }
}