package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Boj9081 {
    static StringBuilder sb;

    static void np(char[] input) {
        int n = input.length;

        // step1. 꼭대기를 찾는다. (꼭대기 바로 앞이 교환할 자리)
        int i = n - 1; // 꼭대기
        while (i > 0 && input[i - 1] >= input[i])
            --i;

        if (i == 0)
            return;

        // step2. 꼭대기 바로 앞(i-1) 자리에 교환할 값을 뒤쪽부터 찾는다.
        int j = n - 1;
        while (input[i - 1] >= input[j])
            --j;

        // step3. 꼭대기 바로 앞(i-1)자리와 그 자리값보다 한단계 큰 자리(j) 수와 교환
        swap(input, (i - 1), j);

        // step4. 꼭대기 부터 맨 뒤까지 오름차순으로 정렬
        int k = n;
        Arrays.sort(input, i, k);

    }

    static void swap(char[] input, int i, int j) {
        Character tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            char[] input = new char[str.length()];

            for (int j = 0; j < input.length; j++) {
                input[j] = str.charAt(j);
            }

            np(input);
            for (Character character : input) {
                sb.append(character);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        sc.close();
    }
}

