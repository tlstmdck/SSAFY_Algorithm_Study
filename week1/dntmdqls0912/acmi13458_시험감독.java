import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<Integer> sites = new ArrayList<>(n);
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) {
            sites.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int visor = Integer.parseInt(st.nextToken());
        int subVisor = Integer.parseInt(st.nextToken());

        // 감독의 수, 최악의 경우 : 1,000,000 * 1,000,000
        long totalVisor = 0;
        for(int applier : sites) {
            int people = applier;

            // 주감독은 반드시 시험장에 들어감
            totalVisor++;
            // 주감독만으로 커버가 가능하면 패스
            if (people <= visor) continue;

            // 주감독이 커버하고 남은 인원을 부감독 수로 나누기
            people -= visor;
            totalVisor += people/subVisor;

            // 나머지가 있다면 +1
            if (people%subVisor > 0) {
                totalVisor++;
            }
        }
        System.out.println(totalVisor);
    }
}