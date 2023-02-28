package boj;

import java.util.Scanner;

public class Boj1052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 물병 수
        int K = sc.nextInt(); // 한 번에 옮길 수 있는 수

        if(N <= K) // 한번에 다 옮길 수 있음
            System.out.println(0);
        else {
            int sell = 0; // 사야되는 물병 수

            while (true) {
                int remain = 0; // 나머지를 담을 변수
                int divideN = N;

                while (divideN != 0) {
                    if(divideN%2 == 1) // 나머지가 있음
                        remain++;

                    divideN /= 2;
                }

                if(remain <= K) // 나머지가 한번에 옮길 수 있는 거면 옮기면됨
                    break;

                // 안되면 한개 사고 총량도 그만큼 늘어남
                sell++;
                N++;
            }
            System.out.println(sell);
        }
    }
}
