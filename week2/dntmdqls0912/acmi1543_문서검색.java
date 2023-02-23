import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 앞에서부터 단어를 비교해가며 겹치는 부분이 없도록 index를 옮기며 검사한다

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String doc = br.readLine();
        String word = br.readLine();
        int answer = 0;
        int index = 0;

//        index부터 시작하는 word 비교가 doc range를 벗어나지 않을 떄까지 검사
        while (index<=doc.length()-word.length()) {
//            현재 index부터 word만큼의 길이를 잘라서 비교
            if (doc.substring(index, index+word.length()).equals(word)) {
                answer++;
                index += word.length();
            }
            else {
                index++;
            }
        }
        System.out.println(answer);
    }
}