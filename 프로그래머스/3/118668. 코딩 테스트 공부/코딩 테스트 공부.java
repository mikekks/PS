import java.util.*;

public class Solution {
	public int solution(int alp, int cop, int[][] problems) {
		// 주어진 모든 문제를 풀 수 있는 알고력, 코딩력

		int eAlp = 0;
		int eCop = 0;

		for(int[] p : problems){
			eAlp = Math.max(eAlp, p[0]);
			eCop = Math.max(eCop, p[1]);
		}
        
        alp = Math.min(alp, eAlp);
        cop = Math.min(cop, eCop);

		int[][] dp = new int[300][300];
        
        for(int i=0; i<=299; i++){
			for(int j=0; j<=299; j++){
                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        
        dp[alp][cop] = 0;
        
		for(int i=alp; i<=eAlp; i++){
			for(int j=cop; j<=eCop; j++){
                 
				dp[i+1][j] = Math.min(dp[i][j]+1, dp[i+1][j]);
                dp[i][j+1] = Math.min(dp[i][j]+1, dp[i][j+1]);

				for(int[] p : problems){                    
					if(i >= p[0] && j >= p[1]){
                        int ni = Math.min(eAlp, i + p[2]);
                        int nj = Math.min(eCop, j + p[3]);
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + p[4]);
					}
				}
			}
		}

		return dp[eAlp][eCop];
	}
}