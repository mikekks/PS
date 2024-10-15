class Solution {
    int MOD = 20170805;
    int[][][] dp;
    
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        dp = new int[m+1][n+1][2];
        
        // 오던 방향이 중요하다.
        // 이동 가능한 전체 가능한 경로 수 -> dp
        
        dp[1][1][0] = 1;
        dp[1][1][1] = 1;
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(cityMap[i-1][j-1] == 0){
                    dp[i][j][0] += (dp[i-1][j][0] + dp[i][j-1][1]) % MOD;
                    dp[i][j][1] += (dp[i-1][j][0] + dp[i][j-1][1]) % MOD;
                }
                else if(cityMap[i-1][j-1] == 2){
                    dp[i][j][0] += dp[i-1][j][0];
                    dp[i][j][1] += dp[i][j-1][1];
                }
                else if(cityMap[i-1][j-1] == 1){
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                }
            }
        }
        
        return dp[m][n][0];
    }
}