import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        // dp[i][0] : 첫번째 픽
        // dp[i][1] : 마지막 픽
        int[] dpO = new int[money.length];
        int[] dpX = new int[money.length];
        
        dpO[0] = money[0];
        dpX[0] = 0;
        
        dpO[1] = money[0];
        dpX[1] = money[1];
        
        int e = money.length-1;
        
        for(int i=2; i<=e; i++){
            dpO[i] = Math.max(dpO[i-1], dpO[i-2]+money[i]);
            dpX[i] = Math.max(dpX[i-1], dpX[i-2]+money[i]);
        }
        
        return Math.max(dpO[e-1], dpX[e]);
    }
}