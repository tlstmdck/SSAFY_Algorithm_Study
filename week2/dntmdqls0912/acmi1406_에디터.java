import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;

// cursor와 buffer를 이용해 각 스택의 탑 사이를 커서의 위치로 정함



public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String init = br.readLine();
        int n = Integer.parseInt(br.readLine());

        // 초기 문자열을 커서 스택에 입력
        Cursor cursor = new Cursor(init);

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            // 커맨드를 입력 받아서 그에 대한 메소드 실행
            switch (cmd) {
                case "L" :
                    cursor.L();
                    break;
                case "D" :
                    cursor.D();
                    break;
                case "B":
                    cursor.B();
                    break;
                case "P":
                    char c = st.nextToken().charAt(0);
                    cursor.P(c);

            }
        }
        cursor.print();
    }
}


class Cursor {
    // 커서의 왼쪽 문자열
    Stack<Character> cursor = new Stack<>();
    // 커서의 오른쪽 문자열
    Stack<Character> buffer = new Stack<>();

    // 초기 문자열 입력 생성자
    public Cursor(String str) {
        for(char c : str.toCharArray()) {
            cursor.push(c);
        }
    }

    // 커서 스택의 탑을 버퍼로 넣음
    public void L() {
        if (!cursor.empty()) {
            buffer.push(cursor.pop());
        }
    }

    // 버퍼 스택의 탑을 커서로 넣음
    public void D() {
        if (!buffer.empty()) {
            cursor.push(buffer.pop());
        }
    }

    // 커서 스택에서 탑을 제거
    public void B() {
        if (!cursor.empty()) {
            cursor.pop();
        }
    }

    // 문자를 커서 스택에 입력 (커서의 왼쪽)
    public void P(char c) {
        cursor.push(c);
    }

    // 입력되있는 문자열을 순서대로 출력
    public void print() {
        StringBuilder sb = new StringBuilder();
        while(!cursor.empty()) {
            buffer.push(cursor.pop());
        }

        while(!buffer.empty()) {
            sb.append(buffer.pop());
        }

        System.out.println(sb.toString());
    }

}