import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// 해결 시간 : 16분
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();  // 첫 줄은 필요없어서 날림

        // 기본 hashset과 정렬하는 sortedSet
        Set<String> set = new HashSet<>();
        Set<String> sortedSet = new TreeSet<>();

        String name;
        int sum = 0;

        // 버퍼가 공백줄을 받을때까지 계속 받기
        while((name = br.readLine()) != null) {
            // set은 중복값을 add 하면 false를 반환한다
            // 따라서 add가 false이면 중복값이므로 듣보잡에 해당
            if (!set.add(name)) {
                sum++;
                sortedSet.add(name);    // 정렬 set에 듣보잡 추가
            }
        }

        System.out.println(sum);
        for(String rootless : sortedSet) {
            System.out.println(rootless);
        }
    }
}