import java.util.*;

class Solution {
    int mod = 1000000007;
    
    public int solution(int n, int[] money) {
        int answer = 0;
        
        // 갯수 제한 X
        // 단순히 dfs X
        // 이진 탐색 느낌 X
        int[][] dp = new int[money.length+1][n+1];
        
        for(int i=1; i<=money.length; i++){
            for(int j=0; j<=n; j++){
                
                if(j == 0){
                    dp[i][j] = 1;
                }
                else if(j-money[i-1] >= 0){
                    dp[i][j] = (dp[i-1][j] + dp[i][j-money[i-1]]) % mod;
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
                
                
            }                
        }
        
        return dp[money.length][n];
    }
}