import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 전형적인 분할정복 문제
// 각 영역을 재귀때마다 4분할하여 각각의 영역에 대해 다시 재귀 실행
// 영역의 크기가 1x1이 되면 0 또는 1여부를 확인

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] map = new String[n];
        for(int i=0; i<n; i++) {
            map[i] = br.readLine();
        }

        System.out.println(quadTree(map, 0, 0,  n));

    }

    public static String quadTree(String[] map, int x, int y, int n) {
        
//        영역의 크기가 1일 경우
        if (n == 1) {
            return map[y].charAt(x) == '0' ? "0" : "1";
        }

//       이전 영역에 대한 결과값을 String으로 이어 붙임
        String subTree = quadTree(map, x, y, n/2) +
                quadTree(map, x + n/2, y, n/2) +
                quadTree(map, x, y + n/2, n/2) +
                quadTree(map, x + n/2, y + n/2, n/2);
//        이전에 탐색한 영역이 모두 0이거나 1일경우 한글자로 합친다
        if (subTree.equals("0000")) return "0";
        if (subTree.equals("1111")) return "1";
        return "("+subTree+")";
    }
}