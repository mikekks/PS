import java.util.*;

public class Solution {
    public int solution(String arr[]) {
        int answer = -1;
        
        int[][][] dp = new int[arr.length/2+1][arr.length/2+1][2];
        int[] list = new int[arr.length/2+1];
        int[] op = new int[arr.length/2];
        
        int opIdx = 0;
        int numIdx = 0;
                
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("+")) {
                op[opIdx++] = 1;
            }
            else if(arr[i].equals("-")){
                op[opIdx++] = 0;
            }
            else {
                list[numIdx++] = Integer.parseInt(arr[i]);
            }
        }
        
        for(int i=0; i<list.length; i++){
            dp[i][i][0] = list[i];
            dp[i][i][1] = list[i];
        }
        
        for(int len=1; len<list.length; len++){
            for(int s=0; s<list.length; s++){
                int e = s+len;
                if(e >= list.length) break;
                
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int k=s; k<e; k++){
                    if(op[k] == 1){
                        max = Math.max(max, dp[s][k][1] + dp[k+1][e][1]);
                        min = Math.min(min, dp[s][k][0] + dp[k+1][e][0]);
                    }
                    else{
                        max = Math.max(max, dp[s][k][1] - dp[k+1][e][0]);
                        min = Math.min(min, dp[s][k][0] - dp[k+1][e][1]);  
                    }

                }
                
                dp[s][e][0] = min;
                dp[s][e][1] = max;
                
            }
        }
        
        return dp[0][arr.length/2][1];
    }
}