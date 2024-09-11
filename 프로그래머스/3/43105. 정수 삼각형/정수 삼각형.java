import java.util.*;

class Solution {
    
    int[][] dp = new int[505][505];
    
    public int solution(int[][] triangle) {
        int answer = 0;
        
        dp[0][1] = triangle[0][0];
        
        for(int i=1; i<triangle.length; i++){
            for(int j=0; j<triangle[i].length; j++){
                int m = Math.max(dp[i-1][j], dp[i-1][j+1]);
                dp[i][j+1] = m + triangle[i][j];
            }
            
        }
        
        for(int i=0; i<triangle.length; i++){
            if(dp[triangle.length-1][i] > answer){
                answer = dp[triangle.length-1][i];
            }
        }
        
        return answer;
    }
}