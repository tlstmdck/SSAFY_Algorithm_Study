import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Java의 stack 대신 array를 이용해 stack을 직접 구현함

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
//        배열을 이용해 stack 구현
        int[] stack = new int[10000];
        int index = -1;
        int com = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i=0; i<com; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
//                    push 시 index를 한칸 앞으로 이동시킨 후, 숫자 삽입
                    int num = Integer.parseInt(st.nextToken());
                    stack[++index] = num;
                    break;

                case "pop":
//                    pop 시 index가 0 이상이면 요소가 있다는 뜻이므로 요소 출력
                    if (index >= 0) {
                        System.out.println(stack[index--]);
                    }
                    else {
                        System.out.println(-1);
                    }
                    break;

                case "size":
                    System.out.println(index+1);
                    break;

                case "empty":
                    if (index == -1) {
                        System.out.println(1);
                    }
                    else {
                        System.out.println(0);
                    }
                    break;

                case "top":
                    if (index >= 0) {
                        System.out.println(stack[index]);
                    }
                    else {
                        System.out.println(-1);
                    }
                    break;

                default:
            }

        }
    }
}