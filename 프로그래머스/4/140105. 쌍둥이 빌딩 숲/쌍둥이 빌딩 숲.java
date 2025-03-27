import java.util.*;

class Solution {
    
    public int solution(int n, int count) {
        long MOD = 1_000_000_007;
        
        // 같은 높이를 가지는 빌딩 사이에는 그보다 높은 빌딩 X
        
        long[][] dp = new long[n+1][count+1];
        dp[1][1] = 1L;
        
        for(int i=2;i<=n;i++){          
            for(int j=1;j<=count;j++){              
                dp[i][j]=(dp[i-1][j]*(i-1)*2+dp[i-1][j-1])%1000000007;
            }
        }
        
        
        return (int)dp[n][count];
    }
}