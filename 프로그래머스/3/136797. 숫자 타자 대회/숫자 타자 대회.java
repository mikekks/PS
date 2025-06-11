import java.util.*;

class Solution {
    
     private final int cost[][] = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };
    int[][][] dp;
    String numbers;
    
    public int solution(String _numbers) {
        numbers = _numbers;
        dp = new int[numbers.length()][10][10];
        
        for (int i = 0; i < numbers.length(); i++) {
            for (int j = 0; j < 10; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
                
        return dfs(0, 4, 6);
    }
    
    public int dfs(int idx, int left, int right){
        if(idx == numbers.length()) return 0;
        
        if(dp[idx][left][right] != -1) return dp[idx][left][right];
        
        int ret = Integer.MAX_VALUE;
        int curNum = numbers.charAt(idx) - '0';
        
        if(curNum != right){
            ret = Math.min(dfs(idx+1, curNum, right)+cost[left][curNum], ret);
        }
        
        if(curNum != left){
            ret = Math.min(dfs(idx+1, left, curNum)+cost[right][curNum], ret);
        }
        
        return dp[idx][left][right] = ret;
        
    }
}