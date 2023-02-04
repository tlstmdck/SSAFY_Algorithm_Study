import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 맵으로 각 단어가 몇번 나오는지 카운트 후
// 각각을 전체 단어중 갯수의 비율 구함

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SortedMap<String, Integer> map = new TreeMap<>();
        int sum = 0;
        String wood;

        // 맵에 단어 KEY에 대한 VALUE를 1씩 더함
        while((wood = br.readLine()) != null && !wood.isEmpty()) {
            map.put(wood, map.getOrDefault(wood, 0) + 1);
            sum++; // 전체 갯수 구하기
        }

        for(String s : map.keySet()) {
            System.out.printf("%s %.4f\n", s, (double)map.get(s) * 100 / sum);
        }

    }
}