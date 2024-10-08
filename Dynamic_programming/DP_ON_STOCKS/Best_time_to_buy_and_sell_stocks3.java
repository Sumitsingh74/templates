package Dynamic_programming.DP_ON_STOCKS;

import java.util.Arrays;
import java.util.Scanner;

public class Best_time_to_buy_and_sell_stocks3 {
    //Read the problem statement first;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int dp[][][] = new int[n + 1][3][3];
        for(int row[][] :dp){
            for(int col[] : row){
                Arrays.fill(col, -1);
            }
        }
        int ans = solve_tabu(arr,n,dp);
        System.out.println(ans);
    }

    //memoization;
    //count should be given as 2 only
    //which is atmost 2
    //the next problem statment which is stock 4
    //we will have the statement as atmost k;
    public static int solve_memo(int arr[],int ind, int buy,int count,int dp[][][]){
        if(count == 0 || ind == arr.length) return 0;
        if(dp[ind][buy][count] != -1) return dp[ind][buy][count];


        int profit = Integer.MIN_VALUE;
        if(buy == 1){
            int x = -arr[ind] + solve_memo(arr,ind + 1, 0,count, dp);
            int y = solve_memo(arr,ind + 1, 1, count ,dp);
            profit = Math.max(x,y);
        }
        else{
            int x = arr[ind] + solve_memo(arr,ind + 1, 1,count - 1, dp);
            int y = solve_memo(arr,ind + 1,0,count ,dp);
            profit = Math.max(x,y);
        }
        return dp[ind][buy][count] = profit;
    }

    //Tabulation;
    public static int solve_tabu(int arr[],int n , int dp[][][]){

        for(int row[][] : dp){
            for(int col[] : row){
                Arrays.fill(col, 0);
            }
        }
        //since we have already filled the dp with 0's we are not required to write the
        //base case for dp , since the base cases are having 0 in return;

        for(int ind = n - 1; ind >=0; ind--){
            for(int buy = 0; buy <= 1; buy++){
                for(int count = 1 ; count <= 2; count++){
                    int profit = Integer.MIN_VALUE;
                    if(buy == 1){
                        int x = -arr[ind] + dp[ind + 1][0][count];
                        int y = dp[ind + 1][1][count];
                        profit = Math.max(x,y);
                    }
                    else{
                        int x = arr[ind] + dp[ind + 1][1][count - 1];
                        int y = dp[ind + 1][0][count];
                        profit = Math.max(x,y);
                    }
                    dp[ind][buy][count] = profit;
                }
            }
        }
        return dp[0][1][2];
    }
}
