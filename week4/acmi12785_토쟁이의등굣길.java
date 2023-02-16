package algorithm.acmi.study.week4;

import java.util.Scanner;

public class acmi12785_토쟁이의등굣길 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int tx = sc.nextInt();
        int ty = sc.nextInt();
        int n = (tx-1)+(ty-1);
        int r = Math.min(tx-1,ty-1);
        long first_distance = comb(n,r);
        n = (w-tx)+(h-ty);
        r = Math.min((w-tx),(h-ty));
        long last_distance = comb(n,r);
        System.out.println(((first_distance%1000007) * (last_distance%1000007))%1000007);
    }
    public static long comb(int n, int r){
        long[][] dp = new long[400][400];
        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for(int i=2; i<=n; i++){
            for(int j=1; j<i; j++){
                dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                dp[i][j] %= 1000007;
            }
        }
        // for(int i=0; i<=n; i++){
        //     for(int j=0; j<=n; j++){
        //         System.out.print(dp[i][j] +" ");
        //     }
        //     System.out.println();
        // }
        return dp[n][r];
    }
}
